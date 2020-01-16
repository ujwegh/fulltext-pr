package ru.nik.fulltextpr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.nik.fulltextpr.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);
    List<Product> findAllByDescriptionLike(String text);
}
