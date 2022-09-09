export interface UserItem {
    age: number;
    email: string;
    id: number;
    image: string;
    password: string;
    phone: string;
  }

  export interface UserState {
    list: Array<UserItem>
  }