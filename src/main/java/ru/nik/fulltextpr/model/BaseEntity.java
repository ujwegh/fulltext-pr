package ru.nik.fulltextpr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Access(AccessType.FIELD)
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String name;

    @CreatedDate
    @DateBridge(resolution = Resolution.DAY)
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime lastUpdatedDate;
}
