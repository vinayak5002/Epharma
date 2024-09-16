import React, { useContext } from 'react';
import { useKeenSlider } from 'keen-slider/react';
import 'keen-slider/keen-slider.min.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { CustContext, CustContextType } from '../state/ContextProvide';
import { useNavigate } from 'react-router-dom';

const Home = () => {
	const { customerId } = useContext<CustContextType>(CustContext);
	const navigator = useNavigate();

	const [sliderRef] = useKeenSlider({
		loop: true,
		mode: 'free-snap',
		slides: { perView: 1 },
	});

	const onStartShopping = () => {
		navigator('/medicine');
	}
	
	return (
		<>
			{/* Hero Section with Text Overlay */}
			<div className="keen-slider" ref={sliderRef}>
				<div className="keen-slider__slide position-relative bg-primary" style={{ width: "100vw", height: "60vh" }}>
					<img className="img-fluid" style={{ objectFit: "cover", width: "100%", height: "100%" }} src="https://retailsolutions.ie/wp-content/uploads/2023/05/more-hands-equals-less-work-shot-two-focused-pharmacist-walking-around-doing-stock-inside-pharmacy-min.jpg" alt="Pharmacy" />
					<div className="position-absolute top-50 start-50 translate-middle text-center" style={{ width: '90%', color: '#0eaa00', fontSize: '2rem' }}>
						<span className="ms-2"></span>
						<h1 className="mb-3" style={{ fontSize: '4rem' }}>Welcome to E-Pharmacy</h1>
						<p>Your trusted online pharmacy for all your needs</p>
					</div>
				</div>
				<div className="keen-slider__slide position-relative bg-primary" style={{ width: "100vw", height: "60vh" }}>
					<img className="img-fluid" style={{ objectFit: "cover", width: "100%", height: "100%" }} src="https://cdn.dribbble.com/userupload/12899352/file/original-6fa3d2995076bb801296f6ffd2495559.png?resize=1504x1000" alt="Pharmacy" />
					<div className="position-absolute top-50 start-50 translate-middle text-white text-center" style={{ width: '90%', color: '#0eaa00', fontSize: '2rem' }}>
						<h1>Reliable and Convenient</h1>
						<p>Get your medications delivered right to your doorstep</p>
					</div>
				</div>
				{/* Add more slides with text overlay as needed */}
			</div>

			{/* About Section */}
			<section className="container my-5">
				<div className="row align-items-center">
					<div className="col-md-6">
						<h2 className="mb-4">About Us</h2>
						<p className="lead">
							Welcome to E-Pharmacy, your online destination for all your pharmaceutical needs. We offer a wide range of medications, wellness products, and health services at your convenience.
						</p>
					</div>
					<div className="col-md-6 d-flex justify-content-center">
						<img
							src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSPBcOAjLP54nbI3jySooP1MI5SKPOeF_qXhw&s"
							alt="About Us"
							className="img-fluid rounded shadow-sm"
							style={{ maxHeight: '100%', objectFit: 'contain' }}
						/>
					</div>
				</div>
			</section>

			{/* Features Section */}
			<section className="bg-light py-5">
				<div className="container">
					<h2 className="text-center mb-4">Our Features</h2>
					<div className="row">
						<div className="col-md-4 text-center">
							<h3>Wide Range of Products</h3>
							<p>From prescription medicines to over-the-counter products, we have it all.</p>
						</div>
						<div className="col-md-4 text-center">
							<h3>Fast Delivery</h3>
							<p>Get your medications delivered right to your doorstep quickly and safely.</p>
						</div>
						<div className="col-md-4 text-center">
							<h3>Expert Consultation</h3>
							<p>Consult with our experienced pharmacists online for personalized advice.</p>
						</div>
					</div>
				</div>
			</section>

			{/* Call-to-Action Section */}
			<section className="bg-success text-white text-center py-5">
				<div className="container">
					<h2>Ready to Get Started?</h2>
					<p>Explore our range of products and services or sign up to start shopping today!</p>
					{
						customerId === -1 ?
							<>
								<a href="/sign-up" className="btn btn-light btn-lg me-3">Sign Up</a>
								<a href="/login" className="btn btn-light btn-lg">Log in</a>
							</>
							:
							<button onClick={onStartShopping} className="btn btn-light btn-lg">Start Shopping</button>
					}
				</div>
			</section>

			{/* Testimonials Section (Optional) */}
			<section className="container my-5">
				<h2 className="text-center mb-4">What Our Customers Say</h2>
				<div className="row">
					<div className="col-md-4 text-center">
						<blockquote className="blockquote">
							<p className="mb-0">"Great service and fast delivery!"</p>
							<footer className="blockquote-footer">John Doe</footer>
						</blockquote>
					</div>
					<div className="col-md-4 text-center">
						<blockquote className="blockquote">
							<p className="mb-0">"Very convenient and user-friendly website."</p>
							<footer className="blockquote-footer">Jane Smith</footer>
						</blockquote>
					</div>
					<div className="col-md-4 text-center">
						<blockquote className="blockquote">
							<p className="mb-0">"Excellent customer support and great prices."</p>
							<footer className="blockquote-footer">Alex Johnson</footer>
						</blockquote>
					</div>
				</div>
			</section>
		</>
	);
};

export default Home;
