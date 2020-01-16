package ru.nik.fulltextpr.util;

import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nik.fulltextpr.repository.CategoryRepository;
import ru.nik.fulltextpr.repository.ProductRepository;

@Component
public class ProductUtil {

    @Autowired
    private CategoryRepository categoryRepository;
    @Accessors
    private ProductRepository productRepository;

    /**
     * Fill db to test fulltext search on large amount of data
     */
    public void fillDb() {






    }




}
