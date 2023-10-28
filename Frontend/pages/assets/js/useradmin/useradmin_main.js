// //-----------field variables
//
//
// //------------load root methods
// getUsernameProfileimageAndAddIntoUI();
//
// //---------------
//
// //-----------------get username and profile image and add into UI
// function getUsernameProfileimageAndAddIntoUI() {
//     console.log(localStorage.getItem("secure_data_username"));
//     console.log(localStorage.getItem("secure_data_access_token"));
//     console.log(localStorage.getItem("secure_data_refresh_token"));
//
//     const username = localStorage.getItem("secure_data_username");
//     const accessToken = localStorage.getItem("secure_data_access_token");
//     const refreshToken = localStorage.getItem("secure_data_refresh_token");
//
//     const accessData = {
//         "access_username": username,
//         "access_jwt_token": accessToken,
//         "access_refresh_token": refreshToken
//     };
//
//     console.log(accessData);
//     $.ajax({
//         method: "GET",
//         contentType: "application/json",
//         async: true,
//         url: 'http://localhost:1010/main/user-service/user-admin-get-profile-image',
//         data: JSON.stringify(accessData), // Sending data directly without JSON.stringify
//
//         success: function (data) {
//             console.log("rspd_code: " + data.rspd_code);
//             console.log("rspd_message: " + data.rspd_message);
//             console.log("access_username: " + data.access_username);
//             console.log("access_token: " + data.token.access_jwt_token);
//             console.log("refresh_token: " + data.token.access_refresh_token);
//             console.log("data: " + data.data);
//         },
//         error: function (xhr, status, error) {
//             console.log("AJAX Error: " + error+xhr);
//         }
//     });
// }
const user_admin_main_pg_loading_model = $('#user_admin_main_pg-loading-model');

const user_admin_main_pg_profile_img = $("#user_admin_main_pg_profile_img");
const user_admin_main_pg_top_admin_name = $("#user_admin_main_pg_top_admin_name")

$(document).ready(function() {
    // console.log(localStorage.getItem("secure_data_username"));
    // console.log(localStorage.getItem("secure_data_access_token"));
    // console.log(localStorage.getItem("secure_data_refresh_token"));

    //show loading model
    user_admin_main_pg_loading_model.modal('show');

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
                user_admin_main_pg_profile_img.attr('src', 'data:image/png;base64,' + data.data);
                user_admin_main_pg_top_admin_name.text("Mr. "+data.token.access_username+" [Admin]");

                //hide loading model
                user_admin_main_pg_loading_model.modal('hide');

            } else {
                console.log("Profile image retrieval failed");
            }
        },
        error: function(xhr, status, error) {
            console.log("Profile image retrieval failed");
        }
    });
});

