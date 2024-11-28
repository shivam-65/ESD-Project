package com.shivam.backend3.Controller;

import com.shivam.backend3.Entity.Employee_Salary;
import com.shivam.backend3.Service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @GetMapping("/salary/history/{employeeId}/{month}/{year}")
    public List<Employee_Salary> getSalaryHistory(@PathVariable Integer employeeId,
                                           @PathVariable Integer month,
                                           @PathVariable Integer year) {
        return salaryService.getSalaryHistory(employeeId, month, year);
    }

    @GetMapping("/salary/history/{employeeId}")
    public List<Employee_Salary> getSalary(@PathVariable Integer employeeId){
        return salaryService.getSalary(employeeId);
    }
}

