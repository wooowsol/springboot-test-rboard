package org.zerock.rboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(RboardApplication.class, args);
    }

}
