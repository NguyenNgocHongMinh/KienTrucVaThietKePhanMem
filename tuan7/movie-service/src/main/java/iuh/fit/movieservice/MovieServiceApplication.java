package iuh.fit.movieservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient; // Thêm dòng này

@SpringBootApplication
@EnableDiscoveryClient // Thêm annotation này để đăng ký với Eureka
public class MovieServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieServiceApplication.class, args);
    }

}