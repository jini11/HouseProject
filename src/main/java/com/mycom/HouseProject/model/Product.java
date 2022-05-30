package com.mycom.HouseProject.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "상품명을 입력해주세요.")
    private String name;

    @NotNull
    @NotBlank(message = "회사명을 입력해주세요.")
    private String company;

    @NotNull
    @NotBlank(message = "카테고리를 선택해주세요.")
    @Pattern(regexp = "^[1-6]*$", message = "카테고리를 선택해주세요.")
    private String category;

    @NotNull
    @NotBlank(message = "가격을 입력해주세요.")
    @Pattern(regexp = "^[0-9]*$", message = "숫자만 입력할 수 있습니다.")
    private String price;

    @NotNull
    @NotBlank(message = "재고를 입력해주세요.")
    @Pattern(regexp = "^[0-9]*$", message = "숫자만 입력할 수 있습니다.")
    private String stock;

    private Long discount;
    private Long saleprice;
    private String description;

    @NotBlank(message = "이미지를 첨부해주세요.")
    private String imgName;

    @NotBlank(message = "이미지를 첨부해주세요.")
    private String imgPath;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Cart> carts = new ArrayList<>();

}
