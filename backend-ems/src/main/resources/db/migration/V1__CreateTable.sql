CREATE TABLE emp_details (
id INT AUTO_INCREMENT PRIMARY KEY,
emp_id INT NOT NULL UNIQUE,
first_name VARCHAR(10) NOT NULL,
last_name VARCHAR(10),
email VARCHAR(255) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL,
role VARCHAR(20) NOT NULL,
blood_group VARCHAR(10),
gender VARCHAR(10),
is_active BOOLEAN NOT NULL,
martial_status VARCHAR(20),
dob VARCHAR(10) NOT NULL,
image LONGBLOB,
created_on DATETIME,
updated_on DATETIME,
created_by INT
);

CREATE TABLE contact_info (
id INT AUTO_INCREMENT PRIMARY KEY,
contact_type VARCHAR(255) NOT NULL,
phn_number VARCHAR(10) NOT NULL,
isActive BOOLEAN NOT NULL,
created_on DATETIME,
updated_on DATETIME,
empid INT NOT NULL,
CONSTRAINT fk_contact_emp_details FOREIGN KEY (empid) REFERENCES emp_details (id)
);

CREATE TABLE address_info (
id INT AUTO_INCREMENT PRIMARY KEY,
address_line1 VARCHAR(255) NOT NULL,
address_line2 VARCHAR(255),
city VARCHAR(255) NOT NULL,
state VARCHAR(255) NOT NULL,
pincode VARCHAR(10) NOT NULL,
empid INT NOT NULL,
created_on DATETIME,
updated_on DATETIME,
CONSTRAINT fk_address_emp_details FOREIGN KEY (empid) REFERENCES emp_details (id)
);