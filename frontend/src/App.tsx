import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import NavBar from './components/NavBar';
import Footer from './components/Footer';
import Login from './components/Login';
import SignUp from './components/SignUp';
import CartPage from './components/CartPage';
import Medicine from './components/Medicine';
import Home from './components/Home';
import ViewProfile from './components/ViewProfile';
import UpdateProfile from './components/UpdateProfile';
import { PlaceOrderPage } from './components/PlaceOrderPage';
import MyOrdersPage from './components/MyOrdersPage';

const App: React.FC = () => {
  return (
    <div className="d-flex flex-column min-vh-100">
      <BrowserRouter>
        <NavBar />

        <div className="flex-grow-1">
          <Routes>
            <Route path='/' element={<Home />} />
            <Route path='/home' element={<Home />} />
            <Route path='/medicine' element={<Medicine />} />
            <Route path='/place-order' element={<PlaceOrderPage />} />
            <Route path='/my-orders' element={<MyOrdersPage />} />
            <Route path='/sign-up' element={<SignUp />} />
            <Route path='/login' element={<Login />} />
            <Route path='/addtocart' element={<CartPage />} />
            <Route path='/view' element={<ViewProfile />} />
            
          </Routes>
        </div>

        <Footer />
      </BrowserRouter>
    </div>
  );

}

export default App;
