import * as React from "react";
import { Link } from "react-router-dom";
import { useTypedSelector } from "../../../hooks/useTypedSelector";
import http from "../../../http_common";


const Navbar: React.FC = () => {

  const {isAuth, user} = useTypedSelector(store=>store.auth);
  const url = http.defaults.baseURL;
  return (
    <header>
      <nav className="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <div className="container">
          <Link className="navbar-brand" to="/">
            Магазинчик
          </Link>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarCollapse"
            aria-controls="navbarCollapse"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarCollapse">
            <ul className="navbar-nav me-auto mb-2 mb-md-0">
              <li className="nav-item">
                <Link
                  className="nav-link"
                  aria-current="page"
                  to="/create"
                >
                  Додати користвача
                </Link>
              </li>

              <li className="nav-item">
                <Link
                  className="nav-link"
                  aria-current="page"
                  to="/products/create"
                >
                  Додати товар
                </Link>
              </li>
            </ul>
            {isAuth ? (
              <ul className="navbar-nav">
                <li className="nav-item">
                  <Link className="nav-link" to="/profile">
                    <img src={url+"files/32_"+user?.image} />
                    {user?.email}
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/">
                    Вихід
                  </Link>
                </li>
              </ul>
            ) : (
              <ul className="navbar-nav">
                <li className="nav-item">
                  <Link className="nav-link" to="/login">
                    Вхід
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/register">
                    Реєстрація
                  </Link>
                </li>
              </ul>
            )}
          </div>
        </div>
      </nav>
    </header>
  );
};
export default Navbar;
