import React, { useContext, useEffect, useState } from 'react'
import axios from 'axios';
import { CustContext } from '../state/ContextProvide';
import { CartURL, MedicineURL } from '../constants/constants';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from 'react-router-dom';
import { CartData, MedicineData } from '../types/types';


const CartPage = () => {

  const [medicineList, setMedicineListState] = useState(Array<MedicineData>);

  const [price, setPrice] = useState(0);

  const {customerId, setNumCartItems} = useContext(CustContext);
  const navigator = useNavigate();

  const GetCartURL: string = `http://localhost:5000/cart-api/medicines/customer/${customerId}`;

  const updateQuantity = async (cartId: number, quantity: number) => {
    if(quantity >= 1){
      await axios.put(`${CartURL}/update-quantity/cartItem/${cartId}/quantity/${quantity}`)
      .then((res) => console.log(res))
      .catch((err) => console.log(err));
  
      fetchData();
    }
  }

  const fetchMedicineData = async (medicineId: number, cartId: number, quantity: number) => {
    const medicine: MedicineData = (await axios.get(MedicineURL + medicineId)).data;
    medicine.cartId = cartId;
    medicine.quantity = quantity;
    return medicine
  }

  const fetchData = async () => {
    try {
      const response = await axios.get(GetCartURL)
      const data: Array<CartData> = response.data;

      const medicineList: MedicineData[] = await Promise.all(data.map(element => {
        return fetchMedicineData(element.medicineId, element.cartId, element.quantity);
      }));

      console.log(response.data);
      
      console.log(medicineList);
      setMedicineListState(medicineList);

      console.log("hello get medicine acc to cart");
    } catch (err) {
      console.log(err);
    }
  }

  const removeFromCart = async (cartId: number) => {
    const URL: string = `${CartURL}/delete-medicine/${cartId}`;

    try {
      const response = await axios.delete(URL);
      const data = response.data;

      toast.success('Deleted from cart', {
        position: 'bottom-right',
        autoClose: 3000, 
      });

      console.log(response);
    }
    catch (err) {
      toast.error('Some error occured', {
        position: 'bottom-right', 
        autoClose: 3000,
      });
      console.log(err);
    }
    fetchData();
  }

  const handleClearCart = async () => {
    const URL: string = `${CartURL}/clear-cart/${customerId}`;

    try {
      const response = await axios.delete(URL);
      const data = response.data;

      toast.success('Cart cleared', {
        position: 'bottom-right',
        autoClose: 3000, 
      });

      setMedicineListState([]);

      console.log(response);
    }
    catch (err) {
      toast.error('Some error occured', {
        position: 'bottom-right', 
        autoClose: 3000,
      });
      console.log(err);
    }
  }

  const handlePlaceOrder = () => {
    navigator("/place-order");
  }
  
  useEffect(() => {
    setNumCartItems(medicineList.length);

    let newPrice = 0;

    medicineList.forEach( e => {
      console.log(e.price,e.quantity)
      
      newPrice += e.price * e.quantity
    });


    setPrice(newPrice);

  }, [medicineList]);

  useEffect(() => {
    fetchData();
  }, []);

    return (
      <div>
      <ToastContainer />
      <h1 className="text-center mb-4">Medicine List</h1>
      <div className="container">
        {/* Order Total Card */}
        <div className="card mb-4 border-info">
          <div className="card-body">
            <div className='container' style={{width: '60%'}}>
              <div className='' style={{display: 'flex', flexDirection: 'row', justifyContent: 'space-between', width: '100%'}}>
                <div className='column'>
                  <h5 className="card-title">Order Total</h5>
                  <p className="card-text fs-4">Total Price: <strong>{price} Rs.</strong></p>
                </div>
                <button onClick={handleClearCart}>Clear cart</button>
              </div>
            </div>
          </div>
          <button onClick={handlePlaceOrder}>Place Order</button>
        </div>
  
        {/* Medicine List */}
        <div className="row">
          {medicineList && medicineList.length > 0 ? (
            medicineList.map((item) => (
              <div className="col-md-4 mb-4" key={item.medicineId}>
                <div className="card border-primary shadow-sm">
                  <div className="card-body">
                    <h5 className="card-title">{item.medicineName}</h5>
                    <p className="card-text"><strong>Price per unit :</strong> {item.price}</p>
                    <p className="card-text"><strong>Manufacturer:</strong> {item.manufacturer}</p>
                    <p className="card-text"><strong>Quantity:</strong> {item.quantity}</p>
                    <div className="d-flex justify-content-between">
                      <button 
                        className="btn btn-danger btn-sm"
                        onClick={() => updateQuantity(item.cartId, item.quantity - 1)}
                        disabled={item.quantity <= 1}
                      >
                        -
                      </button>
                      <button 
                        className="btn btn-primary btn-sm"
                        onClick={() => updateQuantity(item.cartId, item.quantity + 1)}
                      >
                        +
                      </button>
                      <button 
                        className="btn btn-danger btn-sm"
                        onClick={() => removeFromCart(item.cartId)}
                      >
                        Remove
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            ))
          ) : (
            <p className="text-center">No items in the cart.</p>
          )}
        </div>
      </div>
    </div>
  );
}

export default CartPage
