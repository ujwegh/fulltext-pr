package ru.nik.fulltextpr.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import ru.nik.fulltextpr.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class ProductItemProcessor implements ItemProcessor<Product, Product> {
    private AtomicLong longId = new AtomicLong(0);

    @Override
    public Product process(final Product product) throws Exception {
        final String description = product.getDescription();
        int tabIndex = description.indexOf("\t");
        String category = "c";
        if (tabIndex > 0)
            category = description.substring(tabIndex);

        return new Product(longId.incrementAndGet(), LocalDateTime.now(), LocalDateTime.now(), "n", description, category, BigDecimal.valueOf((int) (Math.random() * 100000)));
    }

}
