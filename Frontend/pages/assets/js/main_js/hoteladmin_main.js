//-----------------varibales--------
const hotel_admin_main_pg_loading_model = $('#hotel_admin_main_pg-loading-model');

const hotel_admin_main_pg_profile_img = $("#hotel_admin_main_pg_profile_img");
const hotel_admin_main_pg_top_admin_name = $("#hotel_admin_main_pg_top_admin_name")
//---------------------------------------------

//---------------load profile image and username---------------------

$(document).ready(function() {
    console.log(localStorage.getItem("secure_data_hotel_admin_username"));
    console.log(localStorage.getItem("secure_data_hotel_admin_access_token"));
    console.log(localStorage.getItem("secure_data_hotel_admin_refresh_token"));

    //show loading model
    hotel_admin_main_pg_loading_model.modal('show');

    $.ajax({
        method: "GET",
        contentType: "application/json",
        url: 'http://localhost:1010/main/hotel-service/hotel-admin-get-profile-image',
        async: true,
        data: {
            access_username: localStorage.getItem("secure_data_hotel_admin_username"),
            access_jwt_token: localStorage.getItem("secure_data_hotel_admin_access_token"),
            access_refresh_token: localStorage.getItem("secure_data_hotel_admin_refresh_token")
        },
        success: function(data) {
            if (data.rspd_code === RespondCodes.Response_SUCCESS) {
                // Save tokens to localStorage
                localStorage.setItem("secure_data_hotel_admin_username", data.token.access_username);
                localStorage.setItem("secure_data_hotel_admin_access_token", data.token.access_jwt_token);
                localStorage.setItem("secure_data_hotel_admin_refresh_token", data.token.access_refresh_token);

                // Set image from base64 data
                hotel_admin_main_pg_profile_img.attr('src', 'data:image/png;base64,' + data.data);
                hotel_admin_main_pg_top_admin_name.text("Mr. "+data.token.access_username+" [Admin]");

                //hide loading model
                setTimeout(function () {
                    hotel_admin_main_pg_loading_model.modal('hide');
                }, 1000); // delay

            } else {
                console.log("Profile image retrieval failed");
            }
        },
        error: function(xhr, status, error) {
            console.log("Profile image retrieval failed");
        }
    });
});

