package com.shivam.backend3.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee_Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column // Added @Column annotation to specify the column name
    private Integer salaryId;

    @Column (name = "payment_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date payment_date;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;
}
