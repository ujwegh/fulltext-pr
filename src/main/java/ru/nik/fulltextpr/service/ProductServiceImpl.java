package ru.nik.fulltextpr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nik.fulltextpr.model.Product;
import ru.nik.fulltextpr.repository.ProductDao;
import ru.nik.fulltextpr.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductRepository repository, ProductDao productDao) {
        this.repository = repository;
        this.productDao = productDao;
    }


    @Override
    public List<Product> getAll(String searchText) {
        return productDao.searchAllProducts(searchText);
    }

    @Override
    public Integer getAllCount(String searchText) {
        return productDao.searchAllCount(searchText);
    }

    @Override
    public List<Product> getAllWithPagination(String searchText) {
        return null;
    }

    @Override
    public List<Product> getAllByLike(String searchText) {
        return repository.findAllByDescriptionLike(searchText);
    }
}
