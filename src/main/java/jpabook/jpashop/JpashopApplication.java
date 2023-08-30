package jpabook.jpashop;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpashopApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpashopApplication.class, args);
    }

    // Entity 외부로 노출 하는 경우 사용
    // (일반적으로 사용하는 것 아님)
    @Bean
    Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }

}
