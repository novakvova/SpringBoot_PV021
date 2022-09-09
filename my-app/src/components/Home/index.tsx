import axios from "axios";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import {UserItem} from './types';


const HomePage = () => {
    const {list} = useSelector((store: any) => store.user);
    console.log("users list redux ", list);
    //const [users, setUsers] = useState<Array<UserItem>>([]);

    // useEffect(() => {
    //     console.log("Load data server");
    //     axios.get<Array<UserItem>>("http://localhost:8080/")
    //         .then(resp => {
    //             //console.log("Server result", resp);
    //             setUsers(resp.data);
    //         });
    // }, []);
    console.log("users", list);

    const listUser = list.map((item: any) => (
      <tr key={item.id}>
        <th>{item.email}</th>
        <td>{item.phone}</td>
        <td>{item.age}</td>
      </tr>
    ));

    return (
      <>
        <h1>Головна сторінка</h1>
        <button className="btn btn-success">Додати в редакс</button>
        <table className="table">
          <thead>
            <tr>
              <th scope="col">Email</th>
              <th scope="col">Телефон</th>
              <th scope="col">Вік</th>
            </tr>
          </thead>
          <tbody>
            {listUser}
          </tbody>
        </table>
      </>
    );
};

export default HomePage;