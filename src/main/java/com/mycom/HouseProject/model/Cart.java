package com.mycom.HouseProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long pid;
    @NotNull
    private Long count;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnore
    private User user;
}
