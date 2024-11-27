package com.shivam.backend3.Service;

import com.shivam.backend3.DAO.EmployeeRepository;
import com.shivam.backend3.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee verifyEmployee(String email, String enteredPassword) {
        try {
            // Retrieve the employee by email
            Employee employee = employeeRepository.findByEmail(email);

            // Check if the employee exists and has the "admin" department
            if (employee != null) {
                // Check if the passwords match
                if(employee.isPasswordMatch(enteredPassword))
                    return employee;
            }
        }
        catch (DataAccessException e) {
            // Handle the exception (log or rethrow if necessary)
            System.out.println("DataAccessException: " + e.getMessage());
        }

        // Either the employee doesn't exist or has a different department
        return null;
    }

}
