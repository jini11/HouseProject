package com.mycom.HouseProject.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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
    private String category;
    @NotNull
    private Long price;
    @NotNull
    private Long stock;
    private Long discount;
    private Long saleprice;
    private String description;
    private String imgName;
    private String imgPath;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Cart> carts = new ArrayList<>();

}
