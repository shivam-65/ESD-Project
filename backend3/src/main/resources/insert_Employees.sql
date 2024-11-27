USE esd2;

INSERT INTO employees (employee_id, first_name, last_name, email, title, password) VALUES
(1, 'shivam','padaliya','abc@gmail.com', 'Admin', 'password'),
(2, 'yash','patel','yash@gmail.com', 'HR', 'password'),
(3, 'raj','kanani','raj@gmail.com', 'Manager', 'password');

INSERT INTO employee_salary (id, employee_id, payment_date, amount, description) VALUES
(1, 1, '2024-05-06', 10000, 'qwerty'),
(2, 1, '2024-06-06', 10000, 'qwerty'),
(3, 2, '2023-01-01', 20000, 'qwerty'),
(4, 3, '2022-05-07', 50000, 'monthly salary added');
(5, 3, '2023-04-03', 60000, 'Bonus+salary');

