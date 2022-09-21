import React from 'react';
import { Routes, Route } from 'react-router-dom';

import './App.css';
import { LoginPage } from './components/auth/login';
import HomeLayout from './components/containers/home';
import CreateUserPage from './components/CreateUser';
import HomePage from './components/Home';

function App() {
  return (
    <>
    <Routes>
      <Route path="/" element={<HomeLayout/>}>
        <Route index element={<HomePage/>} />
        <Route path="create" element={<CreateUserPage/>} />
        <Route path="login" element={<LoginPage/>} />
      </Route>
    </Routes>
    </>
  );
}

export default App;
