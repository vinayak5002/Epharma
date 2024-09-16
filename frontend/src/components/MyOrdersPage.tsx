import { useContext, useEffect, useState } from "react";
import { OrderURl } from "../constants/constants";
import { CustContext } from "../state/ContextProvide";
import axios from "axios";

// create types for this order data
interface Order {
    orderId: number;
    customerId: number;
    addressId: number;
    cardId: number;
    netPrice: number;
    medicineDiscount: number;
    memberDiscount: number;
    orderDate: string;
    orderStatus: string;
    deliveryDate: string;
    deliveryStatus: string;
    orderItems: OrderItem[];
    cancleReason: string;
}

interface OrderItem {
    orderId: number;
    medicineId: number;
    quantity: number;
}

const MyOrdersPage = () => {
    const {customerId, setCustomer} = useContext(CustContext);

    const [orders, setOrders] = useState<Order[]>([]);

    const fetchOrders = async (orderList: number[]) => {
        let newOrders: Order[] = [];

        for (let i = 0; i < orderList.length; i++) {
            const URL: string = `${OrderURl}/order/${orderList[i]}`;

            try {
                const response = await axios.get(URL);
                console.log(response.data);

                const data: Order = response.data;

                newOrders.push(data);
            }
            catch (error) {
                console.log(error);
            }
        }

        //reverse the order list to show the latest order first
        newOrders.reverse();

        setOrders(newOrders);
        console.log(newOrders);
    }

    const fetchData = async () => {
        // Fetch data from the server
        const URL: string = `${OrderURl}/order/customer/${customerId}`;

        try {
            const response = await axios.get(URL);
            console.log(response.data);

            const data = response.data;

            fetchOrders(data);
        }
        catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        fetchData();
    }, []);

    return (
        <div className="container my-4">
            <h1 className="text-center mb-4">My Orders</h1>
            <div className="row">
                {orders.map((order) => (
                    <div className="col-md-4 mb-4" key={order.orderId}>
                        <div className="card border-primary shadow-sm">
                            <div className="card-body">
                                <h5 className="card-title mb-3">Order #{order.orderId}</h5>
                                <p className="card-text"><strong>Order Date:</strong> {new Date(order.orderDate).toLocaleDateString()}</p>
                                <p className="card-text"><strong>Delivery Date:</strong> {new Date(order.deliveryDate).toLocaleDateString()}</p>
                                <p className="card-text"><strong>Net Price:</strong> Rs. {order.netPrice.toFixed(2)}</p>
                                <p className="card-text"><strong>Order Status:</strong> {order.orderStatus}</p>
                                <p className="card-text"><strong>Delivery Status:</strong> {order.deliveryStatus}</p>
                                <p className="card-text"><strong>Medicine Discount:</strong> Rs. {order.medicineDiscount.toFixed(2)}</p>
                                <p className="card-text"><strong>Member Discount:</strong> Rs. {order.memberDiscount.toFixed(2)}</p>
                                {/* Show cancel reason only when it's there */}
                                {order.cancleReason && (
                                    <p className="card-text text-danger"><strong>Cancel Reason:</strong> {order.cancleReason}</p>
                                )}
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );


}

export default MyOrdersPage;