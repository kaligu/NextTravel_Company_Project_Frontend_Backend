//////////////////////--------------component variables

const pswd_recovery_login_username_txtfld = $('#pswd_recovery_login_username');

const loadingModel = $('#loading-model');

const alertModel = $('#alert-model-send-otp');
const alertModel_title =  $('#alert-model-title');
const alertModel_content = $('#alert-model-content');

///////////////////////////////////////////////

function pswdRecoveryLoginSearchBtnClicked(){
    //show loading model
    loadingModel.modal('show');

    $.ajax({
        method: "GET",
        async: true,
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
            resolve(false);
            console.log("Exception occured!");
        }
    });
}