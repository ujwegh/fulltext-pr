package ru.nik.fulltextpr.repository;

import ru.nik.fulltextpr.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> searchProducts(String searchText, int pageNo, int resultsPerPage);
    List<Product> searchAllProducts(String searchText);
    int searchAllCount(String searchText);
}
