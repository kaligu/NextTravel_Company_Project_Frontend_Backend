/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/28/2023
  @ Time         : 6:29 PM
*/
package lk.nexttravel.api_gateway.service.impl;

import lk.nexttravel.api_gateway.Persistence.UserRepository;
import lk.nexttravel.api_gateway.advice.util.InternalServerException;
import lk.nexttravel.api_gateway.advice.util.UnauthorizeException;
import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.dto.auth.FrontendTokenDTO;
import lk.nexttravel.api_gateway.dto.auth.InternalFrontendSecurityCheckDTO;
import lk.nexttravel.api_gateway.dto.guide.ReqNewGuideSaveDTO;
import lk.nexttravel.api_gateway.dto.hotel.ReqHotelSaveDTO;
import lk.nexttravel.api_gateway.dto.user.ReqProfileDataAdminsDTO;
import lk.nexttravel.api_gateway.dto.user.UserReqProfileDataDTO;
import lk.nexttravel.api_gateway.entity.User;
import lk.nexttravel.api_gateway.service.HotelService;
import lk.nexttravel.api_gateway.service.security.Authenticate_Authorize_Service;
import lk.nexttravel.api_gateway.service.security.util.APIGatewayJwtAccessTokenServiceBackend;
import lk.nexttravel.api_gateway.util.RespondCodes;
import lk.nexttravel.api_gateway.util.RoleTypes;
import lk.nexttravel.api_gateway.util.RqRpURLs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/28/2023
 * Time    : 6:29 PM
 */

@Service
public class HotelServiceImpl implements HotelService {

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
            System.out.println(internalFrontendSecurityCheckDTO.toString());
            if(
                    internalFrontendSecurityCheckDTO.isAccesssible()
                            &&
                            internalFrontendSecurityCheckDTO.getRole().equals(RoleTypes.ROLE_ADMIN_SERVICE_HOTEL)
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
    public Mono<ResponseEntity<RespondDTO>> createNewHotel(String name, String location, String locationMapLink, String remarks, String referenceLink, String email, String tell1, String tell2, String categoryStar, String isPetAllowed, String perdayHotelFee, String pernightHotelFee, String cancellationFee, String image, String accessUsername, String accessToken, String refreshToken , String option_1_description, String option_1_name ,String option_1_fee, String option_2_description, String option_2_name,  String option_2_fee, String option_3_description, String option_3_name, String option_3_fee, String option_4_description, String option_4_name, String option_4_fee) {
        try{
            FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder().access_jwt_token(accessToken).access_username(accessUsername).access_refresh_token(refreshToken).build();
            InternalFrontendSecurityCheckDTO internalFrontendSecurityCheckDTO = authenticate_authorize_service.validateRequestsAndGetMetaData(frontendTokenDTO);
            if(
                    internalFrontendSecurityCheckDTO.isAccesssible()
                            &&
                            internalFrontendSecurityCheckDTO.getRole().equals(RoleTypes.ROLE_ADMIN_SERVICE_HOTEL)
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
