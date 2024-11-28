package com.shivam.backend3.Service;

import com.shivam.backend3.DAO.EmployeeRepository;
import com.shivam.backend3.DTO.Loginform;
import com.shivam.backend3.Entity.Employee;
import com.shivam.backend3.exception.EmployeeNotFoundException;
import com.shivam.backend3.helper.EncryptionService;
import com.shivam.backend3.helper.JWTHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    public Employee getEmployee(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new EmployeeNotFoundException(
                        format("Cannot update Customer:: No customer found with the provided ID:: %s", email)
                ));
    }


    public Employee getEmployeeByEmail(String email) {
        Employee employee = getEmployee(email);
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not found"));
    }

    public ResponseEntity<?> verifyEmployee(Loginform loginform) {
//        try {
//            // Retrieve the employee by email
//            Employee employee = employeeRepository.findByEmail(email);
//
//            // Check if the employee exists and has the "admin" department
//            if (employee != null) {
//                // Check if the passwords match
//                if(employee.isPasswordMatch(enteredPassword))
//                    return employee;
//            }
//        }
//        catch (DataAccessException e) {
//            // Handle the exception (log or rethrow if necessary)
//            System.out.println("DataAccessException: " + e.getMessage());
//        }
//
//        // Either the employee doesn't exist or has a different department
//        return null;

        Employee employee = getEmployeeByEmail(loginform.email());
//        if(!encryptionService.validate(loginform.password(), employee.getPassword())) {
//            return null;
//        }

         if(loginform.password().equals(employee.getPassword())) {
             employee.setJwtToken(jwtHelper.generateToken(loginform.email()));
             return ResponseEntity.ok(employee);
         }

//        return "Authenticate Successfully";
        return ResponseEntity.badRequest().build();


    }

}
