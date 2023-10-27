//////////////////////--------------component variables

const login_container = $('#login_container');

const pswdrecover_pg_loadingModel = $('#pswrdrecover-pg-loading-model');

const pswdrecover_pg_alertModelError = $('#pswdrecover-pg-alert-model-error');
const pswdrecover_pg_alertModelError_title =  $('#pswdrecover-pg-alert-model-title-error');
const pswdrecover_pg_alertModelError_content = $('#pswdrecover-pg-alert-model-content-error');

const pswdrecover_pg_alertModelDone = $('#pswdrecover-pg-alert-model-done');
const pswdrecover_pg_alertModelDone_title =  $('#pswdrecover-pg-alert-model-title-done');
const pswdrecover_pg_alertModelDone_content = $('#pswdrecover-pg-alert-model-content-done');

const pswd_recovery_login_username_txtfld = $('#pswd_recovery_login_username');
const pswd_recovery_login_username_container = $('#forgot_pswd_get_username_container');


//////--------------------------password recovery username

function pswdRecoveryUsernameSearchBtnClicked(){
    //show loading model
    pswdrecover_pg_loadingModel.modal('show');

    $.ajax({
        method: "GET",
        async: false,
        url: 'http://localhost:1010/main/user/search-user-send-otp?username=' + encodeURIComponent(pswd_recovery_login_username_txtfld.val()),
        processData: false,
        contentType: false,
        success: function (data) {
            // if (data.rspd_code === RespondCodes.Respond_THIS_USER_ALREADY_REGISTERED) {
            //     console.log("Done! Checked Username and sent otp to your mail...");
            //     resolve(true);
            // } else {
            //     console.log("Error occured!");
            //     resolve(false);
            // }
            setTimeout(function () {

                //hide loading model
                pswdrecover_pg_loadingModel.modal('hide');

                setTimeout(function () {

                    pswdrecover_pg_alertModelDone_title.text("Welcome Again!");
                    pswdrecover_pg_alertModelDone_content.text("Have a Nice Day Mr."+data.token.access_username);
                    pswdrecover_pg_alertModelDone.modal('show');

                    setTimeout(function () {

                    }, 3100); // delay

                }, 100); // delay

            }, 500); // delay
        },
        error: function (xhr, exception) {
            setTimeout(function () {

                //hide loading model
                pswdrecover_pg_loadingModel.modal('hide');

                setTimeout(function () {

                    pswdrecover_pg_alertModelError_title.text("Username or Password Mismatched!");
                    pswdrecover_pg_alertModelError_content.text("Try again!");
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
const pswd_recovery_login_otp_txtfld = $('#pswd_recovery_login_otp');

function pswdRecoveryLoginOTOCheckBtnClicked(){



}


////////////////////////-------------------------------------------------------------close alert model
//when click alert model's close button close the model
function closeAlertModel(){
    alertModel.modal('hide');
    location.reload();
}