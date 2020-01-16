package ru.nik.fulltextpr.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import ru.nik.fulltextpr.model.Product;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EntityManagerFactory emf;

    @Bean
    public FlatFileItemReader<Product> reader() {
        return new FlatFileItemReaderBuilder<Product>()
                .name("personItemReader")
                .resource(new ClassPathResource("select_description__category_from_part_n.csv"))
                .delimited()
                .delimiter(" --- ")
                .names("description")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Product>() {{
                    setTargetType(Product.class);
                }})
                .build();
    }

    @Bean
    public ProductItemProcessor processor() {
        return new ProductItemProcessor();
    }

    @Bean
    public JpaItemWriter<Product> writer() {
        JpaItemWriter writer = new JpaItemWriter();
        writer.setEntityManagerFactory(emf);
        return writer;
    }

    @Bean
    public Job importUserJob(Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JpaItemWriter<Product> writer) {
        return stepBuilderFactory.get("step1")
                .<Product, Product>chunk(1000)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }
}