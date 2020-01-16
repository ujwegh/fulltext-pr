package ru.nik.fulltextpr.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nik.fulltextpr.dto.ProductDto;
import ru.nik.fulltextpr.service.ProductService;
import ru.nik.fulltextpr.util.EntityMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/search")
    public List<ProductDto> getAllByFulltextSearch(@NonNull @RequestParam("text") String searchText) {
        log.info("Get all product by fulltext search of string: {}", searchText);
        return service.getAll(searchText)
                .stream()
                .map(EntityMapper::toProductDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/search/count")
    public Integer getAllCountByFulltextSearch(@NonNull @RequestParam("text") String searchText) {
        log.info("Count all product by fulltext search of string: {}", searchText);
        return service.getAllCount(searchText);
    }

    @GetMapping
    public List<ProductDto> getAll(@NonNull @RequestParam("text") String searchText) {
        log.info("Get all products by search by 'like' request");
        return service.getAllByLike(searchText)
                .stream()
                .map(EntityMapper::toProductDto)
                .collect(Collectors.toList());
    }
}
