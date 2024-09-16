// types.ts

export interface MedicineData {
    cartId: number;
    medicineId: number;
    medicineName: string;
    manufacturer: string;
    price: number;
    category: string;
    manufacturingDate: string;
    expiryDate: string;
    discountPercent: number;
    quantity: number;
}

export interface CartData {
    cartId: number;
    customerId: number;
    medicineId: number;
    quantity: number;
}

export interface CustomerData {
    customerId: number;
    customerName: string;
    addressId: number;
    cardId: number | undefined;
    plan: string;
    healthCoins: number;
    cartList: MedicineData[] | undefined;
}

export interface AddressFormData {
    addressName: string;
    addressLine1: string;
    addressLine2: string;
    area: string;
    city: string;
    state: string;
    pincode: number;
}

export interface CustomerAddress {
    addressId: Number;
    addressName: string;
    addressLine1: string;
    addressLine2: string;
    area: string;
    city: string;
    state: string;
    pincode: string;
}

export interface CardData {
    cardId?: number;
    nameOnCard: string;
    cardType: string;
    cvv: number;
    expiryDate: string;
}

export interface AddressData {
    addressId: number;
    addressName: string;
    addressLine1: string;
    addressLine2: string;
    area: string;
    city: string;
    state: string;
    pincode: string;
}

export interface OrderPrice {
    totalPrice: number;
    medicineDiscount: number;
    memberDiscount: number;
    grandTotal: number;
}
