import { useState } from "react";
import http_common from "../../../http_common";

const ProductCreatePage = () => {

    const [images, setImages] =  useState<Array<string>>([]);

    const handleSelectImage = async (e: React.ChangeEvent<HTMLInputElement>) => {
        const files = e.target.files;
        if (files && files.length) {
            let listImg: Array<string>=[];
            for(let i=0; i<files.length; i++)
            {
                console.log("---files length---", files.length);
                const file = files[i];
                
                var formData = new FormData();
                formData.append("productimage", file);
                
                http_common.post("api/products/upload", formData, {
                    headers: {
                      'Content-Type': 'multipart/form-data'
                    }
                });


                const url = URL.createObjectURL(file);
                listImg.push(url);
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

      const listImages = images.map((item, index) => (
        <img key = {index}  src={item} width="100"/> 
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