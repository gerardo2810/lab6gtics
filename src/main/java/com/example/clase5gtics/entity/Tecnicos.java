package com.example.clase5gtics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "technician")
public class Tecnicos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TechnicianID")
    private Integer technicianId;
    @Column(name = "FirstName", nullable = false)
    private String firstName;
    @Column(name = "LastName", nullable = false)
    private String lasttName;
    @Column(name = "Dni", nullable = false)
    private String dni;
    @Column(name = "Phone", nullable = false)
    private String phone;
    @Column(name = "Age", nullable = false)
    private Integer age;

}
