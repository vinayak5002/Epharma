import React, { useState, useContext } from 'react';
import { X } from 'lucide-react';
import UpdateProfileDetails from './sections/UpdateProfileDetails';
import UpdateAddressDetails from './sections/UpdateAddressDetails';
import UpdateCardDetails from './sections/UpdateCardDetails';

const UpdateProfile = ({ setShowUpdateProfile, custId }: any) => {
  
  const [activeTab, setActiveTab] = useState('profile');

  return (
    <div className='card d-inline-flex rounded p-3 bg-primary-success row m-1 text-center text-bg-light shadow-lg w-100'>
      <div className='d-flex justify-content-end'>
        <X onClick={() => setShowUpdateProfile(false)} />
      </div>
      <div className="card-body" style={{height: '90vh'}}>
        <ul className="nav nav-tabs" id="myTab" role="tablist">
          <li className="nav-item" role="presentation">
            <button 
              className={`nav-link ${activeTab === 'profile' ? 'active' : ''}`} 
              id="profile-tab" 
              onClick={() => setActiveTab('profile')}
              type="button"
            >
              Update Profile Details
            </button>
          </li>
          <li className="nav-item" role="presentation">
            <button 
              className={`nav-link ${activeTab === 'address' ? 'active' : ''}`} 
              id="address-tab" 
              onClick={() => setActiveTab('address')}
              type="button"
            >
              Add Address
            </button>
          </li>
          <li className="nav-item" role="presentation">
            <button 
              className={`nav-link ${activeTab === 'card' ? 'active' : ''}`} 
              id="card-tab" 
              onClick={() => setActiveTab('card')}
              type="button"
            >
              Add Card details
            </button>
          </li>
        </ul>
        <div className="tab-content mt-3">
          <div 
            className={`tab-pane fade ${activeTab === 'profile' ? 'show active' : ''}`} 
            id="profile" 
            role="tabpanel"
          >
            <UpdateProfileDetails />
          </div>
          <div 
            className={`tab-pane fade ${activeTab === 'address' ? 'show active' : ''}`} 
            id="address" 
            role="tabpanel"
          >
            <UpdateAddressDetails/>
          </div>
          <div 
            className={`tab-pane fade ${activeTab === 'card' ? 'show active' : ''}`} 
            id="card" 
            role="tabpanel"
          >
            <UpdateCardDetails />
          </div>
        </div>
      </div>
    </div>
  );
};

export default UpdateProfile;
