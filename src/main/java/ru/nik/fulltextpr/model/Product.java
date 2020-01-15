package ru.nik.fulltextpr.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "products")
@Indexed
public class Product extends BaseEntity {

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String description;

    @Min(0)
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private BigDecimal price = BigDecimal.ZERO;

    @IndexedEmbedded
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

}
