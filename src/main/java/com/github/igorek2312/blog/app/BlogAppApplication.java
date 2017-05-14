package com.github.igorek2312.blog.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "application-${spring.profiles.active}.properties", ignoreResourceNotFound = true)
public class BlogAppApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BlogAppApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogAppApplication.class, args);
    }
}
