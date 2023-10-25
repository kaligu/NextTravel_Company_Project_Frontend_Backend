package lk.nexttravel.api_gateway;

import lk.nexttravel.api_gateway.Persistence.UserRepository;
import lk.nexttravel.api_gateway.dto.auth.UserSignupDTO;
import lk.nexttravel.api_gateway.entity.User;
import lk.nexttravel.api_gateway.service.SequenceGeneratorService;
import lk.nexttravel.api_gateway.service.UserService;
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
    UserService userService;

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
                userService.saveNewGuestUser(
                        UserSignupDTO.builder()
                                .signup_name(name)
                                .signup_name_with_initial(nameWithInitial)
                                .signup_address(addres)
                                .signup_email(email)
                                .signup_nic_or_passport(nicOrPassport)
                                .signup_password(password)
                                .signup_profile_image(image)
                                .build()
                );
            }
        };
    }

}
