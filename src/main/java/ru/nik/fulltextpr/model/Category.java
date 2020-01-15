package ru.nik.fulltextpr.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
public class Category extends BaseEntity{

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String value;
}
