package com.shivam.backend3.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    @Column(nullable = false)
    private String firstname;

    private String lastname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String title;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Employee_Salary> employeeSalaries;

    public boolean isPasswordMatch(String enteredPassword) {
        // Assuming passwords are stored in plain text (not recommended for production)
        return enteredPassword.equals(this.password);
    }

}
