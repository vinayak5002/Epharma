import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { MedicineURL, CustomerURL, CardURL, OrderURl } from "../constants/constants";
import { CustContext } from "../state/ContextProvide";
import { ToastContainer, toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

import { MedicineData, CartData, CustomerData, CardData, AddressData, OrderPrice } from '../types/types';



export const PlaceOrderPage = () => {

    const { customerId, setCustomer } = useContext(CustContext);
    const navigator = useNavigate();

    const [medicineState, setMedicineState] = useState<MedicineData[]>();

    const [customer, setCustomerState] = useState<CustomerData>();

    const [cardList, setCardList] = useState<CardData[]>();
    const [card, setCard] = useState<CardData>();

    const [addressList, setAddressList] = useState<AddressData[]>();
    const [address, setAddress] = useState<AddressData>();

    const [orderDetails, setOrderDetails] = useState<OrderPrice>();

    const GetCartURL: string = `http://localhost:5000/cart-api/medicines/customer/${customerId}`;

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
            setMedicineState(medicineList);

            console.log("hello get medicine acc to cart");

            computeOrder(medicineList);
        } catch (err) {
            console.log(err);
        }
    }

    const fetchCardDetails = async (): Promise<CardData[]> => {
        const URL: string = `${CardURL}/cards/customer/${customerId}`;

        try {
            const response = await axios.get(URL);
            console.log("Card details");
            console.log(response);

            setCardList(response.data as CardData[]);

            // Assuming response.data is of type CardData[]
            return response.data as CardData[];
        } catch (e) {
            console.log(e);
            return []; // Return an empty array in case of error
        }
    };

    const fetchCustomerDetails = async (medicineList: MedicineData[]): Promise<CustomerData | undefined> => {
        const URL = `${CustomerURL}/customer/${customerId}`;

        try {
            const response = await axios.get(URL);
            console.log("Getting customer details");
            console.log(response);

            const data = response.data;

            console.log("Address list");
            console.log(data.addressList);

            const customer: CustomerData = {
                customerId: data.customerId,
                customerName: data.customerName,
                addressId: data.addressList[0].addressId,
                cardId: undefined,
                plan: data.plan,
                healthCoins: data.healthCoins,
                cartList: medicineList,
            };

            try {
                const cardListData = await fetchCardDetails();
                if (cardListData.length > 0) {
                    setCard(cardListData[0]);
                    customer.cardId = cardListData[0].cardId;
                } else {
                    console.log('No card data available');
                }
            } catch (err) {
                console.log(err);
            }

            setAddress(data.addressList[0]);
            setAddressList(data.addressList);

            setCustomerState(customer);
            return customer;
        } catch (err) {
            console.log(err);
            return undefined; // Return undefined in case of error
        }
    };


    const computeOrder = async (medicineList: MedicineData[]) => {


        const customerData = await fetchCustomerDetails(medicineList)

        let totalPrice = 0;

        medicineList.forEach(e => {
            totalPrice += e.price * e.quantity
        });

        const medicineDiscount = medicineList.map(e => e.price * (e.discountPercent / 100)).reduce((a, b) => a + b);

        const memberDiscount = customerData?.healthCoins ?? 0;

        const netPrice = totalPrice - medicineDiscount - memberDiscount;

        const orderItems = medicineList.map(e => ({ medicineId: e.medicineId, quantity: e.quantity }));

        const params = {
            customerId: (customerData)?.customerId,
            addressId: address?.addressId,
            cardId: card?.cardId,
            netPrice: netPrice,
            medicineDiscount: medicineDiscount,
            memberDiscount: memberDiscount,
            orderDate: Date.now(),
            orderItems: orderItems
        }

        setOrderDetails({
            totalPrice: totalPrice,
            medicineDiscount: medicineDiscount,
            memberDiscount: memberDiscount,
            grandTotal: netPrice
        });
        console.log("Computing order");

        console.log(params);
    }

    const makePayment = async () => {
        const URL = `${OrderURl}/order/place-order`;

        const dateObject = new Date(Date.now());

        const formattedDate = dateObject.toISOString().split('.')[0]; // Removes milliseconds        

        const param = {
            customerId: customer?.customerId,
            addressId: customer?.addressId,
            cardId: customer?.cardId,
            netPrice: orderDetails?.grandTotal,
            medicineDiscount: orderDetails?.medicineDiscount,
            memberDiscount: orderDetails?.memberDiscount,
            orderDate: formattedDate,
            orderItems: customer?.cartList?.map(e => ({ medicineId: e.medicineId, quantity: e.quantity }))
        }

        console.log("param", param);

        try {
            const response = await axios.post(URL, param);
            toast.success("Order placed successfully");
            console.log(response);
            // disable the make payment button, wait for 3 seconds, redirect to my-orders page
            document.getElementById("payment-button")?.setAttribute("disabled", "true");
            setTimeout(() => {
                navigator('/my-orders');
            }, 3000);
        }
        catch (err) {
            toast.error("Order failed");
            console.log(err);
        }
    }

    const handleAddressChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        const addressId = parseInt(e.target.value);
        const selectedAddress = addressList?.find(a => a.addressId === addressId);
        setAddress(selectedAddress);
    }

    const handleCardChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        const cardId = parseInt(e.target.value);
        const selectedCard = cardList?.find(c => c.cardId === cardId);
        setCard(selectedCard);
    }

    useEffect(() => {
        fetchData();

    }, []);

    return (
        <>
            <ToastContainer />
            <div>
                <h1 className="text-center mb-4">Medicine List</h1>
                <div className="container">
                    <div className="row">
                        {medicineState && medicineState.length > 0 ? (
                            medicineState.map((item) => (
                                <div className="col-md-4 mb-4" key={item.medicineId}>
                                    <div className="card border-primary shadow-sm">
                                        <div className="card-body">
                                            <h5 className="card-title">{item.medicineName}</h5>
                                            <p className="card-text"><strong>Price per unit :</strong> {item.price}</p>
                                            <p className="card-text"><strong>Manufacturer:</strong> {item.manufacturer}</p>
                                            <p className="card-text"><strong>Quantity:</strong> {item.quantity}</p>
                                            <div className="d-flex justify-content-between">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            ))
                        ) : (
                            <p className="text-center">No items in the cart.</p>
                        )}
                    </div>
                    <h2>Order Total</h2>
                    <div>
                        <h4>Total Price: {orderDetails?.totalPrice}</h4>
                        <h4>Medicine Discount: {orderDetails?.medicineDiscount}</h4>
                        <h4>Member Discount: {orderDetails?.memberDiscount}</h4>
                        <h3>Grand total: {orderDetails?.grandTotal}</h3>
                    </div>
                    <br />
                    <h2>Delivering to Address:</h2>
                    <div className="form-group mb-4">
                        <label htmlFor="addressSelect">Select Address:</label>
                        <select
                            id="addressSelect"
                            className="form-control"
                            onChange={handleAddressChange}
                            value={address?.addressId}
                        >
                            {(addressList ?? []).map(address => (
                                <option key={address.addressId} value={address.addressId}>
                                    {address.addressName} - {address.city}, {address.state}
                                </option>
                            ))}
                        </select>
                    </div>
                    {address && (
                        <div className="col-md-4 mb-4">
                            <div className="card border-primary shadow-sm">
                                <div className="card-body">
                                    <h5 className="card-title">{address.addressName}</h5>
                                    <p className="card-text"><strong>Address Line 1:</strong> {address.addressLine1}</p>
                                    <p className="card-text"><strong>Address Line 2:</strong> {address.addressLine2}</p>
                                    <p className="card-text"><strong>Address Line 3:</strong> {address.area}, {address.city}, {address.state}, {address.pincode}</p>
                                </div>
                            </div>
                        </div>
                    )}

                    <h2>Paying with Card:</h2>
                    <div className="form-group mb-4">
                        <label htmlFor="addressSelect">Select Card:</label>
                        <select
                            id="cartSelect"
                            className="form-control"
                            onChange={handleCardChange}
                            value={card?.cardId}
                        >
                            {(cardList ?? []).map(card => (
                                <option key={card.cardId} value={card.cardId}>
                                    {card.cardType} - {card.nameOnCard}, {card.expiryDate}
                                </option>
                            ))}
                        </select>
                    </div>
                    {card ? (
                        <div className="col-md-4 mb-4">
                            <div className="card border-primary shadow-sm">
                                <div className="card-body">
                                    <h5 className="card-title">{card.nameOnCard}</h5>
                                    <p className="card-text"><strong>Type:</strong> {card.cardType}</p>
                                    <p className="card-text"><strong>Expiry Date:</strong> {card.expiryDate}</p>
                                </div>
                            </div>
                        </div>
                    ) : (
                        <p className="text-center">No card details available.</p>
                    )}

                </div>
                <button id="payment-button" onClick={makePayment}>Make payment</button>
            </div>
        </>
    );
}