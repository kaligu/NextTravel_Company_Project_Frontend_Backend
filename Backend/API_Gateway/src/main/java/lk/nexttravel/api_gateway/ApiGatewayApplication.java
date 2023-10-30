package lk.nexttravel.api_gateway;

import jakarta.annotation.PostConstruct;
import lk.nexttravel.api_gateway.Persistence.UserRepository;
import lk.nexttravel.api_gateway.dto.auth.UserSignupDTO;
import lk.nexttravel.api_gateway.service.SequenceGeneratorService;
import lk.nexttravel.api_gateway.service.SystemUserService;
import lk.nexttravel.api_gateway.util.RoleTypes;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class ApiGatewayApplication {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SystemUserService systemUserService;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    PasswordEncoder passwordEncoder;

    private String UserServiceMngrImg ;
    private String HotelServiceMngrImg ;
    private String TravelPackageServiceMngrImg ;
    private String VehicleServiceMngrImg;
    private String GuideServiceMngrImg;

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() throws IOException {
        return args -> {

            if(!userRepository.existsByName("kaligu")){
                systemUserService.saveNewAdminUserOnlyTesting(
                        UserSignupDTO.builder()
                                .signup_name("kaligu")
                                .signup_name_with_initial("H.C.K.Jayanath")
                                .signup_address("Horana")
                                .signup_email("chethiyakaligu2@gmail.com")
                                .signup_nic_or_passport("200133702832")
                                .signup_password("Kaligu@1234")
                                .signup_profile_image(UserServiceMngrImg)
                                .build()
                        ,
                        RoleTypes.ROLE_ADMIN_SERVICE_USER
                );
            }
            if(!userRepository.existsByName("nimal")){
                systemUserService.saveNewAdminUserOnlyTesting(
                        UserSignupDTO.builder()
                                .signup_name("nimal")
                                .signup_name_with_initial("P.Nimal Perera")
                                .signup_address("Colombo")
                                .signup_email("chethiyakaligu2@gmail.com")
                                .signup_nic_or_passport("199899858533")
                                .signup_password("Nimal@1234")
                                .signup_profile_image(VehicleServiceMngrImg)
                                .build()
                        ,
                        RoleTypes.ROLE_ADMIN_SERVICE_VEHICLE
                );
            }
            if(!userRepository.existsByName("lakmal")){
                systemUserService.saveNewAdminUserOnlyTesting(
                        UserSignupDTO.builder()
                                .signup_name("lakamal")
                                .signup_name_with_initial("K.P.M.Lakmal")
                                .signup_address("Padukka")
                                .signup_email("chethiyakaligu2@gmail.com")
                                .signup_nic_or_passport("199658652548")
                                .signup_password("Lakmal@1234")
                                .signup_profile_image(TravelPackageServiceMngrImg)
                                .build()
                        ,
                        RoleTypes.ROLE_ADMIN_SERVICE_TRAVELPACKAGE
                );
            }
            if(!userRepository.existsByName("pasan")){
                systemUserService.saveNewAdminUserOnlyTesting(
                        UserSignupDTO.builder()
                                .signup_name("pasan")
                                .signup_name_with_initial("P.K.K.Pasan Athapaththu")
                                .signup_address("Ingiriya")
                                .signup_email("chethiyakaligu2@gmail.com")
                                .signup_nic_or_passport("199665859354")
                                .signup_password("Pasan@1234")
                                .signup_profile_image(HotelServiceMngrImg)
                                .build()
                        ,
                        RoleTypes.ROLE_ADMIN_SERVICE_HOTEL
                );
            }
            if(!userRepository.existsByName("nirot")){
                systemUserService.saveNewAdminUserOnlyTesting(
                        UserSignupDTO.builder()
                                .signup_name("nirot")
                                .signup_name_with_initial("M.K.P.Nirot")
                                .signup_address("Moragalla")
                                .signup_email("chethiyakaligu2@gmail.com")
                                .signup_nic_or_passport("198858652548")
                                .signup_password("Nirot@1234")
                                .signup_profile_image(GuideServiceMngrImg)
                                .build()
                        ,
                        RoleTypes.ROLE_ADMIN_SERVICE_GUIDE
                );
            }


        };


    }


    @PostConstruct
    public void initializeImages() {
        try {
            UserServiceMngrImg = Files.readString(Paths.get("D:\\springboot\\sem2Project\\NextTravel_Company_Project_Frontend_Backend\\Backend\\API_Gateway\\src\\main\\resources\\UserServiceMngrImg.txt"));
            HotelServiceMngrImg = Files.readString(Paths.get("D:\\springboot\\sem2Project\\NextTravel_Company_Project_Frontend_Backend\\Backend\\API_Gateway\\src\\main\\resources\\HotelServiceMngrImg.txt"));
            TravelPackageServiceMngrImg = Files.readString(Paths.get("D:\\springboot\\sem2Project\\NextTravel_Company_Project_Frontend_Backend\\Backend\\API_Gateway\\src\\main\\resources\\TravelPackageServiceMngrImg.txt"));
            VehicleServiceMngrImg = Files.readString(Paths.get("D:\\springboot\\sem2Project\\NextTravel_Company_Project_Frontend_Backend\\Backend\\API_Gateway\\src\\main\\resources\\VehicleServiceMngrImg.txt"));
            GuideServiceMngrImg = Files.readString(Paths.get("D:\\springboot\\sem2Project\\NextTravel_Company_Project_Frontend_Backend\\Backend\\API_Gateway\\src\\main\\resources\\GuideServiceMngrImg.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
