DROP SCHEMA IF EXISTS epharma_customer;

CREATE SCHEMA epharma_customer;
USE epharma_customer;

CREATE TABLE customer (
    customerId INT AUTO_INCREMENT PRIMARY KEY,
    customerName VARCHAR(255) NOT NULL,
    customerEmailId VARCHAR(255) NOT NULL,
    contactNumber VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    gender VARCHAR(15) NOT NULL,
    dateOfBirth DATE NOT NULL
);

create table address(
  customerId INT,
  addressName VARCHAR(255) NOT NULL,
    addressLine1 VARCHAR(255) NOT NULL,
    addressLine2 VARCHAR(255) NOT NULL,
    area VARCHAR(255) NOT NULL,
    city VARCHAR(15) NOT NULL,
    state VARCHAR(255) NOT NULL,
    pincode VARCHAR(15) NOT NULL,
    FOREIGN KEY (customerId) REFERENCES customer(customerId)
);

INSERT INTO customer VALUES (1001,'adesh', 'adesh@gmail.com', '7037520915', 'adesh@123', 'M', SYSDATE()-INTERVAL 2 DAY);
INSERT INTO customer VALUES (1002,'vinayak', 'vinayak@gmail.com', '7037520915', 'vinayak@123', 'M', SYSDATE()-INTERVAL 2 DAY);
INSERT INTO customer VALUES (1003,'tanuj', 'tanuj@gmail.com', '7037520915', 'tanuj@123', 'M', SYSDATE()-INTERVAL 2 DAY);
INSERT INTO customer VALUES (1004,'ankit', 'ankit@gmail.com', '7037520915', 'ankit@123', 'M', SYSDATE()-INTERVAL 2 DAY);
INSERT INTO customer VALUES (1005,'harsh', 'harsh@gmail.com', '7037520915', 'harsh@123', 'M', SYSDATE()-INTERVAL 2 DAY);
INSERT INTO customer VALUES (1006,'arinjay', 'arinjay@gmail.com', '7037520915', 'arinjay@123', 'M', SYSDATE()-INTERVAL 2 DAY);


INSERT INTO address (customerId, addressName, addressLine1, addressLine2, area, city, state, pincode)
VALUES (1001, 'hathras', 'hathras junction', 'thulai', 'thulai', 'hathras', 'up', '204102');

INSERT INTO address (customerId, addressName, addressLine1, addressLine2, area, city, state, pincode)
VALUES (1002,'hathras', 'hathras junction', 'thulai', 'thulai', 'hathras', 'up', '204102');



commit;

select*from customer;
select * from address;
