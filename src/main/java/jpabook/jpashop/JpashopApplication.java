package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashopApplication {

    public static void main(String[] args) {


        SpringApplication.run(JpashopApplication.class, args);
    }

    // Entity 외부로 노출 하는 경우 사용
    // (일반적으로 사용하는 것 아님)
//    @Bean
//    Hibernate5Module hibernate5Module() {
//        return new Hibernate5Module();
//    }

}
