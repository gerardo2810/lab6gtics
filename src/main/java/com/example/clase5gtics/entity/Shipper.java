package com.example.clase5gtics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Shippers")
public class Shipper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipperid")
    private Integer shipperId;
    @Column(name = "companyname", nullable = false)
    private String companyname;
    private String phone;

}
