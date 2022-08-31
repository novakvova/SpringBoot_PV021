import axios from "axios";
import { useEffect, useState } from "react";

interface UserItem {
  age: number;
  emasil: string;
  id: number;
  image: string;
  password: string;
  phone: string;
}

const HomePage = () => {
    const [users, setUsers] = useState<Array<UserItem>>([]);

    useEffect(() => {
        console.log("Load data server");
        axios.get<Array<UserItem>>("http://localhost:8080/")
            .then(resp => {
                //console.log("Server result", resp);
                setUsers(resp.data);
            });
    }, []);
    console.log("users", users);

    const listUser = users.map((item) => (
      <tr key={item.id}>
        <th>{item.emasil}</th>
        <td>{item.phone}</td>
        <td>{item.age}</td>
      </tr>
    ));

    return (
      <>
        <h1>Головна сторінка</h1>
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