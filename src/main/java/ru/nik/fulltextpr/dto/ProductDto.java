package ru.nik.fulltextpr.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private String category;
    private BigDecimal price;
}
