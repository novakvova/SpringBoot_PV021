import { UserState } from './types';

const initialState : UserState = {
    list: [
        {
            id: 1,
            email: "аа@dd.dd",
            image: 'ssd',
            password:'ssss',
            phone:'sss',
            age: 18
        }
    ]
}

export const userReducer = (state= initialState, action: any) : UserState => {


    return state;
};

