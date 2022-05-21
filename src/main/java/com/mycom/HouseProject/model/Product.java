package com.mycom.HouseProject.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String company;
    @NotNull
    private Long category;
    @NotNull
    private Long price;
    @NotNull
    private Long stock;
    private Long discount;
    private String description;
}
