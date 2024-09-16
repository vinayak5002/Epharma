import React, { ReactNode, useState } from "react";

interface CustContextType {
    customerId: number;
    customerName: string;
    numCartItems: number;
    setCustomer: (id: number) => void;
    setCustomerName: (name: string) => void;
    setNumCartItems: (items: number) => void;
    setNumCartItemsAddOne: () => void;
}

const CustContext = React.createContext<CustContextType>({
    customerId: -1,
    setCustomer: () => { },
    customerName: "",
    setCustomerName: () => { },
    numCartItems: 0,
    setNumCartItems: () => { },
    setNumCartItemsAddOne: () => { }
});

interface CustProviderProps {
    children: ReactNode;
}

const CustProvider = ({ children }: CustProviderProps) => {
    const [customerId, setCustomerId] = useState<number>(() => {
        const savedCustomerId = localStorage.getItem("customerId")
        return savedCustomerId ? parseInt(savedCustomerId, 10) : -1;
    });

    const [customerName, setCustomerNameState] = useState<string>(() => {
        const savedCustomerName = localStorage.getItem("customerName")
        return savedCustomerName ? savedCustomerName : "";
    });

    const [numCartItems, setNumCartItemsState] = useState<number>(() => {
        const savedNumCartItems = localStorage.getItem("numCartItems")
        return savedNumCartItems ? parseInt(savedNumCartItems, 10) : 0;
    });

    const setCustomer = (id: number) => {
        setCustomerId(id);
        localStorage.setItem("customerId", id.toString());
    };

    const setCustomerName = (name: string) => {
        setCustomerNameState(name);
        localStorage.setItem("customerName", name);
    };

    const setNumCartItems = (items: number) => {
        setNumCartItemsState(items);
        localStorage.setItem("numCartItems", items.toString());
    };

    const setNumCartItemsAddOne = () => {
        setNumCartItemsState(numCartItems + 1);
        localStorage.setItem("numCartItems", (numCartItems + 1).toString());
    }

    return (
        <CustContext.Provider value={{ customerId, setCustomer, customerName, setCustomerName, numCartItems, setNumCartItems, setNumCartItemsAddOne }}>
            {children}
        </CustContext.Provider>
    );

};

export { CustContext, CustProvider };
export type { CustContextType };
