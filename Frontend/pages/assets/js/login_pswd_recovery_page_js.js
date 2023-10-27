//////////////////////--------------component variables

const pswdrecover_pg_loadingModel = $('#pswrdrecover-pg-loading-model');

const pswdrecover_pg_alertModelError = $('#pswdrecover-pg-alert-model-error');
const pswdrecover_pg_alertModelError_title =  $('#pswdrecover-pg-alert-model-title-error');
const pswdrecover_pg_alertModelError_content = $('#pswdrecover-pg-alert-model-content-error');

const pswdrecover_pg_alertModelDone = $('#pswdrecover-pg-alert-model-done');
const pswdrecover_pg_alertModelDone_title =  $('#pswdrecover-pg-alert-model-title-done');
const pswdrecover_pg_alertModelDone_content = $('#pswdrecover-pg-alert-model-content-done');

const pswd_recovery_login_username_txtfld = $('#pswd_recovery_login_username');

const pswd_recovery_login_otp_txtfld = $('#pswd_recovery_login_otp_txtfld');

const pswdrecover_pg_forgot_pswd_get_otp_container = $('#forgot_pswd_get_otp_container');
const pswdrecover_pg_forgot_pswd_get_username_container = $('#forgot_pswd_get_username_container');

const pswdrecover_pg_pswdadding_model = $('#pswrdrecover-pg-pswdadding-model');

let pswd_recovery_login_username_txtfld_value;
let pswd_recovery_login_otp_txtfld_value;

//////--------------------------password recovery username

function pswdRecoveryUsernameSearchBtnClicked(){
    pswd_recovery_login_username_txtfld_value = pswd_recovery_login_username_txtfld.val();

    //show loading model
    pswdrecover_pg_loadingModel.modal('show');

    $.ajax({
        method: "GET",
        async: true,
        url: 'http://localhost:1010/main/user/ischeck-username-and-send-otp?username=' + encodeURIComponent(pswd_recovery_login_username_txtfld_value),
        processData: false,
        contentType: false,
        success: function (data) {
            setTimeout(function () {

                //hide loading model
                pswdrecover_pg_loadingModel.modal('hide');

                setTimeout(function () {
                    if (data.rspd_code === RespondCodes.Respond_THIS_USER_ALREADY_REGISTERED) {
                        pswdrecover_pg_alertModelDone_title.text("OTP sent to you'r Mail...");
                        pswdrecover_pg_alertModelDone_content.text("Please check your e-mail account for the OTP we just sent you and enter that ...");
                        pswdrecover_pg_alertModelDone.modal('show');

                        setTimeout(function () {
                            pswdrecover_pg_alertModelDone.modal('hide');

                            pswdrecover_pg_forgot_pswd_get_username_container.css('display','none');
                            pswdrecover_pg_forgot_pswd_get_otp_container.css('display','block');

                        }, 3300); // delay
                    } else {
                        pswdrecover_pg_alertModelError_title.text("Not Found!");
                        pswdrecover_pg_alertModelError_content.text("This User not Registerd yet.Try again or register please!");
                        pswdrecover_pg_alertModelError.modal('show');

                        setTimeout(function () {
                            location.reload(); //reload
                        }, 2800); // delay
                    }

                }, 100); // delay

            }, 500); // delay
        },
        error: function (xhr, exception) {
            setTimeout(function () {

                //hide loading model
                pswdrecover_pg_loadingModel.modal('hide');

                setTimeout(function () {

                    pswdrecover_pg_alertModelError_title.text("Try Again!");
                    pswdrecover_pg_alertModelError_content.text("Register or Type another one ...!");
                    pswdrecover_pg_alertModelError.modal('show');

                    setTimeout(function () {
                        location.reload(); //reload
                    }, 2800); // 2000 milliseconds

                }, 100); // 2000 milliseconds

            }, 500); // 2000 milliseconds
        }
    });
}

///-------OTP varification
function pswdRecoveryLoginOTOCheckBtnClicked(){
    pswd_recovery_login_otp_txtfld_value = pswd_recovery_login_otp_txtfld.val();

    //show loading model
    pswdrecover_pg_loadingModel.modal('show');

    $.ajax({
        method: "GET",
        async: true,
        url: 'http://localhost:1010/main/user/isverify-username-with-otp?username=' + encodeURIComponent(pswd_recovery_login_username_txtfld_value)+'&otp='+encodeURIComponent(pswd_recovery_login_otp_txtfld_value),
        processData: false,
        contentType: false,
        success: function (data) {
            setTimeout(function () {

                //hide loading model
                pswdrecover_pg_loadingModel.modal('hide');

                setTimeout(function () {
                    if (data.rspd_code === RespondCodes.Respond_USERNAME_AND_OTP_VERIFIED) {
                        pswdrecover_pg_alertModelDone_title.text("OTP Matched...");
                        pswdrecover_pg_alertModelDone_content.text("Create a new password and you can login here ...");
                        pswdrecover_pg_alertModelDone.modal('show');

                        setTimeout(function () {
                            //open to enter password
                            pswdrecover_pg_pswdadding_model.modal('show');
                            pswdrecover_pg_alertModelDone.modal('hide');

                        }, 1000); // delay
                    } else {
                        pswdrecover_pg_alertModelError_title.text("OTP Not Matched!");
                        pswdrecover_pg_alertModelError_content.text("Type correct OTP and Try again...!");
                        pswdrecover_pg_alertModelError.modal('show');
                    }

                }, 100); // delay

            }, 500); // delay
        },
        error: function (xhr, exception) {
            setTimeout(function () {

                //hide loading model
                pswdrecover_pg_loadingModel.modal('hide');

                setTimeout(function () {

                    pswdrecover_pg_alertModelError_title.text("OTP Not Matched!");
                    pswdrecover_pg_alertModelError_content.text("Type correct OTP and Try again...!");
                    pswdrecover_pg_alertModelError.modal('show');

                    setTimeout(function () {
                        pswdrecover_pg_alertModelError.modal('hide');

                    }, 1000); // delay

                }, 100); // 2000 milliseconds

            }, 500); // 2000 milliseconds
        }
    });
}
