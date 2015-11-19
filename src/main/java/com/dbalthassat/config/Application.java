package com.dbalthassat.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@SpringBootApplication
@ComponentScan("com.dbalthassat")
@EntityScan("com.dbalthassat.entity")
@EnableJpaRepositories(basePackages = "com.dbalthassat.repository")
public class Application extends SpringBootServletInitializer {
    @Bean
    public ObjectMapper objectMapper() {
        HibernateAwareObjectMapper hibernateAwareObjectMapper = new HibernateAwareObjectMapper();
        hibernateAwareObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return hibernateAwareObjectMapper;
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter(objectMapper());
    }

    /**
     * Start Spring boot app on tomcat server.
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(this.getClass());
    }
}
