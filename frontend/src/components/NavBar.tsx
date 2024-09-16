import { useContext, useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { ShoppingCart, User, BriefcaseMedical } from "lucide-react";
import { CustContext } from "../state/ContextProvide";
import { ToastContainer, toast } from 'react-toastify';

const NavBar = () => {
  const { customerId, setCustomer, customerName, numCartItems, setCustomerName, setNumCartItems  } = useContext(CustContext);
  const navigator = useNavigate();

  const handleLogOut = () => {
    setCustomer(-1);
    setCustomerName("");
    setNumCartItems(0);
    navigator("/login");
  }

  useEffect(() => {
    console.log("NavBar: customerId: ", customerId);
    console.log("NavBar: customerName: ", customerName);
    console.log("NavBar: numCartItems: ", numCartItems);
  }, []);

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-dark">
      <ToastContainer />
      <div className="container-fluid">
        <Link className="navbar-brand" to='/'>
          <BriefcaseMedical size={25} color="#ffffff" />
        </Link>

        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav me-auto w-100 mb-2 mb-lg-0 d-flex align-items-center justify-content-between">
            <div className="d-flex align-items-center">
              {customerId !== -1 && (
                <>
                  <li className="nav-item">
                    <Link className="nav-item nav-link" style={{ color: "white" }} to='/home'>Epharmacy</Link>
                  </li>
                  <li className="nav-item">
                    <Link className="nav-item nav-link" style={{ color: "white" }} to='/medicine'>Medicine</Link>
                  </li>
                  <li className="nav-item">
                    <Link className="nav-item nav-link" style={{ color: "white" }} to='/my-orders'>My orders</Link>
                  </li>
                </>
              )}
            </div>

            <div className="d-flex align-items-center">
              {customerId !== -1 ? (
                <>
                  <div className="d-flex align-items-center me-3">
                    <Link className="nav-item nav-link me-2" to='/addtocart'>
                      <ShoppingCart size={25} color="#ffffff" />
                        <span className="badge bg-primary ms-2">{numCartItems}</span>
                    </Link>
                  </div>

                  <div className="d-flex align-items-center me-3">
                    <span className="text-white me-2">Welcome, {customerName}</span>
                    <Link className="nav-item nav-link me-2" to='/view'>
                      <User size={25} color="#ffffff" />
                    </Link>
                  </div>

                  <li className="nav-item">
                    <Link className="nav-item nav-link" style={{ color: "white" }} onClick={handleLogOut} to='/login'>Log out</Link>
                  </li>
                </>
              ) : (
                <>
                  <li className="nav-item">
                    <Link className="nav-item nav-link" style={{ color: "white" }} to='/login'>Sign In</Link>
                  </li>
                  <li className="nav-item">
                    <Link className="nav-item nav-link" style={{ color: "white" }} to='/sign-up'>Sign Up</Link>
                  </li>
                </>
              )}
            </div>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default NavBar;
