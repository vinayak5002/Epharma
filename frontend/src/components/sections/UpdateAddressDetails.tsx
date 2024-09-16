import React, { useContext, useState } from 'react';
import { toast } from 'react-toastify';
import { AddressFormData } from '../../types/types';
import { postCustomerAddress } from '../../data/data';
import { CustContext } from '../../state/ContextProvide';

const UpdateAddressDetails: React.FC = () => {
  const { customerId } = useContext(CustContext);

  const [address, setAddress] = useState<AddressFormData>({
    addressName: '',
    addressLine1: '',
    addressLine2: '',
    area: '',
    city: '',
    state: '',
    pincode: 0
  });

  const [errors, setErrors] = useState({
    addressName: '',
    addressLine1: '',
    area: '',
    city: '',
    state: '',
    pincode: ''
  });

  const validateForm = () => {
    let valid = true;
    const newErrors: any = {};

    // Check if required fields are not empty
    if (!address.addressName) {
      newErrors.addressName = 'Address Name is required';
      valid = false;
    }
    if (!address.addressLine1) {
      newErrors.addressLine1 = 'Address Line 1 is required';
      valid = false;
    }
    if (!address.area) {
      newErrors.area = 'Area is required';
      valid = false;
    }
    if (!address.city) {
      newErrors.city = 'City is required';
      valid = false;
    }
    if (!address.state) {
      newErrors.state = 'State is required';
      valid = false;
    }
    if (!address.pincode || address.pincode <= 0) {
      newErrors.pincode = 'Pincode is required and must be greater than 0';
      valid = false;
    }

    setErrors(newErrors);
    return valid;
  };

  const onChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setAddress({ ...address, [name]: value });
  };

  const onSubmitHandler = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!validateForm()) return;

    try {
      await postCustomerAddress(customerId, address);
      toast.success("Address updated successfully");
      setAddress({
        addressName: '',
        addressLine1: '',
        addressLine2: '',
        area: '',
        city: '',
        state: '',
        pincode: 0
      });
      setErrors({
        addressName: '',
        addressLine1: '',
        area: '',
        city: '',
        state: '',
        pincode: ''
      });
    } catch (error) {
      toast.error("Error updating address");
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="mb-4">Update Address</h2>
      <form onSubmit={onSubmitHandler}>
        <div className="row mb-3">
          <div className="col-md-3 text-end">
            <label htmlFor="addressName" className="form-label">Address Name</label>
          </div>
          <div className="col-md-9">
            <input
              type="text"
              className="form-control"
              id="addressName"
              name="addressName"
              value={address.addressName}
              onChange={onChangeHandler}
              placeholder="Enter address name"
            />
            {errors.addressName && <div className="text-danger">{errors.addressName}</div>}
          </div>
        </div>
        <div className="row mb-3">
          <div className="col-md-3 text-end">
            <label htmlFor="addressLine1" className="form-label">Address Line 1</label>
          </div>
          <div className="col-md-9">
            <input
              type="text"
              className="form-control"
              id="addressLine1"
              name="addressLine1"
              value={address.addressLine1}
              onChange={onChangeHandler}
              placeholder="Enter address line 1"
            />
            {errors.addressLine1 && <div className="text-danger">{errors.addressLine1}</div>}
          </div>
        </div>
        <div className="row mb-3">
          <div className="col-md-3 text-end">
            <label htmlFor="addressLine2" className="form-label">Address Line 2</label>
          </div>
          <div className="col-md-9">
            <input
              type="text"
              className="form-control"
              id="addressLine2"
              name="addressLine2"
              value={address.addressLine2}
              onChange={onChangeHandler}
              placeholder="Enter address line 2 (optional)"
            />
          </div>
        </div>
        <div className="row mb-3">
          <div className="col-md-3 text-end">
            <label htmlFor="area" className="form-label">Area</label>
          </div>
          <div className="col-md-9">
            <input
              type="text"
              className="form-control"
              id="area"
              name="area"
              value={address.area}
              onChange={onChangeHandler}
              placeholder="Enter area"
            />
            {errors.area && <div className="text-danger">{errors.area}</div>}
          </div>
        </div>
        <div className="row mb-3">
          <div className="col-md-3 text-end">
            <label htmlFor="city" className="form-label">City</label>
          </div>
          <div className="col-md-9">
            <input
              type="text"
              className="form-control"
              id="city"
              name="city"
              value={address.city}
              onChange={onChangeHandler}
              placeholder="Enter city"
            />
            {errors.city && <div className="text-danger">{errors.city}</div>}
          </div>
        </div>
        <div className="row mb-3">
          <div className="col-md-3 text-end">
            <label htmlFor="state" className="form-label">State</label>
          </div>
          <div className="col-md-9">
            <input
              type="text"
              className="form-control"
              id="state"
              name="state"
              value={address.state}
              onChange={onChangeHandler}
              placeholder="Enter state"
            />
            {errors.state && <div className="text-danger">{errors.state}</div>}
          </div>
        </div>
        <div className="row mb-3">
          <div className="col-md-3 text-end">
            <label htmlFor="pincode" className="form-label">Pincode</label>
          </div>
          <div className="col-md-9">
            <input
              type="text"
              className="form-control"
              id="pincode"
              name="pincode"
              value={address.pincode === 0 ? '' : address.pincode}
              onChange={onChangeHandler}
              placeholder="Enter pincode"
            />
            {errors.pincode && <div className="text-danger">{errors.pincode}</div>}
          </div>
        </div>
        <button type="submit" className="btn btn-primary">Update Address</button>
      </form>
    </div>
  );
};

export default UpdateAddressDetails;
