package org.example.storage;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void init() {
        try {
            if(!Files.exists(rootLocation))
            {
                Files.createDirectory(rootLocation);
            }
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String fileName = UUID.randomUUID().toString()+"."+extension; //генеруємо унікальне ім'я
            Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));
            return fileName;
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }
    @Override
    public String store(String base64) {
        try {
            if (base64.isEmpty()) {    //перевіряється чи файлік прийшов
                throw new StorageException("Failed to store empty base64 ");
            }
            UUID uuid = UUID.randomUUID(); //генеруємо унікальне ім'я

            String [] charArray = base64.split(","); //розділяємо код картинки на дві частини, відділяємо розширення
            String extension;
            System.out.println("-----------------"+ charArray[0]);
            switch (charArray[0]) {//check image's extension
                case "data:image/png;base64":
                    extension = "png";
                    break;
                default://should write cases for more images types
                    extension = "jpg";
                    break;
            }

            String randomFileName = uuid.toString()+"."+extension; //робимо ім'я файліка: унікальне ім'я + розширення
            java.util.Base64.Decoder decoder = Base64.getDecoder(); //створюємо екземпляр декодера
            byte[] bytes = new byte[0]; // створюємо массив байтів
            bytes = decoder.decode(charArray[1]); // декодуємо Base64 до вайтів
            int [] imageSize = {32, 150, 300, 600, 1200}; // масив розмірів фотографій
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
            for(int size : imageSize){ // в циклі створюємо фотки кожного розміру
                String directory= rootLocation.toString() +"/"+size+"_"+randomFileName; //створюємо папку де фотка буде зберігатися
// My Example
//створюємо буфер для нової фотографії, де важливо вказуємо розширення яке буде у фотки та розмір (32х32, 150х150)
                //по типу оперативна пам'ять
                BufferedImage newImg = ImageUtils.resizeImage(image,
                        extension=="jpg"? ImageUtils.IMAGE_JPEG : ImageUtils.IMAGE_PNG, size,size);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); //створюємо Stream
                //фото записуємо у потік для отримання масиву байтів
                ImageIO.write(newImg, extension, byteArrayOutputStream); //за допомогою цього Stream записуємо в буфер фотографію згідно з розширенням
                byte [] newBytes = byteArrayOutputStream.toByteArray(); //з цього Stream знову отримуємо байти
                FileOutputStream out = new FileOutputStream(directory);
                out.write(newBytes); //байти зберігаємо у фійлову систему на сервері
                out.close();

            }
            return randomFileName; //повертаємо назву файла

        } catch (Exception e) {
            throw new StorageException("Failed to store file ", e);
        }
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
    @Override
    public  void  removeFile(String removeFile){
        int [] imageSize = {32, 150, 300, 600, 1200};
        for (int size : imageSize) {
            Path filePath = load(size+"_"+removeFile);
            File file = new File(filePath.toString());
            if (file.delete()) {
                System.out.println(removeFile + " Файл видалено.");
            } else System.out.println(removeFile + " Файл не знайдено.");
        }

    }
}

