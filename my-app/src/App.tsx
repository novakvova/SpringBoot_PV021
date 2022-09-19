import React from 'react';
import { Routes, Route } from 'react-router-dom';

import './App.css';
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
      </Route>
    </Routes>
    </>
  );
}

export default App;
