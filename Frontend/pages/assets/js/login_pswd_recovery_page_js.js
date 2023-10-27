//////////////////////--------------component variables

const login_container = $('#login_container');

const intrologin_pg_loadingModel = $('#intro-login-pg-loading-model');

const intrologin_pg_alertModelError = $('#intro-login-pg-alert-model-error');
const intrologin_pg_alertModelError_title =  $('#intro-login-pg-alert-model-title-error');
const intrologin_pg_alertModelError_content = $('#intro-login-pg-alert-model-content-error');

const intrologin_pg_alertModelDone = $('#intro-login-pg-alert-model-done');
const intrologin_pg_alertModelDone_title =  $('#intro-login-pg-alert-model-title-done');
const intrologin_pg_alertModelDone_content = $('#intro-login-pg-alert-model-content-done');

const pswd_recovery_login_username_txtfld = $('#pswd_recovery_login_username');
const pswd_recovery_login_username_container = $('#forgot_pswd_get_username_container');


//////--------------------------password recovery username

function pswdRecoveryUsernameSearchBtnClicked(){
    //show loading model
    intrologin_pg_loadingModel.modal('show');

    $.ajax({
        method: "GET",
        async: false,
        url: 'http://localhost:1010/main/user/search-user-send-otp?username=' + encodeURIComponent(pswd_recovery_login_username_txtfld.val()),
        processData: false,
        contentType: false,
        success: function (data) {
            if (data.rspd_code === RespondCodes.Respond_THIS_USER_ALREADY_REGISTERED) {
                console.log("Done! Checked Username and sent otp to your mail...");
                resolve(true);
            } else {
                console.log("Error occured!");
                resolve(false);
            }
        },
        error: function (xhr, exception) {
            intrologin_pg_loadingModel.modal('hide');
            throw exception;
        }
    });
}

///-------OTP varification
const pswd_recovery_login_otp_txtfld = $('#pswd_recovery_login_otp');

function pswdRecoveryLoginOTOCheckBtnClicked(){



}


////////////////////////-------------------------------------------------------------close alert model
//when click alert model's close button close the model
function closeAlertModel(){
    alertModel.modal('hide');
    location.reload();
}