package com.github.igorek2312.blog.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EntityScan(
        basePackages = {
                "org.springframework.data.jpa.convert.threeten",
                "com.github.igorek2312.blog.app.model"
        }
)
public class BlogAppApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BlogAppApplication.class);
    }

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(BlogAppApplication.class, args);
    }
}
