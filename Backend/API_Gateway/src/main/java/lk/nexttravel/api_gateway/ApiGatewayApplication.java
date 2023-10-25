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

import java.io.File;

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
            if(!userRepository.existsByName("useradmin")){
                userService.saveNewAdminUserOnlyTesting(
                        UserSignupDTO.builder()
                                .signup_name("useradmin")
                                .signup_name_with_initial("User Admin")
                                .signup_address("address")
                                .signup_email("chethiyakaligu2@gmail.cm")
                                .signup_nic_or_passport("nicOrPassport")
                                .signup_password("UserAdmin@1234")
                                .build()
                        ,
                        RoleTypes.ROLE_ADMIN_SERVICE_USER
                );
            }
            if(!userRepository.existsByName("vehicleadmin")){
                userService.saveNewAdminUserOnlyTesting(
                        UserSignupDTO.builder()
                                .signup_name("vehicleadmin")
                                .signup_name_with_initial("Vehicle Admin")
                                .signup_address("address")
                                .signup_email("chethiyakaligu2@gmail.cm")
                                .signup_nic_or_passport("nicOrPassport")
                                .signup_password("VehicleAdmin@1234")
                                .build()
                        ,
                        RoleTypes.ROLE_ADMIN_SERVICE_VEHICLE
                );
            }
            if(!userRepository.existsByName("travelpackageadmin")){
                userService.saveNewAdminUserOnlyTesting(
                        UserSignupDTO.builder()
                                .signup_name("travelpackageadmin")
                                .signup_name_with_initial("Travel Package Admin")
                                .signup_address("address")
                                .signup_email("chethiyakaligu2@gmail.cm")
                                .signup_nic_or_passport("nicOrPassport")
                                .signup_password("TravelPackageAdmin@1234")
                                .build()
                        ,
                        RoleTypes.ROLE_ADMIN_SERVICE_TRAVELPACKAGE
                );
            }
            if(!userRepository.existsByName("hoteladmin")){
                userService.saveNewAdminUserOnlyTesting(
                        UserSignupDTO.builder()
                                .signup_name("hoteladmin")
                                .signup_name_with_initial("Hotel Admin")
                                .signup_address("address")
                                .signup_email("chethiyakaligu2@gmail.cm")
                                .signup_nic_or_passport("nicOrPassport")
                                .signup_password("HotelAdmin@1234")
                                .build()
                        ,
                        RoleTypes.ROLE_ADMIN_SERVICE_HOTEL
                );
            }
            if(!userRepository.existsByName("guideadmin")){
                userService.saveNewAdminUserOnlyTesting(
                        UserSignupDTO.builder()
                                .signup_name("guideadmin")
                                .signup_name_with_initial("Guide Admin")
                                .signup_address("address")
                                .signup_email("chethiyakaligu2@gmail.cm")
                                .signup_nic_or_passport("nicOrPassport")
                                .signup_password("GuideAdmin@1234")
                                .build()
                        ,
                        RoleTypes.ROLE_ADMIN_SERVICE_GUIDE
                );
            }


        };
    }

}
