CREATE DATABASE IF NOT EXISTS esd2;

USE esd2;

CREATE TABLE employees(
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL ,
    title VARCHAR(255),

);

CREATE TABLE employee_salary(
    salary_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    payment_date DATE,
    amount DECIMAL(10,2),
    description TEXT,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
);
