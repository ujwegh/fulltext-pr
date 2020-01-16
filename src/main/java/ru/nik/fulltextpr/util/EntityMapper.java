package ru.nik.fulltextpr.util;

import ru.nik.fulltextpr.dto.ProductDto;
import ru.nik.fulltextpr.model.Product;

public class EntityMapper {
    private EntityMapper() {}

    public static ProductDto toProductDto(Product from) {
        return ProductDto.builder()
                .id(from.getId())
                .name(from.getName())
                .description(from.getDescription())
                .category(from.getCategory())
                .build();
    }
}
