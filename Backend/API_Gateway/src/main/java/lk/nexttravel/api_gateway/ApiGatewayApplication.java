package lk.nexttravel.api_gateway;

import lk.nexttravel.api_gateway.Persistence.UserRepository;
import lk.nexttravel.api_gateway.entity.User;
import lk.nexttravel.api_gateway.service.SequenceGeneratorService;
import lk.nexttravel.api_gateway.util.RoleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ApiGatewayApplication {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(){
        return args -> {
            if(!userRepository.existsByName("admin")){
                userRepository.save(
                        User.builder()
                                .id("U00"+sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME))
                                .email("chethiyakaligu2@gmail.com")
                                .name("admin")
                                .password(passwordEncoder.encode("Root@1234"))
                                .role_type(RoleTypes.ROLE_ADMIN_SERVICE_USER)
                                .build()
                );
            }
        };
    }

}
