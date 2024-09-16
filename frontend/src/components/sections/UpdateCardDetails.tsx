import React, { useState } from 'react';

const UpdateCardDetails: React.FC = () => {
  const [cardDetails, setCardDetails] = useState({
    cardNumber: '',
    nameOnCard: '',
    cvv: '',
    expiryDate: ''
  });

  const [errors, setErrors] = useState({
    cardNumber: '',
    nameOnCard: '',
    cvv: '',
    expiryDate: ''
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setCardDetails({
      ...cardDetails,
      [name]: value
    });
  };

  const validateForm = () => {
    let valid = true;
    const newErrors: any = {};

    // Validate card number
    if (!cardDetails.cardNumber) {
      newErrors.cardNumber = 'Card number is required';
      valid = false;
    }

    // Validate name on card
    if (!cardDetails.nameOnCard) {
      newErrors.nameOnCard = 'Name on card is required';
      valid = false;
    }

    // Validate CVV
    if (!cardDetails.cvv || cardDetails.cvv.length !== 3) {
      newErrors.cvv = 'CVV is required and must be 3 digits';
      valid = false;
    }

    // Validate expiry date
    if (!cardDetails.expiryDate) {
      newErrors.expiryDate = 'Expiry date is required';
      valid = false;
    }

    setErrors(newErrors);
    return valid;
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (!validateForm()) return;

    // Handle form submission
    console.log('Card details updated:', cardDetails);
  };

  return (
    <div className="container mt-4">
      <h2 className="mb-4">Update Card Details</h2>
      <form onSubmit={handleSubmit}>
        <div className="row mb-3">
          <div className="col-md-3 text-end">
            <label htmlFor="cardNumber" className="form-label">Card Number</label>
          </div>
          <div className="col-md-9">
            <input
              type="text"
              className="form-control"
              id="cardNumber"
              name="cardNumber"
              value={cardDetails.cardNumber}
              onChange={handleChange}
              placeholder="Enter card number"
            />
            {errors.cardNumber && <div className="text-danger">{errors.cardNumber}</div>}
          </div>
        </div>
        <div className="row mb-3">
          <div className="col-md-3 text-end">
            <label htmlFor="nameOnCard" className="form-label">Name on Card</label>
          </div>
          <div className="col-md-9">
            <input
              type="text"
              className="form-control"
              id="nameOnCard"
              name="nameOnCard"
              value={cardDetails.nameOnCard}
              onChange={handleChange}
              placeholder="Enter name on card"
            />
            {errors.nameOnCard && <div className="text-danger">{errors.nameOnCard}</div>}
          </div>
        </div>
        <div className="row mb-3">
          <div className="col-md-3 text-end">
            <label htmlFor="cvv" className="form-label">CVV</label>
          </div>
          <div className="col-md-9">
            <input
              type="password"
              className="form-control"
              id="cvv"
              name="cvv"
              value={cardDetails.cvv}
              onChange={handleChange}
              placeholder="Enter CVV"
              maxLength={3}
            />
            {errors.cvv && <div className="text-danger">{errors.cvv}</div>}
          </div>
        </div>
        <div className="row mb-3">
          <div className="col-md-3 text-end">
            <label htmlFor="expiryDate" className="form-label">Expiry Date</label>
          </div>
          <div className="col-md-9">
            <input
              type="date"
              className="form-control"
              id="expiryDate"
              name="expiryDate"
              value={cardDetails.expiryDate}
              onChange={handleChange}
            />
            {errors.expiryDate && <div className="text-danger">{errors.expiryDate}</div>}
          </div>
        </div>
        <button type="submit" className="btn btn-primary">Update Card</button>
      </form>
    </div>
  );
};

export default UpdateCardDetails;
