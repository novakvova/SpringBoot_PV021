import { userReducer } from './../components/Home/reducer';
import { authReducer } from './../components/auth/login/reducer';
import { combineReducers } from "redux";

export const rootReducer = combineReducers({
    user: userReducer,
    auth: authReducer
});

export type RootState = ReturnType<typeof rootReducer>;