import { AuthState, AuthAction, AuthActionTypes } from './types';

const intialState: AuthState = {
    user: undefined,
    isAuth: false,
    loading: false
}

export const authReducer = (state = intialState, action: AuthAction) : AuthState =>
{
    switch(action.type)
    {
        case AuthActionTypes.LOGIN_AUTH: 
            return {
                ...state,
                isAuth: false,
                loading: true
            };

        case AuthActionTypes.LOGIN_AUTH_SUCCESS: 
            return {
                ...state,
                isAuth: true,
                loading: false,
                user: { ...action.payload }
            }
    }
    return state;
}