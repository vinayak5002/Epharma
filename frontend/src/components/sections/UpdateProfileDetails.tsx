import React, { useContext, useState } from 'react';
import { CustContext } from '../../state/ContextProvide';
import { CustomerData } from '../../types/types';
import { fetchCustomerData } from '../../data/data';
import { toast } from 'react-toastify';
import axios from 'axios';

interface NewProfileData  {
  customerName: string | null,
  customerEmailId: string | null,
  contactNumber: string | null,
  gender: string | null,
  plan: string | null,
};

const UpdateProfileDetails: React.FC = () => {
  const [state, setState] = useState<NewProfileData>({
    customerName: null,
    customerEmailId: null,
    contactNumber: null,
    gender: null,
    plan: null,
  });

  const { customerId, setCustomer, setCustomerName } = useContext(CustContext);

  const updateProfile = async () => {
    try {
      const params: any = {...state};
      params.customerId = customerId;

      console.log(params)

      const response = await axios.put(
        'http://localhost:5002/customer-api/customer/update-profile',
        params
      );
      console.log(response.data);
      toast.success('Profile updated successfully!');
    } catch (error) {

      if (axios.isAxiosError(error)) {

        if (error.response?.status === 400) {
          toast.error('Email id already exists');
          console.log('Bad Request - 400');
        } else {
          toast.error('An unexpected error occurred');
          console.log('Error:', error.message);
        }
      } else {
        console.log('Unexpected error:', error);
      }

      return;
    }

    const customerData: CustomerData = await fetchCustomerData(customerId);

    setCustomer(customerData.customerId);
    setCustomerName(customerData.customerName);

    setTimeout(() => {
      window.location.reload();
    }, 1000);
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setState((prevState: any) => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    updateProfile();
  };

  return (
    <div className="container mt-4">
      <h2 className="mb-4">Update Profile</h2>
      <form onSubmit={handleSubmit}>
        <div className="row mb-3">
          <div className="col-md-3 text-end">
            <label htmlFor="customerName" className="form-label">Name</label>
          </div>
          <div className="col-md-9">
            <input
              type="text"
              className="form-control"
              id="customerName"
              name="customerName"
              value={state.customerName === null ? '' : state.customerName}
              onChange={handleChange}
            />
          </div>
        </div>
        <div className="row mb-3">
          <div className="col-md-3 text-end">
            <label htmlFor="customerEmailId" className="form-label">Email ID</label>
          </div>
          <div className="col-md-9">
            <input
              type="email"
              className="form-control"
              id="customerEmailId"
              name="customerEmailId"
              value={state.customerEmailId === null ? '' : state.customerEmailId}
              onChange={handleChange}
            />
          </div>
        </div>
        <div className="row mb-3">
          <div className="col-md-3 text-end">
            <label htmlFor="contactNumber" className="form-label">Contact Number</label>
          </div>
          <div className="col-md-9">
            <input
              type="text"
              className="form-control"
              id="contactNumber"
              name="contactNumber"
              value={state.contactNumber === null ? '' : state.contactNumber}
              onChange={handleChange}
            />
          </div>
        </div>
        <div className="row mb-3">
          <div className="col-md-3 text-end">
            <label htmlFor="gender" className="form-label">Gender</label>
          </div>
          <div className="col-md-9">
            <select
              id="gender"
              name="gender"
              className="form-select"
              value={state.gender === null ? '' : state.gender}
              onChange={handleChange}
            >
              <option value="">Select Gender</option>
              <option value="Male">Male</option>
              <option value="Female">Female</option>
              <option value="Other">Other</option>
            </select>
          </div>
        </div>

        <div className="row mb-3">
          <div className="col-md-3 text-end">
            <label htmlFor="plan" className="form-label">Plan</label>
          </div>
          <div className="col-md-9">
            <select
              id="plan"
              name="plan"
              className="form-select"
              value={state.plan === null ? '' : state.plan}
              onChange={handleChange}
            >
              <option value="">Select Plan</option>
              <option value="REGULAR">Regular</option>
              <option value="BASIC">Basic</option>
              <option value="PRO">Pro</option>
              <option value="ADVANCE">Advance</option>
            </select>
          </div>
        </div>
        <button type="submit" className='btn btn-primary'>Update Profile</button>
      </form>
    </div>
  );  
};

export default UpdateProfileDetails;
