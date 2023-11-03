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
import lk.nexttravel.api_gateway.dto.user.ReqProfileDataAdminsDTO;
import lk.nexttravel.api_gateway.dto.user.UserReqProfileDataDTO;
import lk.nexttravel.api_gateway.dto.vehicle.ReqVehicleSaveDTO;
import lk.nexttravel.api_gateway.entity.User;
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
    public Mono<ResponseEntity<RespondDTO>> UserAdminGetProfileData(String access_username, String access_jwt_token, String access_refresh_token) {
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

                User user = userRepository.findUserByName(frontendTokenDTO.getAccess_username()).get();

                System.out.println(user.getId());
                ResponseEntity<ReqProfileDataAdminsDTO> reqProfileDataAdminsDTOResponseEntity = restTemplate.exchange(
                        "http://localhost:1020/api/admin/user-admin-get-profile-data?id=" + user.getId() + "&token=" + apiGatewayJwtAccessTokenServiceBackend.generateToken(),
                        HttpMethod.GET,
                        entity,
                        ReqProfileDataAdminsDTO.class
                );
                //send to front
                return Mono.just(new ResponseEntity<RespondDTO>(
                        RespondDTO.builder()
                                .rspd_code(RespondCodes.Respond_SUCCESS)
                                .data(
                                        UserReqProfileDataDTO.builder()
                                                .id(user.getId())
                                                .name(user.getName())
                                                .name_with_initial(reqProfileDataAdminsDTOResponseEntity.getBody().getName_with_initial())
                                                .address(reqProfileDataAdminsDTOResponseEntity.getBody().getAddress())
                                                .email(user.getEmail())
                                                .profile_image(reqProfileDataAdminsDTOResponseEntity.getBody().getProfile_image())
                                                .nic_or_passport(reqProfileDataAdminsDTOResponseEntity.getBody().getNic_or_passport())
                                                .build()
                                )
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
                        "http://localhost:1050/api/vehicle/save_new_vehicle",
                        HttpMethod.POST,
                        new HttpEntity<Object> (
                                ReqVehicleSaveDTO.builder()
                                        .vehicle_type(vehicle_type)
                                        .vehicle_fuel_type(vehicle_fuel_type)
                                        .vehicle_hybrid_or_non_hybrid(vehicle_hybrid_or_non_hybrid)
                                        .vehicle_seat_capacity(vehicle_seat_capacity)
                                        .vehicle_transmission_type(vehicle_transmission_type)
                                        .vehicle_fuel_usage(vehicle_fuel_usage)
                                        .vehicle_perday_vehicle_fee(vehicle_perday_vehicle_fee)
                                        .vehicle_category(vehicle_category)

                                        .vehicle_image_sideview(vehicle_image_sideview)
                                        .vehicle_image_frontview(vehicle_image_frontview)
                                        .vehicle_image_rearview(vehicle_image_rearview)
                                        .vehicle_image_front_interior_view(vehicle_image_front_interior_view)
                                        .vehicle_image_rear_interior_view(vehicle_image_rear_interior_view)

                                        .vehicle_driver_name(vehicle_driver_name)
                                        .vehicle_driver_tell(vehicle_driver_tell)
                                        .vehicle_driver_license_rear_view(vehicle_driver_license_rear_view)
                                        .vehicle_driver_license_front_view(vehicle_driver_license_front_view)
                                        .vehicle_driver_remarks(vehicle_driver_remarks)

                                        .token(apiGatewayJwtAccessTokenServiceBackend.generateToken())
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
