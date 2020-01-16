package ru.nik.fulltextpr.repository;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import ru.nik.fulltextpr.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Product> searchProducts(String searchText, int pageNo, int resultsPerPage) {
        FullTextQuery jpaQuery = getFullTextQuery(searchText);

        jpaQuery.setMaxResults(resultsPerPage);
        jpaQuery.setFirstResult((pageNo - 1) * resultsPerPage);

        return jpaQuery.getResultList();
    }

    @Override
    @Transactional
    public List<Product> searchAllProducts(String searchText) {
        FullTextQuery fullTextQuery = getFullTextQuery(searchText);
        return fullTextQuery.getResultList();
    }

    @Override
    public int searchAllCount(String searchText) {
        FullTextQuery jpaQuery = getFullTextQuery(searchText);
        return jpaQuery.getResultSize();
    }


    private FullTextQuery getFullTextQuery(String searchText) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Product.class)
                .get();

        Query keywordQuery = queryBuilder
                .keyword()
                .onFields("name", "description")
                .matching(searchText)
                .createQuery();
        return getJpaQuery(keywordQuery);
    }


    private FullTextQuery getJpaQuery(org.apache.lucene.search.Query luceneQuery) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        return fullTextEntityManager.createFullTextQuery(luceneQuery, Product.class);
    }
}
