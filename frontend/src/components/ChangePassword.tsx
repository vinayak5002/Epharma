import { LogIn, LogOut, X } from "lucide-react";
import React, { useContext } from "react";
import axios from "axios";
import Login from "./Login";
import { logOutUser } from "../data/data";
import { CustContext } from "../state/ContextProvide";
interface Userpassword {
  customerId: number;
  oldPassword: string;
  newPassword: string;
  confirmPassword: string;
}

const ChangePassword = ({ setShowUpdatePassword, custId }: any) => {
  const { setCustomer, setCustomerName, setNumCartItems } = useContext(CustContext);

  const [state, setstate] = React.useState({} as Userpassword);

  const handleChange = (e: any) => {
    const { name, value } = e.target;
    setstate((prevState: any) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const logOutUser = () => {
      setCustomer(-1);
      setCustomerName("");
      setNumCartItems(0);
      window.location.href = "/login";
  }

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      console.log("hello change passworddddddddddd");
      console.log(state);
      state.customerId=custId;
      const response = await axios.put(
        "http://localhost:5002/customer-api/customer/change-password",
        state
      );
      alert("password changed successfully");
      setShowUpdatePassword(false);
      
      logOutUser();
      console.log(response.data);
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <div className="card">
      <div className="d-flex justify-content-end">
        <X onClick={() => setShowUpdatePassword(false)} />
       </div>
       <div className="card-body">
        <form action="" onSubmit={handleSubmit}>
          <div>
            <label htmlFor="htmlForChangePasword" className="form-label">
              Enter the old password
            </label>
            <input
              type="text"
              className="form-control"
              id="oldPassword"
              name="oldPassword"
              value={state.oldPassword}
              onChange={handleChange}
            />
          </div>

          <div>
            <label htmlFor="htmlForChangePasword" className="form-label">
              Enter the new password
            </label>
            <input
              type="text"
              className="form-control"
              id="newPassword"
              name="newPassword"
              value={state.newPassword}
              onChange={handleChange}
            />
          </div>
      <button onSubmit={handleSubmit} className="btb btn-primary">
        update password
      </button>
        </form>
      </div>
      
    </div>
  );
};

export default ChangePassword;
