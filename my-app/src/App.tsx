import React from 'react';
import { Routes, Route } from 'react-router-dom';

import './App.css';
import Login from './components/auth/login';
import HomeLayout from './components/containers/home';
import CreateUserPage from './components/CreateUser';
import HomePage from './components/Home';
import ProductCreatePage from './components/products/create';

function App() {
  return (
    <>
    <Routes>
      <Route path="/" element={<HomeLayout/>}>
        <Route index element={<HomePage/>} />
        <Route path="create" element={<CreateUserPage/>} />
        <Route path="products/create" element={<ProductCreatePage/>} />
        <Route path="login" element={<Login/>} />
      </Route>
    </Routes>
    </>
  );
}

export default App;
