
export enum AuthActionTypes {
    LOGIN_AUTH = "LOGIN_AUTH",
    LOGIN_AUTH_SUCCESS = "LOGIN_AUTH_SUCCESS"
};

export interface ILogin {
    email: string,
    password: string
}

export interface ILoginResponse {
    token: string
}

export interface IUser {
    email: string,
    image: string,
    exp: Date | null
}


export interface LoginAuthAction {
    type: AuthActionTypes.LOGIN_AUTH
}

export interface LoginAuthSuccessAction {
    type: AuthActionTypes.LOGIN_AUTH_SUCCESS,
    payload: IUser
}

export type AuthAction = LoginAuthAction | LoginAuthSuccessAction;