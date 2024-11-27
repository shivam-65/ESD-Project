USE esd2;

ALTER TABLE employee_salary
ADD CONSTRAINT employee_id
FOREIGN KEY (employee_id)
REFERENCES employees(employee_id);

