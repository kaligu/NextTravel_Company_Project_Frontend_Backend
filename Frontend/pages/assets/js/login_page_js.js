//////////////////////--------------component variables

const pswd_recovery_login_username_txtfld = $('#pswd_recovery_login_username');

const loadingModel = $('#loading-model');

const alertModel = $('#alert-model-send-otp');
const alertModel_title =  $('#alert-model-title');
const alertModel_content = $('#alert-model-content');

////////---------- login operation
//fields variables
let loginUsername =  $('#login_Username');
let loginPassword =  $('#login_Password');
const  user_login_btn = $('#user_login_btn');

let logintxtfld1=false;
let logintxtfld2=false;

function isUsernameCheckedRegexLogin(username) {
    const regex = /^[a-zA-Z0-9_.-]{5,30}$/;
    return regex.test(username);
}
function isSignUpPasswordCheckedRegexLogin(password) {
    const regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
    return regex.test(password);
}

//check regax action signup_password
loginUsername.on('keyup', function () {
    const tinvalidFeedback = loginUsername.siblings('.invalid-feedback');
    const tvalidFeedback = loginUsername.siblings('.valid-feedback');
    if (isUsernameCheckedRegexLogin(loginUsername.val())) {
        loginUsername.removeClass('is-invalid');
        loginUsername.addClass('is-valid');
        tvalidFeedback.show();
        tinvalidFeedback.hide();

        logintxtfld1=true;

        readyToEnableLoginBtn();//trigger to enable
    } else {
        loginUsername.removeClass('is-valid');
        loginUsername.addClass('is-invalid');
        tinvalidFeedback.show();
        tvalidFeedback.hide();

        logintxtfld1=false;

        readyToEnableLoginBtn();//trigger to enable
    }
});

//check regax action signup_password
loginPassword.on('keyup', function () {
    const tinvalidFeedback = loginPassword.siblings('.invalid-feedback');
    const tvalidFeedback = loginPassword.siblings('.valid-feedback');
    if (isSignUpPasswordCheckedRegexLogin(loginPassword.val())) {
        loginPassword.removeClass('is-invalid');
        loginPassword.addClass('is-valid');
        tvalidFeedback.show();
        tinvalidFeedback.hide();

        logintxtfld2=true;

        readyToEnableLoginBtn();//trigger to enable
    } else {
        loginPassword.removeClass('is-valid');
        loginPassword.addClass('is-invalid');
        tinvalidFeedback.show();
        tvalidFeedback.hide();

        logintxtfld2=false;

        readyToEnableLoginBtn();//trigger to enable
    }
});

function readyToEnableLoginBtn() {
    if (logintxtfld1 === true && logintxtfld2 === true ) {
        // All conditions are true, enable the signup button
        user_login_btn.prop("disabled", false);
    }
}

function loginFormBtnClicked() {
    //clear old saved secure datas
    localStorage.setItem("secure_data_username", "");
    localStorage.setItem("secure_data_jwt_access_token", "");
    localStorage.setItem("secure_data_refresh_token", "");

    //show loading model
    loadingModel.modal('show');

    $.ajax({
        method:"GET",
        contentType:"application/json",
        url:"http://localhost:1010/main/user/user-login",
        async:true,
        data: {
            username: loginUsername.val(),
            password: loginPassword.val()
        },
        success:function (data){
            // console.log("rspd_code : "+data.rspd_code);
            // console.log("rspd_message : "+data.rspd_message)
            // console.log("access_username : "+data.access_username)
            // console.log("access_token : "+data.token.access_jwt_token)
            // console.log("refresh_token : "+data.token.access_refresh_token)
            // console.log("data : "+data.data)
            //
            // /////////////////////////////////////////////
            // //save tokens on local localStorage
            // localStorage.setItem("secure_data_username", data.token.access_username);
            // localStorage.setItem("secure_data_access_token", data.token.access_jwt_token);
            // localStorage.setItem("secure_data_refresh_token", data.token.access_refresh_token);
            //
            // ////////////////////////////////////////////////
            //
            // //hide loading model
            // loadingModel.modal('hide');
            //
            // //check role and move there page
            // if(data.data === "ROLE_CLIENT"){
            //     window.location.href = '../pages/user_adminpage.html';
            // }else if(data.data === "ROLE_ADMIN_SERVICE_VEHICLE"){
            //     window.location.href = '../pages/vehicle_adminpage.html';
            // }else if(data.data === "ROLE_ADMIN_SERVICE_HOTEL"){
            //     window.location.href = '../pages/hotel_adminpage.html';
            // }else if(data.data === "ROLE_ADMIN_SERVICE_USER"){
            //     window.location.href = '../pages/user_adminpage.html';
            // }else if (data.data === "ROLE_ADMIN_SERVICE_TRAVELPACKAGE"){
            //     window.location.href = '../pages/travelpackage_adminpage.html';
            // }else if (data.data === "ROLE_ADMIN_SERVICE_GUIDE"){
            //     window.location.href = '../pages/guide_adminpage.html';
            // }

        },
        error: function (xhr,exception){
            //hide loading model
            loadingModel.modal('hide');

            loadingModel.on('hidden.bs.modal', function () {
                // Show alert after the modal is completely hidden

                alertModel_title.innerText = "Error has occured!";
                alertModel_content.innerText = xhr+","+exception;
                alertModel.modal('show');

                // Remove event listener to avoid multiple executions
                loadingModel.off('hidden.bs.modal');
            });
        }
    })
}

//////--------------------------password recovery username

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

///-------OTP varification
const pswd_recovery_login_otp_txtfld = $('#pswd_recovery_login_otp');

function pswdRecoveryLoginOTOCheckBtnClicked(){

}