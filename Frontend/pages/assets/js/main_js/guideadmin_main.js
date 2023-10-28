//-----------------varibales--------
const guide_admin_main_pg_loading_model = $('#guide_admin_main_pg-loading-model');

const guide_admin_main_pg_profile_img = $("#guide_admin_main_pg_profile_img");
const guide_admin_main_pg_top_admin_name = $("#guide_admin_main_pg_top_admin_name")
//---------------------------------------------

//---------------load profile image and username---------------------

$(document).ready(function() {
    // console.log(localStorage.getItem("secure_data_username"));
    // console.log(localStorage.getItem("secure_data_access_token"));
    // console.log(localStorage.getItem("secure_data_refresh_token"));

    //show loading model
    guide_admin_main_pg_loading_model.modal('show');

    $.ajax({
        method: "GET",
        contentType: "application/json",
        url: 'http://localhost:1010/main/user-service/user-admin-get-profile-image',
        async: true,
        data: {
            access_username: localStorage.getItem("secure_data_username"),
            access_jwt_token: localStorage.getItem("secure_data_access_token"),
            access_refresh_token: localStorage.getItem("secure_data_refresh_token")
        },
        success: function(data) {
            if (data.rspd_code === RespondCodes.Response_SUCCESS) {
                // Save tokens to localStorage
                localStorage.setItem("secure_data_username", data.token.access_username);
                localStorage.setItem("secure_data_access_token", data.token.access_jwt_token);
                localStorage.setItem("secure_data_refresh_token", data.token.access_refresh_token);

                // Set image from base64 data
                guide_admin_main_pg_profile_img.attr('src', 'data:image/png;base64,' + data.data);
                guide_admin_main_pg_top_admin_name.text("Mr. "+data.token.access_username+" [Admin]");

                //hide loading model
                setTimeout(function () {
                    guide_admin_main_pg_loading_model.modal('hide');
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

