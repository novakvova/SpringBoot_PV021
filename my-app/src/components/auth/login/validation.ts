import * as yup from "yup";
export const LoginSchema = yup.object({
    
    email: yup
        .string()
        .email("Вкажіть праивльно пошту")
        .required("Пошта є обов'язкови полeм"),
    
    password: yup.string()
        .min(6)
        .max(255)
        .required("Пароль є обов'язкови полeм")
    
});