package com.shivam.backend3.DAO;

import com.shivam.backend3.Entity.Employee_Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Employee_Salary, Long> {

    @Query("SELECT e FROM Employee_Salary e WHERE MONTH(e.payment_date) = :month AND YEAR(e.payment_date) = :year AND e.employee.employeeId = :employeeId")
    List<Employee_Salary> findEmployee_SalariesByEmployeeIdAndMonthAndYear (Integer employeeId, Integer month, Integer year);

    @Query("SELECT s FROM Employee_Salary s WHERE s.employee.employeeId = :employeeId")
    List<Employee_Salary> findAllById(Integer employeeId);

    //List<Employee_Salary> findAllByEmployee(Employee employee);

}
