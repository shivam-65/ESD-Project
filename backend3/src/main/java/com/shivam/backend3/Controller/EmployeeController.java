package com.shivam.backend3.Controller;

import com.shivam.backend3.DTO.Loginform;
import com.shivam.backend3.Entity.Employee;
import com.shivam.backend3.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/employee/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Valid Loginform emp) {
        // Assuming LoginRequest has userId and password fields
//        String email = emp.getEmail();
//        String enteredPassword = emp.getPassword();
//
//        Employee employeeData = employeeService.verifyEmployee(email, enteredPassword);
//        if(employeeData!=null)
//            return ResponseEntity.ok(employeeData);
//         else {
//            return ResponseEntity.status(401).body(null);
//        }

        return ResponseEntity.ok(employeeService.verifyEmployee(emp));
    }
}
