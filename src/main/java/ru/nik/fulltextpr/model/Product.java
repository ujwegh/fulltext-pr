package ru.nik.fulltextpr.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "products")
@Indexed
public class Product extends BaseEntity {

    @Length(max = 1000)
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String description;

    @Min(0)
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private BigDecimal price = BigDecimal.ZERO;

    //    @IndexedEmbedded
//    @ManyToOne(cascade = CascadeType.ALL)
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String category;

    public Product() {
    }
    public Product(Long id, LocalDateTime createdDate, LocalDateTime lastUpdatedDate, String name, String description, String category, BigDecimal price) {
        super(id, name, createdDate, lastUpdatedDate);
        this.description = description;
        this.category = category;
        this.price = price;
    }
}
