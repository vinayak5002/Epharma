import axios from "axios";
import { AddressFormData, CartData, CustomerData } from "../types/types";
import { CartURL, CustomerURL } from "../constants/constants";
import { useContext } from "react";
import { CustContext, CustContextType } from "../state/ContextProvide";


export const fetchCustomerData = async (customerId: number): Promise<CustomerData> => {
    try {
        const response = await axios.get(`${CustomerURL}/customer/${customerId}`);
        return response.data;
    }
    catch (error) {
        throw new Error(String(error));
    }
}

export const fetchCartData = async (customerId: number): Promise<CartData[]> => {
    try {
        const response = await axios.get(`${CartURL}/medicines/customer/${customerId}`);
        return response.data;
    }
    catch (error) {
        throw new Error(String(error));
    }
}

export const postCustomerAddress = async (customerId: number, address: AddressFormData) => {
    const URL = `${CustomerURL}/customer/add-address/${customerId}`;

    try {
        const addressParams = {...address, customerId: customerId};

        console.log("POST URL: ", URL);
        console.log("Address: ", addressParams);

        const response = await axios.post(URL, addressParams);
        return response.status;
    }
    catch (error) {
        throw new Error(String(error));
    }
}

export const logOutUser = (context: CustContextType) => {
    const {setCustomer, setCustomerName, setNumCartItems} = context;

    setCustomer(-1);
    setCustomerName("");
    setNumCartItems(0);
    window.location.href = "/login";
}