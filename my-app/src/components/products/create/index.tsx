import { useState } from "react";
import http_common from "../../../http_common";
import { IProductImageSave } from "./types";

const ProductCreatePage = () => {

    const [images, setImages] =  useState<Array<IProductImageSave>>([]);

    const handleSelectImage = async (e: React.ChangeEvent<HTMLInputElement>) => {
        const files = e.target.files;
        if (files && files.length) {
            let listImg: Array<IProductImageSave>=[];
            for(let i=0; i<files.length; i++)
            {
                console.log("---files length---", files.length);
                const file = files[i];
                
                var formData = new FormData();
                formData.append("productimage", file);
                
                const response = await http_common.post<IProductImageSave>("api/products/upload", formData, {
                    headers: {
                      'Content-Type': 'multipart/form-data'
                    }
                });
                listImg.push(response.data);
            }
            setImages([...images, ...listImg]);

          //const file = files[0];
        //   if (/^image\/\w+/.test(file.type)) {
        //     const fileType = file.type;
        //     if (fileType === "image/png") setUploadedImageType(file.type);
        //     else setUploadedImageType("image/jpeg");
        //     const url = URL.createObjectURL(file);
        //     await toggleModal();
        //     await setImage(url);
        //     cropperObj?.replace(url);
    
        //   } else {
        //     alert("Оберіть файл зображення");
        //   }
          e.target.value="";
        }
      };

      const listImages = images.map((item) => (
        <img key = {item.id}  src={"http://localhost:8080/files/" + item.fileName} width="100"/> 
      ));

    return (
        <>
            <h1>Додати продукт</h1>
            {listImages}
            <label htmlFor="files">
                Оберіть фото
            </label>
            <input type="file" multiple 
                accept="image/jpeg,image/png,image/jpg" 
                style={{display: "none"}} 
                id="files" name="files" 
                onChange={handleSelectImage} />
        </>
    );
}

export default ProductCreatePage;