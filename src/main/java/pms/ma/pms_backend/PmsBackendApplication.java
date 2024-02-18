package pms.ma.pms_backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pms.ma.pms_backend.entities.User;
import pms.ma.pms_backend.services.UserService;

@SpringBootApplication
public class PmsBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PmsBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserService userService) {
        return args -> {
            User user = User.builder()
                    .email("admin@gmail.com")
                    .username("admin")
                    .password("admin")
                    .firstName("Admin")
                    .lastName("Admin")
                    .build();
//            userService.addUser(user);
        };
    }


}
