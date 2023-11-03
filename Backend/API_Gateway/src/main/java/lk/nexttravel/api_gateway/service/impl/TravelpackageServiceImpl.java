/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/28/2023
  @ Time         : 9:46 PM
*/
package lk.nexttravel.api_gateway.service.impl;

import lk.nexttravel.api_gateway.Persistence.UserRepository;
import lk.nexttravel.api_gateway.advice.util.InternalServerException;
import lk.nexttravel.api_gateway.advice.util.UnauthorizeException;
import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.dto.auth.FrontendTokenDTO;
import lk.nexttravel.api_gateway.dto.auth.InternalFrontendSecurityCheckDTO;
import lk.nexttravel.api_gateway.dto.hotel.ReqHotelSaveDTO;
import lk.nexttravel.api_gateway.dto.travelpackage.ReqNewTravelPackageDTO;
import lk.nexttravel.api_gateway.dto.user.ReqProfileDataAdminsDTO;
import lk.nexttravel.api_gateway.dto.user.UserReqProfileDataDTO;
import lk.nexttravel.api_gateway.entity.User;
import lk.nexttravel.api_gateway.service.TravelpackageService;
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
 * Time    : 9:46 PM
 */

@Service
public class TravelpackageServiceImpl implements TravelpackageService {

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
        //Authentication and authorization
        try{
            FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder().access_jwt_token(access_jwt_token).access_username(access_username).access_refresh_token(access_refresh_token).build();


            InternalFrontendSecurityCheckDTO internalFrontendSecurityCheckDTO = authenticate_authorize_service.validateRequestsAndGetMetaData(frontendTokenDTO);
            System.out.println(internalFrontendSecurityCheckDTO.toString());
            if(
                    internalFrontendSecurityCheckDTO.isAccesssible()
                            &&
                            internalFrontendSecurityCheckDTO.getRole().equals(RoleTypes.ROLE_ADMIN_SERVICE_TRAVELPACKAGE)
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
                return Mono.error(new UnauthorizeException("Unauthorized request"));
            }
        }catch (Exception e){

            return Mono.error(new InternalServerException("Internal Server error!"+e));
        }
    }

    @Override
    public Mono<ResponseEntity<RespondDTO>> createNewTravelPack(String travelpackage_name, String travelpackage_travel_areas, String travelpackage_category, String travelpackage_service_charge, String travelpackage_travelling_length_km, String travelpackage_travelling_days, String travelpackage_hotel_booking_type, String travelpackage_hotel_booking_nigths, String travelpackage_hotel_booking_days, String vedio_name, String vedio_content, String vedio_link, String insuarance_name, String insuarance_description, String insuarance_policies, String insuarance_covergae_value, String promotion_name, String promotion_content, String promotion_rate, String promotion_start_date, String promotion_end_date, String access_username, String access_jwt_token, String access_refresh_token) {
        try{
            FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder().access_jwt_token(access_jwt_token).access_username(access_username).access_refresh_token(access_refresh_token).build();
            InternalFrontendSecurityCheckDTO internalFrontendSecurityCheckDTO = authenticate_authorize_service.validateRequestsAndGetMetaData(frontendTokenDTO);
            if(
                    internalFrontendSecurityCheckDTO.isAccesssible()
                            &&
                            internalFrontendSecurityCheckDTO.getRole().equals(RoleTypes.ROLE_ADMIN_SERVICE_TRAVELPACKAGE)
            ) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                ResponseEntity<String> responseEntity = restTemplate.exchange(
                        "http://localhost:1060/api/travelpack/save_new_travelpack",
                        HttpMethod.POST,
                        new HttpEntity<Object> (
                                ReqNewTravelPackageDTO.builder()
                                        .travelpackage_name(travelpackage_name)
                                        .travelpackage_travel_areas(travelpackage_travel_areas)
                                        .travelpackage_category(travelpackage_category)
                                        .travelpackage_service_charge(Integer.parseInt(travelpackage_service_charge))
                                        .travelpackage_travelling_length_km(Integer.parseInt(travelpackage_travelling_length_km))
                                        .travelpackage_travelling_days(Integer.parseInt(travelpackage_travelling_days))
                                        .travelpackage_hotel_booking_type(travelpackage_hotel_booking_type)
                                        .travelpackage_hotel_booking_nigths(Integer.parseInt(travelpackage_hotel_booking_nigths))
                                        .travelpackage_hotel_booking_days(Integer.parseInt(travelpackage_hotel_booking_days))
                                        .vedio_name(vedio_name)
                                        .vedio_content(vedio_content)
                                        .vedio_link(vedio_link)
                                        .insuarance_name(insuarance_name)
                                        .insuarance_description(insuarance_description)
                                        .insuarance_policies(insuarance_policies)
                                        .insuarance_covergae_value(Integer.parseInt(insuarance_covergae_value))
                                        .promotion_name(promotion_name)
                                        .promotion_content(promotion_content)
                                        .promotion_rate(promotion_rate)
                                        .promotion_start_date(promotion_start_date)
                                        .promotion_end_date(promotion_end_date)
                                        .token(apiGatewayJwtAccessTokenServiceBackend.generateToken())
                                        .build()
                                ,
                                headers
                        ),
                        String.class
                );

                if(responseEntity.getStatusCode()==HttpStatus.CREATED){
                    FrontendTokenDTO newfrontendTokenDTO = FrontendTokenDTO.builder()
                            .access_username(access_username)
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
