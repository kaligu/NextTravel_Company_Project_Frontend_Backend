const guide_admin_main_pg_loading_model = $('#guide_admin_main_pg-loading-model');

const guide_admin_main_pg_alert_model_unauthorise_error = $('#guide_admin_main_pg_alert-model-unauthorise-error');

const guide_admin_main_pg_alert_model_error = $('#guide_admin_main_pg_alert-model-error');
const guide_admin_main_pg_alert_model_title_error =  $('#guide_admin_main_pg_alert-model-title-error');
const guide_admin_main_pg_alert_model_content_error = $('#guide_admin_main_pg_alert-model-content-error');

//load profile image
$(document).ready(function(){

    loadAdminProfileData();
});

function loadAdminProfileData(){
    console.log(localStorage.getItem("secure_data_client_username"));
    console.log(localStorage.getItem("secure_data_client_access_token"));
    console.log(localStorage.getItem("secure_data_client_refresh_token"));

    //show loading model
    guide_admin_main_pg_loading_model.modal('show');

    $.ajax({
        method: "GET",
        contentType: "application/json",
        url: 'http://localhost:1010/main/user/user-get-profile-data',
        async: true,
        data: {
            access_username: localStorage.getItem("secure_data_client_username"),
            access_jwt_token: localStorage.getItem("secure_data_client_access_token"),
            access_refresh_token: localStorage.getItem("secure_data_client_refresh_token")
        },
        success: function(data) {
            if (data.rspd_code === RespondCodes.Response_SUCCESS) {
                // Save tokens to localStorage
                localStorage.setItem("secure_data_client_username", data.token.access_username);
                localStorage.setItem("secure_data_client_access_token", data.token.access_jwt_token);
                localStorage.setItem("secure_data_client_refresh_token", data.token.access_refresh_token);

                // Set image from base64 data

                // guide_admin_main_pg_top_admin_name.text("Mr. "+data.token.access_username+" [Admin]");

                //fill setting form
                // p_s_id.text(data.data.id);
                // p_s_username.text(data.data.name);
                // p_s_email.val(data.data.email);
                // p_s_nameinitial.val(data.data.name_with_initial);
                // p_s_nic.val(data.data.nic_or_passport);
                // p_s_address.val(data.data.address);
                $('#profile_image').attr('src', data.data.profile_image);


                //hide loading model
                setTimeout(function () {
                    guide_admin_main_pg_loading_model.modal('hide');

                    setTimeout(function () {
                        $('#alert').show();
                        setTimeout(function () {
                            $('#alert').hide();
                        }, 1000); // delay
                    }, 100); // delay

                }, 1000); // delay

            } else {
                //hide loading model
                setTimeout(function () {
                    guide_admin_main_pg_loading_model.modal('hide');

                    guide_admin_main_pg_alert_model_title_error.text("Error has occurd!");
                    guide_admin_main_pg_alert_model_content_error.text("Try Again!");
                    guide_admin_main_pg_alert_model_error.modal('show');

                }, 1000); // delay
                console.log("fail to logout exception");
            }
        },
        error: function(xhr, status, error) {
            if (xhr.status === 401){
                setTimeout(function () {
                    guide_admin_main_pg_loading_model.modal('hide');

                    guide_admin_main_pg_alert_model_unauthorise_error.modal('show');

                }, 1000); // delay
            }else {
                //hide loading model
                setTimeout(function () {
                    guide_admin_main_pg_loading_model.modal('hide');

                    guide_admin_main_pg_alert_model_title_error.text("Error has occurd!");
                    guide_admin_main_pg_alert_model_content_error.text("Try Again!");
                    guide_admin_main_pg_alert_model_error.modal('show');

                }, 1000); // delay
                console.log("fail to logout exception");
            }
        }
    });
}
