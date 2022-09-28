import { GoogleReCaptchaProvider } from "react-google-recaptcha-v3";
import { LoginPage } from "./LoginPage";

const Login = () => {
  return (
    <GoogleReCaptchaProvider reCaptchaKey="6Ldm6DoiAAAAAF5OBLjVc7sSE02gt-hEFBzWqXvV">
      <LoginPage/>
    </GoogleReCaptchaProvider>
  );
}

export default Login;