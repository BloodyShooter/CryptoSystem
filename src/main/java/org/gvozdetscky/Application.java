package org.gvozdetscky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

/**
 * Created on 10.08.2017.
 * Базовый класс для запуска нашего приложения.
 * @author Egor Gvozdetscky.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private static final long MAX_FILE_SIZE = 1024 * 1024 * 25;//25MB

    private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 30;//30MB

    /**
     * Bean для конфигурации загрузки файлов на сервер
     * Создается фабрика и назначается макс. размер файла и запроса.
     * @return возврашает фабрику
     */
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(MAX_FILE_SIZE);
        factory.setMaxRequestSize(MAX_REQUEST_SIZE);
        return factory.createMultipartConfig();
    }
}
