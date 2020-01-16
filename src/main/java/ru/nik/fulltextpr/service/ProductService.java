package ru.nik.fulltextpr.service;

import ru.nik.fulltextpr.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll(String searchText);
    Integer getAllCount(String searchText);
    List<Product> getAllWithPagination(String searchText);
    List<Product> getAllByLike(String searchText);
}
