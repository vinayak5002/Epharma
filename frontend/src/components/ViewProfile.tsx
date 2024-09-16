import React, { useEffect, useState, useContext } from "react";
import axios from "axios";
import { CustContext } from "../state/ContextProvide";
import UpdateProfile from "./UpdateProfile";
import ChangePassword from "./ChangePassword";
import { ToastContainer } from "react-toastify";

interface UserData {
  customerId: number;
  customerName: string;
  customerEmailId: string;
  contactNumber: string;
  gender: string;
  dateOfBirth: Date;
  password: string;
  plan: string;
  healthCoins: number;
}
interface CustomerAddress {
  addressId: Number;
  addressName: string;
  addressLine1: string;
  addressLine2: string;
  area: string;
  city: string;
  state: string;
  pincode: string;
}
const ViewProfile = () => {
  const [state, setState] = useState({} as UserData);
  const [address, setAddress] = useState({} as CustomerAddress);

  const { customerId } = useContext(CustContext);
  const fetchData = async () => {
    try {
      const customerIdInt = Number(customerId);
      // Ensure customerIdInt is a valid number
      if (isNaN(customerIdInt)) {
        throw new Error("Invalid customer ID");
      }

      const response = await axios.get(
        `http://localhost:5002/customer-api/customer/${customerIdInt}`
      );
      const data = response.data;
      console.log("heello profile");
      setState(data); // data get set to the state
      setAddress(data);
      console.log(data);
      console.log(address);
    } catch (error) {
      console.log(error);
    }
  };

  //  Update profile function
   const updateProfile = async () => {
    try {
      const response = await axios.put(
        'http://localhost:5002/customer-api/customer/update-profile',
        state
      );
      console.log('Profile updated:', response.data);
      alert('Profile updated successfully!');
      console.log(state)
    } catch (error) {
      console.log('Error updating profile:', error);
      alert('Failed to update profile. Please try again.');
    }
  };

  useEffect(() => {
    if (customerId != null && customerId !== undefined) fetchData();
  }, [customerId]);


  const [showUpdateProfile, setShowUpdateProfile] = React.useState(false)
  const [showUpdatePassword,setShowUpdatePassword]=React.useState(false);

  return (
    <div className="container-fluid">
      <ToastContainer />
      <div className="row vh-100">
        {/* Profile Card on the left */}
        <div className="col-md-4 d-flex align-items-center">
          <div className="card d-inline-flex rounded p-3 bg-primary-success text-center text-bg-light shadow-lg w-100">
            <div className="card-body">
              <img 
                src="https://via.placeholder.com/150" 
                alt="Profile" 
                className="card-img-top rounded-circle mx-auto mb-3" 
                style={{ width: '150px', height: '150px', objectFit: 'cover' }}
              />
              <h5 className="card-title">Id: {state.customerId}</h5>
              <h5 className="card-text">EmailId: {state.customerEmailId}</h5>
              <h5 className="card-text">Contact: {state.contactNumber}</h5>
              <h5 className="card-text">Name: {state.customerName}</h5>
              <h5 className="card-text">Gender: {JSON.stringify(state.gender)}</h5>
              <h5 className="card-text">HealthCoins: {state.healthCoins}</h5>
              <h5 className="card-text">Plan: {state.plan}</h5>
              <div className='d-flex justify-content-around mt-3'>
                <button 
                  className='btn btn-primary' 
                  onClick={() => {setShowUpdateProfile(true); setShowUpdatePassword(false)}}
                >
                  Edit Profile
                </button>
                <button 
                  className='btn btn-secondary' 
                  onClick={() => {setShowUpdatePassword(true); setShowUpdateProfile(false)}}
                >
                  Change Password
                </button>
              </div>
            </div>
          </div>
        </div>

        {/* Update Profile and Update Password on the right */}
        <div className="col-md-8 d-flex align-items-center justify-content-center">
          <div className="w-100">
            {showUpdateProfile && 
              <UpdateProfile setShowUpdateProfile={setShowUpdateProfile} custId={state.customerId} />
            }
            {showUpdatePassword && 
              <ChangePassword setShowUpdatePassword={setShowUpdatePassword} custId={state.customerId} />
            }
          </div>
        </div>
      </div>
    </div>
  );
};

export default ViewProfile;
