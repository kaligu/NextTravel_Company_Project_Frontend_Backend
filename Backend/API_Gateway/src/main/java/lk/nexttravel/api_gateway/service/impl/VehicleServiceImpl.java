/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/28/2023
  @ Time         : 7:18 PM
*/
package lk.nexttravel.api_gateway.service.impl;

import lk.nexttravel.api_gateway.Persistence.UserRepository;
import lk.nexttravel.api_gateway.advice.util.InternalServerException;
import lk.nexttravel.api_gateway.advice.util.UnauthorizeException;
import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.dto.auth.FrontendTokenDTO;
import lk.nexttravel.api_gateway.dto.auth.InternalFrontendSecurityCheckDTO;
import lk.nexttravel.api_gateway.dto.hotel.ReqHotelSaveDTO;
import lk.nexttravel.api_gateway.service.VehicleService;
import lk.nexttravel.api_gateway.service.security.Authenticate_Authorize_Service;
import lk.nexttravel.api_gateway.service.security.util.APIGatewayJwtAccessTokenServiceBackend;
import lk.nexttravel.api_gateway.util.RespondCodes;
import lk.nexttravel.api_gateway.util.RoleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/28/2023
 * Time    : 7:18 PM
 */

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    Authenticate_Authorize_Service authenticate_authorize_service;

    @Autowired
    APIGatewayJwtAccessTokenServiceBackend apiGatewayJwtAccessTokenServiceBackend;

    @Override
    public Mono<ResponseEntity<RespondDTO>> UserAdminGetProfileImage(String access_username, String access_jwt_token, String access_refresh_token) {
        try{
            FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder().access_jwt_token(access_jwt_token).access_username(access_username).access_refresh_token(access_refresh_token).build();
            InternalFrontendSecurityCheckDTO internalFrontendSecurityCheckDTO = authenticate_authorize_service.validateRequestsAndGetMetaData(frontendTokenDTO);
            if(
                    internalFrontendSecurityCheckDTO.isAccesssible()
                            &&
                            internalFrontendSecurityCheckDTO.getRole().equals(RoleTypes.ROLE_ADMIN_SERVICE_VEHICLE)
            ) {
                //get data using restcontroller
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<>(null, headers); // Sending an empty body

                ResponseEntity<String> responseEntity = restTemplate.exchange(
                        "http://localhost:1020/api/admin/vehicle-admin-get-profile-image?id=" + userRepository.findUserByName(frontendTokenDTO.getAccess_username()).get().getId() + "&token=" + apiGatewayJwtAccessTokenServiceBackend.generateToken(),
                        HttpMethod.GET,
                        entity,
                        String.class
                );
                //send to front
                return Mono.just(new ResponseEntity<RespondDTO>(
                        RespondDTO.builder()
                                .rspd_code(RespondCodes.Respond_SUCCESS)
                                .data(responseEntity.getBody())
                                .token(
                                        FrontendTokenDTO.builder()
                                                .access_username(internalFrontendSecurityCheckDTO.getUsername())
                                                .access_jwt_token(internalFrontendSecurityCheckDTO.getAccess_token())
                                                .access_refresh_token(internalFrontendSecurityCheckDTO.getRefresh_token())
                                                .build()
                                )
                                .build()
                        ,
                        HttpStatus.OK
                ));
            }else {
                System.out.println("Not authorized");
                return Mono.error(new UnauthorizeException("Unauthorized request"));
            }
        }catch (Exception e){
            System.out.println("internal server error");
            throw new InternalServerException("Internal server Error");
        }
    }

    @Override
    public Mono<ResponseEntity<RespondDTO>> createNewVehcile(String vehicle_type, String vehicle_fuel_type, String vehicle_hybrid_or_non_hybrid, String vehicle_seat_capacity, String vehicle_transmission_type, String vehicle_fuel_usage, String vehicle_perday_vehicle_fee, String vehicle_category, String vehicle_image_sideview, String vehicle_image_frontview, String vehicle_image_rearview, String vehicle_image_front_interior_view, String vehicle_image_rear_interior_view, String vehicle_driver_name, String vehicle_driver_tell, String vehicle_driver_license_rear_view, String vehicle_driver_license_front_view, String vehicle_driver_remarks, String accessUsername, String accessToken, String refreshToken) {
        try{
            FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder().access_jwt_token(accessToken).access_username(accessUsername).access_refresh_token(refreshToken).build();
            InternalFrontendSecurityCheckDTO internalFrontendSecurityCheckDTO = authenticate_authorize_service.validateRequestsAndGetMetaData(frontendTokenDTO);
            if(
                    internalFrontendSecurityCheckDTO.isAccesssible()
                            &&
                            internalFrontendSecurityCheckDTO.getRole().equals(RoleTypes.ROLE_ADMIN_SERVICE_VEHICLE)
            ) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                ResponseEntity<String> responseEntity = restTemplate.exchange(
                        "http://localhost:1040/api/hotel/save_new_hotel",
                        HttpMethod.POST,
                        new HttpEntity<Object> (
                                ReqHotelSaveDTO.builder()
                                        .location(location)
                                        .location_coordinates(locationMapLink)
                                        .category_stars(Integer.parseInt(categoryStar))
                                        .name(name)
                                        .remarks(remarks)
                                        .perday_hotel_fee_for_days(Integer.parseInt(perdayHotelFee))
                                        .perday_hotel_fee_for_nights(Integer.parseInt(pernightHotelFee))
                                        .email(email)
                                        .is_pet_allowed(Boolean.parseBoolean(isPetAllowed))
                                        .cancellation_fee(Integer.parseInt(cancellationFee))
                                        .tell_1(tell1)
                                        .tell_2(tell2)
                                        .reference_link(referenceLink)
                                        .image(image)
                                        .token(apiGatewayJwtAccessTokenServiceBackend.generateToken())

                                        .option_1_name(option_1_name)
                                        .option_1_description(option_1_description)
                                        .option_1_fee(option_1_fee)

                                        .option_2_name(option_2_name)
                                        .option_2_description(option_2_description)
                                        .option_2_fee(option_2_fee)

                                        .option_3_name(option_3_name)
                                        .option_3_description(option_3_description)
                                        .option_3_fee(option_3_fee)

                                        .option_4_name(option_4_name)
                                        .option_4_description(option_4_description)
                                        .option_4_fee(option_4_fee)

                                        .build()
                                ,
                                headers
                        ),
                        String.class
                );

                if(responseEntity.getStatusCode()==HttpStatus.CREATED){
                    FrontendTokenDTO newfrontendTokenDTO = FrontendTokenDTO.builder()
                            .access_username(accessUsername)
                            .access_jwt_token(internalFrontendSecurityCheckDTO.getAccess_token())
                            .access_refresh_token(internalFrontendSecurityCheckDTO.getRefresh_token())
                            .build();

                    //----------------------------------------------return if all are done
                    return Mono.just(
                            new ResponseEntity<RespondDTO> (
                                    RespondDTO.builder()
                                            .rspd_code(RespondCodes.Respond_DATA_SAVED)
                                            .token(newfrontendTokenDTO)
                                            .data(null)
                                            .build()
                                    ,
                                    HttpStatus.CREATED)
                    );
                }else {
                    throw new Exception("Server error");
                }
            }else {
                return Mono.error(new UnauthorizeException("Unauthorized request"));
            }
        }catch (Exception e){
            return Mono.error(new InternalServerException("Internal Server error!"+e));
        }
    }
}
