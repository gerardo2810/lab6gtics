package com.example.clase5gtics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "Categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
    private int id;
    @Column(nullable = false)
    private String categoryname;
    private String description;
    @Lob
    private byte[] picture;
}
