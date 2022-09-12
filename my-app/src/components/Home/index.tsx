import axios from "axios";
import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { useTypedSelector } from "../../hooks/useTypedSelector";
import {UserItem} from './types';


const HomePage = () => {
    const {list, loading} = useTypedSelector(store=>store.user); //useSelector((store) => store.user);
    console.log("users list redux ", list);
    const dispatch = useDispatch();
    //const [users, setUsers] = useState<Array<UserItem>>([]);

    useEffect(() => {
      console.log("Load data server");
      dispatch({
        type: "GET_LIST_USER",
      });
      axios.get<Array<UserItem>>("http://localhost:8080/").then((resp) => {
        dispatch({
          type: "GET_LIST_USER_SUCCESS",
          payload: resp.data,
        });
        //console.log("Server result", resp);
        //setUsers(resp.data);
      });
    }, []);
    console.log("users", list);

    const listUser = list.map((item) => (
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
            {loading && <tr> <td colSpan={3}>Loading ...</td> </tr>}
            {listUser}
          </tbody>
        </table>
      </>
    );
};

export default HomePage;