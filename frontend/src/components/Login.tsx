import { Divide } from 'lucide-react';
import React, { useContext, useState } from 'react'
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { CustContext } from '../state/ContextProvide';

import {useKeenSlider} from "keen-slider/react"
import "keen-slider/keen-slider.min.css"
import { CartData } from '../types/types';
import { fetchCartData } from '../data/data';
interface UserData {
  customerEmailId: string,
  password: string
}

interface FormErrorState {
  customerEmailIdError: string,
  passwordError: string
}

let url: string = "http://localhost:5002/customer-api/customer/login";
const Login = () => {



  const [sliderRef] = useKeenSlider({
    loop:true,
    mode:'free-snap',
    slides:{
      perView: 1,

    }
  })

  const [formErrors, setformErrors] = useState<FormErrorState>({} as FormErrorState);
  const [isFormValid, setIsFormValid] = useState(false);
  const [showPassword,setShowPassword]=useState(false);

  const [formData, setFormData] = useState<UserData>({
    customerEmailId:"",
    password:""
  });
  const navigate = useNavigate();


  const {setCustomer, setNumCartItems, setCustomerName} = useContext(CustContext);
 
  const togglePasswordVisibility=()=>{
     setShowPassword((prevShowPassword)=> !prevShowPassword);
  }

  const handleInputChange = (e: any) => {
    const { name, value } = e.target;

    setFormData({
      ...formData,
      [name]: value
    });
  };

  const handleSubmit = async (e: any) => {
    e.preventDefault();
    // Form is valid, you can submit or process the data here

    try {
      const response = await axios.post(url, formData);
      console.log(response);

      const data = response.data;

      setFormData((prevState) => ({
        ...prevState,
        customerEmailId: "",
        password: "",
      }));

      setCustomer(data.customerId);
      setCustomerName(data.customerName);
      
      const cartList: CartData[] = await fetchCartData(data.customerId);
      setNumCartItems(cartList.length);

      setIsFormValid(true);
      console.log("Logged in");

      navigate("/home");
    }
    catch (err) {
      setIsFormValid(false);
      setformErrors({
        customerEmailIdError:"invalid credentials, try it with correct credentials",
        passwordError:""
      })
      console.log(err)
    }
  };


  return (
    <div className="container d-flex justify-content-center align-items-center vh-100">
      <div className="card p-4 shadow-lg" style={{ maxWidth: '400px', width: '100%' }}>
        <h2 className="card-title text-center mb-4">Login</h2>
        {isFormValid ? (
          <div className="alert alert-success text-center">Login successfully</div>
        ) : (
          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <label htmlFor="email" className="form-label">Email:</label>
              <input
                type="email"
                id="email"
                name="customerEmailId"
                value={formData.customerEmailId}
                onChange={handleInputChange}
                className="form-control"
                required
              />
            </div>
  
            <div className="mb-3">
              <label htmlFor="password" className="form-label">Password:</label>
              <div className="input-group">
                <input
                  id="password"
                  type={showPassword ? 'text' : 'password'}
                  name="password"
                  value={formData.password}
                  onChange={handleInputChange}
                  className="form-control"
                  required
                />
                <button
                  type="button"
                  onClick={togglePasswordVisibility}
                  className="btn btn-outline-secondary"
                >
                  {showPassword ? 'Hide' : 'Show'}
                </button>
              </div>
            </div>
  
            <button type="submit" className="btn btn-primary w-100" disabled={isFormValid}>
              Login
            </button>
  
            {/* Conditionally render the error message box */}
            {formErrors && formErrors.customerEmailIdError && (
              <div className="alert alert-danger mt-3 text-center">
                {formErrors.customerEmailIdError}
              </div>
            )}
  
            <p className="text-center mt-3">
              Or if you don't have an account, <Link to='/sign-up' className="btn btn-link">Create one</Link>
            </p>
          </form>
        )}
      </div>
    </div>
  );

}

export default Login
