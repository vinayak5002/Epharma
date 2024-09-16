import React, { useContext, useState } from 'react'
import axios from 'axios';
import { useEffect } from 'react';
import { CustContext } from '../state/ContextProvide';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from 'react-router-dom';

interface MedicineData {
  medicineImg: string,
  medicineId: number;
  medicineName: string;
  manufacturer: string;
  category: string;
  manufacturingDate: Date;
  expiryDate: Date;
  discountPercent: number;
  quantity: number;
  price: number;
}

type PageState = {
  pageNo: number,
  pageSize: number,
  maxPage: number
}

enum NowShowing {
  ALL,
  SEARCH,
  CATEGORY
}

const Categories = [
  "Allopathy",
  "Veterinary",
  "Diabetes",
  "Covid Essentials",
  "Ayush",
  "Homeopathy",
  "Fitness",
  "Mom & Baby"
]

const PageZeroPageState = { pageNo: 0, pageSize: 6, maxPage: 1 };

const Medicine = () => {
  const { customerId, setNumCartItemsAddOne } = useContext(CustContext);
  const navigator = useNavigate();

  const [state, setState] = useState(Array<MedicineData>);

  const [query, setQuery] = useState("");

  const [category, setCategory] = useState("");

  const [pageState, setPageState] = useState<PageState>(PageZeroPageState)

  const [nowShowing, setNowShowing] = useState<NowShowing>(NowShowing.ALL);

  const changeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
    setQuery(event.target.value);
  }

  const submitHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();

    fetchQuery();
  }

  const categoryChangeHandeller = (event: React.ChangeEvent<HTMLSelectElement>) => {
    const { name, value } = event.target;

    fetchCategory(value);

    setCategory(value);
  }

  const handlePageChange = (newPageNo: number) => {
    if (newPageNo < 0 || newPageNo >= pageState.maxPage) return;

    const newPageState = { ...pageState, pageNo: newPageNo };

    if (nowShowing === NowShowing.ALL) {
      console.log("Showing all")
      fetchData(newPageState);
    }
    else if(nowShowing === NowShowing.SEARCH) {
      console.log("Showing search results")
      fetchQuery(newPageState);
    }
    else {
      console.log("Showing category medicines");
      fetchCategory(category, newPageState);
    }
  }

  const fetchQuery = async (newPageState = pageState) => {
    if (query === "") {
      setNowShowing(NowShowing.ALL)
      fetchData(PageZeroPageState);
      return;
    }

    if(nowShowing !== NowShowing.SEARCH) {
      newPageState = PageZeroPageState;
    }

    setNowShowing(NowShowing.SEARCH);
    // nowShowing = NowShowing.SEARCH;

    const FetchURL = "http://localhost:5000/medicine-api/search";
    const params = {
      keyword: query,
      pageNo: newPageState.pageNo,
      pageSize: 6
    }
    const response = await axios.get(FetchURL, { params });

    setState(response.data.medicines)
    setPageState({
      ...newPageState,
      pageNo: response.data.pageNo,
      maxPage: response.data.numPages
    })
    console.log(response);
  }

  const fetchData = async (newPageState = pageState) => {
    const FetchMedicineInPagesURL: string = "http://localhost:5000/medicine-api/all";

    try {
      const response = await axios.get(`${FetchMedicineInPagesURL}/pageNumber/${newPageState.pageNo}/pageSize/${newPageState.pageSize}`);
      const data = response.data;
      console.log(data)

      setPageState({
        ...newPageState,
        pageNo: data.pageNo,
        maxPage: data.numPages
      })

      setState(data.medicines);

      setNowShowing(NowShowing.ALL);
      // nowShowing = NowShowing.ALL;

    } catch (error) {
      console.log(error);
    }
  }

  const fetchCategory = async (category: string, newPageState = pageState) => {
    if (category === "") {
      fetchData(PageZeroPageState);
      return;
    }
    setQuery("");

    if(nowShowing !== NowShowing.CATEGORY) {
      newPageState = PageZeroPageState;
    }

    setNowShowing(NowShowing.CATEGORY);

    const FetchMedicineByCategoryURL = "http://localhost:5000/medicine-api/";
    const URL = `${FetchMedicineByCategoryURL}/category/${category}/pageNumber/${newPageState.pageNo}/pageSize/${newPageState.pageSize}`;

    const response = await axios.get(URL);
    const date = response.data;

    setState(response.data.medicines)
    setPageState({
      ...newPageState,
      pageNo: response.data.pageNo,
      maxPage: response.data.numPages
    })    

    console.log(response);
  }

  const addToCart = async (medicineId: number, customerId: number, quantity = 1) => {
    const AddToCartURL = "http://localhost:5000/cart-api";
    try {
      const response = await axios.post(`${AddToCartURL}/add-medicine/${medicineId}/customer/${customerId}/quantity/${quantity}`);
      toast.success('Medicine added to cart successfully!', {
        position: 'bottom-right', // Use string literals for positions
        autoClose: 3000, // Auto close after 3 seconds
      });
      console.log(response);

      setNumCartItemsAddOne();
    } catch (err) {
      toast.error('Medicine already present in cart', {
        position: 'bottom-right', // Use string literals for positions
        autoClose: 3000, // Auto close after 3 seconds
      });
      console.error(err);
    }
  };


  useEffect(() => {
    if(customerId === -1) {
      navigator("/login");
    }
    fetchData();
  }, []);
  return (
    <div>
      <ToastContainer />
      <h1 className="text-center mb-4">Medicines</h1>
      <div className="container">
        <div className="d-flex justify-content-between align-items-center mb-4">
          <div className="input-group">
            <form className='input-group'>
              <input
                className="form-control"
                type="search"
                placeholder="Search for medicines..."
                onChange={changeHandler}
                value={query}
                aria-label="Search"
              />
              <button className="btn btn-outline-success" onSubmit={submitHandler} onClick={submitHandler}>Search</button>
            </form>
          </div>
          <select
            className="form-select w-auto"
            name='category'
            onChange={categoryChangeHandeller}
          >
            <option value="">Select Category</option>
            {Categories.map((category, idx) => (
              <option key={idx} value={category}>{category}</option>
            ))}
          </select>
        </div>
        <div className="mb-4 text-center">
          <button className="btn btn-secondary me-2" onClick={() => handlePageChange(pageState.pageNo - 1)}>Prev</button>
          <span>Showing page {pageState.pageNo + 1} of {pageState.maxPage}</span>
          <button className="btn btn-secondary ms-2" onClick={() => handlePageChange(pageState.pageNo + 1)}>Next</button>
        </div>
        <div className="row">
          {state && state.length > 0 ? (
            state.map((medicine, index) => (
              <div className='col-md-4 mb-4' key={medicine.medicineId}>
                <div className='card border-light shadow-sm'>
                  <img                  
                    src={`m-${index%2+1}.jpg`}
                    alt={medicine.medicineName}
                    className='card-img-top'
                    style={{ objectFit: "cover", height: "200px" }}
                  />
                  <div className="card-body">
                    <h5 className="card-title">{medicine.medicineName}</h5>
                    <p className="card-text text-3xl font-bold undeline">{medicine.manufacturer}</p>
                    <div className='container-fluid'>
                      <p className="card-text text-end"><strong>{medicine.price.toFixed(2)} Rs.</strong></p>
                    </div>
                    <p className="card-text"><strong>Category:</strong> {medicine.category}</p>
                    <p className="card-text"><strong>Discount:</strong> <span className='text text-success' style={{fontWeight: 'bold'}}>{medicine.discountPercent}%</span></p>
                    <button
                      className="btn btn-success w-100"
                      onClick={() => { addToCart(medicine.medicineId, customerId) }}
                    >
                      Add to Cart
                    </button>
                  </div>
                </div>
              </div>
            ))
          ) : (
            <p className="text-center">No medicines available.</p>
          )}
        </div>
      </div>
    </div>
  );

};

export default Medicine
