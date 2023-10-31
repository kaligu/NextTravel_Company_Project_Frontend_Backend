//-----------------varibales--------
const user_admin_main_pg_loading_model = $('#user_admin_main_pg-loading-model');

const user_admin_main_pg_alert_model_unauthorise_error = $('#user_admin_main_pg_alert-model-unauthorise-error');

const user_admin_main_pg_alert_model_error = $('#user_admin_main_pg_alert-model-error');
const user_admin_main_pg_alert_model_title_error =  $('#user_admin_main_pg_alert-model-title-error');
const user_admin_main_pg_alert_model_content_error = $('#user_admin_main_pg_alert-model-content-error');

const user_admin_main_pg_alert_model_done = $('#user_admin_main_pg-alert-model-done');
const user_admin_main_pg_alert_model_title_done =  $('#user_admin_main_pg_alert-model-title-done');
const user_admin_main_pg_alert_model_content_done = $('#user_admin_main_pg_alert-model-content-done');


//-----logout-----------
function UserManageConsoleLogout(){
    //show loading model
    user_admin_main_pg_loading_model.modal('show');

    //send request
    $.ajax({
        method: "GET",
        contentType: "application/json",
        url: 'http://localhost:1010/main/user-service/request-to-logout',
        async: true,
        data: {
            access_username: localStorage.getItem("secure_data_user_admin_username"),
            access_jwt_token: localStorage.getItem("secure_data_user_admin_access_token"),
            access_refresh_token: localStorage.getItem("secure_data_user_admin_refresh_token")
        },
        success: function(data) {
            if (data.rspd_code === RespondCodes.Response_SUCCESS) {
                // clear tokens to localStorage
                localStorage.setItem("secure_data_user_admin_username", " ");
                localStorage.setItem("secure_data_user_admin_access_token", " ");
                localStorage.setItem("secure_data_user_admin_refresh_token", " ");

                //hide loading model
                setTimeout(function () {
                    user_admin_main_pg_loading_model.modal('hide');

                    user_admin_main_pg_alert_model_title_done.text("Done");
                    user_admin_main_pg_alert_model_content_done.text("You are logout succesfully!");
                    user_admin_main_pg_alert_model_done.modal('show');

                    setTimeout(function () {
                        user_admin_main_pg_alert_model_done.modal('hide');
                    }, 5000); // delay
                }, 1000); // delay

            } else if(data.rspd_code === RespondCodes.Respond_NOT_AUTHORISED) {
                //hide loading model
                setTimeout(function () {
                    user_admin_main_pg_loading_model.modal('hide');

                    user_admin_main_pg_alert_model_unauthorise_error.modal('show');

                }, 1000); // delay
            }else {
                //hide loading model
                setTimeout(function () {
                    user_admin_main_pg_loading_model.modal('hide');

                    user_admin_main_pg_alert_model_title_error.text("Error has occurd!");
                    user_admin_main_pg_alert_model_content_error.text("Try Again!");
                    user_admin_main_pg_alert_model_error.modal('show');

                    setTimeout(function () {
                        user_admin_main_pg_alert_model_error.modal('hide');
                    }, 1000); // delay
                }, 1000); // delay
                console.log("fail to logout exception");
            }
        },
        error: function(xhr, status, error) {
            //hide loading model
            setTimeout(function () {
                user_admin_main_pg_loading_model.modal('hide');

                user_admin_main_pg_alert_model_title_error.text("Error has occurd!");
                user_admin_main_pg_alert_model_content_error.text("Try Again!");
                user_admin_main_pg_alert_model_error.modal('show');

                setTimeout(function () {
                    user_admin_main_pg_alert_model_error.modal('hide');
                }, 1000); // delay
            }, 1000); // delay
            console.log("fail to logout exception");
        }
    });
}

//---navigate with other containers-----
const home_container = $("#home_container");
const user_manage_container = $("#user_manage_container");
const client_manage_container = $("#client_manage_container");
const admin_manage_container = $("#admin_manage_container");
const finance_data_container = $("#finance_data_container");

const home_nav_icon = $('#home_nav_icon');
const client_nav_icon = $('#client_nav_icon');
const admin_nav_icon = $('#admin_nav_icon');
const finance_nav_icon = $('#finance_nav_icon');

function hideAllContainers(){
    home_container.css('display','none');
    user_manage_container.css('display','none');
    client_manage_container.css('display','none');
    admin_manage_container.css('display','none');
    finance_data_container.css('display','none');

    home_nav_icon.css('fill', 'white');
    client_nav_icon.css('fill', 'white');
    admin_nav_icon.css('fill', 'white');
    finance_nav_icon.css('fill', 'white');
}

function openHomeContainer(){
    hideAllContainers();

    home_container.css('display','block');
    home_nav_icon.css('fill', 'orange');
}

function openClientMngContainer(){
    hideAllContainers();

    client_manage_container.css('display','block');
    client_nav_icon.css('fill', 'orange');
}

function openAdminMngContainer(){
    hideAllContainers();

    admin_manage_container.css('display','block');
    admin_nav_icon.css('fill', 'orange');
}
function openFinanceDataContainer(){
    hideAllContainers();

    finance_data_container.css('display','block');
    finance_nav_icon.css('fill', 'orange');
}

//---Default state home
$(document).ready(function(){
    openHomeContainer();
});
//-----------------------