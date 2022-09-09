import { Form, FormikProvider, useFormik } from "formik";
import { IUserCreate } from "./types";
import http from "../../http_common";
import { useNavigate } from "react-router-dom";
import CropperDialog from "../common/CropperDialog";

const CreateUserPage = () => {
    const initValues: IUserCreate = {
        image: "",
        age: 0,
        email: "",
        phone: "",
        password: ""
    }
    const navigator = useNavigate();

    const onHandleSubmit = async (values: IUserCreate) => {
        console.log("Send data server", values);
        try {
            const result = await http.post<string>("create", values);
            console.log("Create user result", result.data);
            navigator("/");
        }
        catch(exp)
        {
            console.log("Server problem", exp);
        }
        
    }

    const formik = useFormik({
        initialValues: initValues,
        onSubmit: onHandleSubmit
    });

    const {errors, touched, handleSubmit, handleChange, setFieldValue} = formik;

    return (
      <div className="row">
        <div className="offset-md-3 col-md-6">
          <h1 className="text-center">Додати користувача</h1>
          <FormikProvider value={formik}>
            <Form onSubmit={handleSubmit}>
              <div className="mb-3">
                <label htmlFor="email" className="form-label">
                  Пошта
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="email"
                  name="email"
                  onChange={handleChange}
                />
              </div>

              <div className="mb-3">
                <label htmlFor="phone" className="form-label">
                  Телефон
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="phone"
                  name="phone"
                  onChange={handleChange}
                />
              </div>

              <div className="mb-3">
                <label htmlFor="age" className="form-label">
                  Вік
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="age"
                  name="age"
                  onChange={handleChange}
                />
              </div>

              <CropperDialog
                onChange={setFieldValue}
                field="image"
                error={errors.image}
                touched={touched.image}
              />
              
              <div className="mb-3">
                <label htmlFor="password" className="form-label">
                  Пароль
                </label>
                <input
                  type="password"
                  className="form-control"
                  id="password"
                  name="password"
                  onChange={handleChange}
                />
              </div>

              <div className="mb-3">
                <button type="submit" className="btn btn-primary">
                  Додати
                </button>
              </div>
            </Form>
          </FormikProvider>
        </div>
      </div>
    );
};

export default CreateUserPage;