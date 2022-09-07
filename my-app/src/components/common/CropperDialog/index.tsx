import { useState } from "react";
import { ICropperModal } from "./types";

const CropperDialog : React.FC<ICropperModal> = ({
    field
}) => {
    const [currentImage, setCurrentImage] = useState<string>(
        "https://cdn3.iconfinder.com/data/icons/photo-tools/65/select-512.png"); 

    return (
      <>
        <label htmlFor="image">
          <img src={currentImage}
            style={{ cursor: "pointer"}}
            width="150" />
        </label>
      </>
    );
}

export default CropperDialog;