import axios from "axios";
import React, { ChangeEvent, useState } from "react";
import { validation } from "../validator/validation";

let url: string = "http://localhost:5002/customer-api/register";

interface UserData {
  customerName: string;
  customerEmailId: string;
  contactNumber: number | undefined;
  gender: string;
  dateOfBirth: Date | undefined;
  password: string;
}

const SignUp = () => {
  const [state, setState] = useState<UserData>({
    customerName:"",
    customerEmailId:"",
    contactNumber:undefined,
    gender:"",
    dateOfBirth: new Date(),
    password:""
  });
  
  const preferencesList: Array<string> = ["male", "female", "other"];

  interface FormErrorState {
    customerNameError: string;
    customerEmailIdError: string;
    contactNumberError: string;
    genderError: string;
    dateOfBirthError: string;
    passwordError: string;
  }

  const [formErrors, setFormErrors] = useState<FormErrorState>({
    customerNameError: "",
    customerEmailIdError: "",
    contactNumberError: "",
    genderError: "",
    dateOfBirthError: "",
    passwordError: "",
  });
  const [mandatory, setMandatory] = useState(false);
  const [successMessage, setSuccessMessage] = useState("");
  const [valid, setValid] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  type Messages = {
    NAME_ERROR: string;
    EMAIL_ERROR: string;
    GENDER_ERROR: string;
    DATE_OF_BIRTH_ERROR: string;
    MOBILE_NUMBER_ERROR: string;
    PASSWORD_ERROR: string;
    ERROR: string;
    MANDATORY: string;
  };

  const messages: Messages = {
    NAME_ERROR: "Name should contain alphabets and a single space between words",
    EMAIL_ERROR: "Email id should be in valid format (example@abc.com or example@abc.in)",
    GENDER_ERROR: "Select your gender",
    DATE_OF_BIRTH_ERROR: "Date of Birth is required",
    MOBILE_NUMBER_ERROR: "Mobile should contain 10 digits starting with 6, 7, 8, or 9",
    PASSWORD_ERROR: "Must have at least 1 lowercase and 1 uppercase character",
    ERROR: "Please run the backend",
    MANDATORY: "Enter all the form fields",
  };

  const handleChange = (event: ChangeEvent<HTMLInputElement | HTMLSelectElement>): void => {
    const { name, value, type } = event.target;
    if (type === "date") {
      setState(prevState => ({
        ...prevState,
        [name]: value ? new Date(value) : undefined
      }));
    } else {
      setState(prevState => ({
        ...prevState,
        [name]: type === "number" ? Number(value) : value
      }));
    }
    validateField(name, value);
  };

  const validateField = (name: string, value: any) => {
    let errors = formErrors;
    switch (name) {
      case "customerName":
        if (!validation.validateFullName(value)) {
          setFormErrors(prevState => ({ ...prevState, customerNameError: messages.NAME_ERROR }));
        } else {
          setFormErrors(prevState => ({ ...prevState, customerNameError: "" }));
        }
        break;
      case "customerEmailId":
        if (!validation.validateEmail(value)) {
          setFormErrors(prevState => ({ ...prevState, customerEmailIdError: messages.EMAIL_ERROR }));
        } else {
          setFormErrors(prevState => ({ ...prevState, customerEmailIdError: "" }));
        }
        break;
      // Add additional validations as needed
    }
    if (!formErrors.customerNameError && !formErrors.customerEmailIdError) {
      setValid(true);
    } else {
      setValid(false);
    }
  };

  const handleSubmit = (e: React.FormEvent): void => {
    e.preventDefault();
    if (
      state.customerName === "" ||
      state.customerEmailId === "" ||
      state.contactNumber === undefined ||
      state.gender === "" ||
      state.password === ""
    ) {
      setMandatory(true);
      setSuccessMessage("");
      console.log("Some of the fields are empty");
    } else {
      setMandatory(false);
      axios
        .post(url, state)
        .then((response) => {
          setState({
            customerName: "",
            customerEmailId: "",
            contactNumber: undefined,
            gender: "",
            dateOfBirth: undefined,
            password: "",
          });
          setSuccessMessage("Customer registered successfully");
          console.log("Registered");
        })
        .catch((err) => {
          console.log(err);
          setErrorMessage("Backend is down");
        });
    }
  };

  return (
    <div className="" style={{ padding: "100px" }}>
      <h1>Provide your details</h1>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="customerName">Enter your Name</label>
          <input
            type="text"
            className="form-control"
            name="customerName"
            value={state.customerName}
            onChange={handleChange}
            placeholder="Enter your Name"
          />
          {formErrors.customerNameError && <div className="btn btn-danger">{formErrors.customerNameError}</div>}
        </div>

        <div className="form-group">
          <label htmlFor="customerEmailId">Enter your Email</label>
          <input
            type="email"
            className="form-control"
            name="customerEmailId"
            value={state.customerEmailId}
            onChange={handleChange}
            placeholder="Enter your email"
          />
          {formErrors.customerEmailIdError && <div className="btn btn-danger">{formErrors.customerEmailIdError}</div>}
        </div>

        <div className="form-group">
          <label htmlFor="contactNumber">Enter your Mobile Number</label>
          <input
            type="tel"
            className="form-control"
            name="contactNumber"
            value={state.contactNumber === undefined ? "" : state.contactNumber}
            onChange={handleChange}
            placeholder="Enter your Mobile Number"
          />
        </div>

        <div className="form-group">
          <label htmlFor="gender">Gender</label>
          <select
            name="gender"
            className="form-control"
            value={state.gender}
            onChange={handleChange}
          >
            <option value="">--Select Your Gender--</option>
            {preferencesList.map((ele, idx) => (
              <option value={ele} key={idx}>
                {ele}
              </option>
            ))}
          </select>
        </div>

        <div className="form-group">
          <label htmlFor="dateOfBirth">Date of Birth</label>
          <input
            type="date"
            className="form-control"
            name="dateOfBirth"
            value={state.dateOfBirth ? state.dateOfBirth.toISOString().split('T')[0] : ""}
            onChange={handleChange}
            placeholder="dd/mm/yyyy"
          />
        </div>

        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input
            type="password"
            className="form-control"
            name="password"
            value={state.password}
            onChange={handleChange}
            placeholder="Enter password"
          />
        </div>

        <button className="btn btn-success" type="submit">Submit</button>
        {mandatory && <span className="text-danger">{messages.MANDATORY}</span>}
        {errorMessage && <span className="text-danger">{errorMessage}</span>}
        {successMessage && <span className="text-success">{successMessage}</span>}
      </form>
    </div>
  );
};

export default SignUp;
