package org.gvozdetscky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created on 10.08.2017.
 * Базовый класс для запуска нашего приложения.
 * @author Egor Gvozdetscky.
 */
@SpringBootApplication
public class CryptoSystem {

    public static void main(String[] args) {
        SpringApplication.run(CryptoSystem.class, args);
    }

}
