//-----------------varibales--------
const user_admin_main_pg_loading_model = $('#user_admin_main_pg-loading-model');

const user_admin_main_pg_alert_model_unauthorise_error = $('#user_admin_main_pg_alert-model-unauthorise-error');

const user_admin_main_pg_alert_model_error = $('#user_admin_main_pg_alert-model-error');
const user_admin_main_pg_alert_model_title_error =  $('#user_admin_main_pg_alert-model-title-error');
const user_admin_main_pg_alert_model_content_error = $('#user_admin_main_pg_alert-model-content-error');

const user_admin_main_pg_alert_model_done = $('#user_admin_main_pg-alert-model-done');
const user_admin_main_pg_alert_model_title_done =  $('#user_admin_main_pg_alert-model-title-done');
const user_admin_main_pg_alert_model_content_done = $('#user_admin_main_pg_alert-model-content-done');

const p_s_username = $('#p_s_username');
const p_s_email = $('#p_s_email');
const p_s_nameinitial = $('#p_s_nameinitial');
const p_s_nic = $('#p_s_nic');
const p_s_address = $('#p_s_address');
const p_s_image = $('#p_s_image')
const p_s_password = $('#p_s_password');

let pstxtfld1 = false;
let pstxtfld2 = false;
let pstxtfld3 = false;
let pstxtfld4 = false;
let pstxtfld5 = false;
let pstxtfld6 = false;
let pstxtfld7 = true; //image

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

                        window.open("http://localhost:63342/NextTravel_Company_Project_Frontend_Backend/Frontend/index.html?_ijt=52ammnccq57t2dg07p5m351hpu&_ij_reload=RELOAD_ON_SAVE");
                    }, 5000); // delay
                }, 1000); // delay

            }else {

                //hide loading model
                setTimeout(function () {
                    user_admin_main_pg_loading_model.modal('hide');

                    user_admin_main_pg_alert_model_title_error.text("Error has occurd!");
                    user_admin_main_pg_alert_model_content_error.text("Try Again!");
                    user_admin_main_pg_alert_model_error.modal('show');

                }, 1000); // delay
                console.log("fail to logout exception");
            }
        },
        error: function(xhr, status, error) {
            if (xhr.status === 401){
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

                }, 1000); // delay
                console.log("fail to logout exception");
            }
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

//------- profile setting image set ------------
$(document).ready(function() {
    $('#p_s_image_input').on('change', function () {
        var newImageFile = this.files[0]; // Get the file when a change occurs in the input

        if (newImageFile) { // Check if a file is selected
            var reader = new FileReader();
            var baseString;
            reader.onload = function () {
                baseString = reader.result;

                // Update the 'src' attribute of the image element
                p_s_image.attr('src', baseString);
            };
            reader.readAsDataURL(newImageFile);
        } else {
            console.error('No file selected');
        }
    });
});

//--------------update profile Settings---------


//validations



p_s_username.on('keyup', function () {
    if(isUsernameCheckedRegex(p_s_username.val())){
        p_s_username.removeClass('is-invalid');
        p_s_username.addClass('is-valid');
        pstxtfld1 = true;
        settingUpdateBtnTrigger();
    }else{
        p_s_username.addClass('is-invalid');
        p_s_username.removeClass('is-valid');
        pstxtfld1 = false;
        settingUpdateBtnTrigger();
    }
});

p_s_nameinitial.on('keyup', function () {
    if(isSignUpNameWithInitialCheckedRegex(p_s_nameinitial.val())){
        p_s_nameinitial.removeClass('is-invalid');
        p_s_nameinitial.addClass('is-valid');
        pstxtfld2=true;
        settingUpdateBtnTrigger();
    }else{
        p_s_nameinitial.addClass('is-invalid');
        p_s_nameinitial.removeClass('is-valid');
        pstxtfld2=false;
        settingUpdateBtnTrigger();
    }

});

p_s_email.on('keyup', function () {
    if(isSignUpEmailCheckedRegex(p_s_email.val())){
        p_s_email.removeClass('is-invalid');
        p_s_email.addClass('is-valid');
        pstxtfld3=true;
        settingUpdateBtnTrigger();
    }else{
        p_s_email.addClass('is-invalid');
        p_s_email.removeClass('is-valid');
        pstxtfld3=false;
        settingUpdateBtnTrigger();
    }
});

p_s_address.on('keyup', function () {
    if(isSignUpNAddressCheckedRegex(p_s_address.val())){
        p_s_address.removeClass('is-invalid');
        p_s_address.addClass('is-valid');
        pstxtfld4=true;
        settingUpdateBtnTrigger();
    }else{
        p_s_address.addClass('is-invalid');
        p_s_address.removeClass('is-valid');
        pstxtfld4=false;
        settingUpdateBtnTrigger();
    }
});

p_s_nic.on('keyup', function () {
    if(isSignUpNicOrPassportCheckedRegex(p_s_nic.val())){
        p_s_nic.removeClass('is-invalid');
        p_s_nic.addClass('is-valid');
        pstxtfld5=true;
        settingUpdateBtnTrigger();
    }else{
        p_s_nic.addClass('is-invalid');
        p_s_nic.removeClass('is-valid');
        pstxtfld5=false;
        settingUpdateBtnTrigger();
    }
});

p_s_password.on('keyup', function () {
    if(isSignUpPasswordCheckedRegex(p_s_password.val())){
        p_s_password.removeClass('is-invalid');
        p_s_password.addClass('is-valid');
        pstxtfld6=true;
        settingUpdateBtnTrigger();
    }else{
        p_s_password.addClass('is-invalid');
        p_s_password.removeClass('is-valid');
        pstxtfld6=false;
        settingUpdateBtnTrigger();
    }
});

const profileImageInput = document.getElementById('p_s_image_input');
profileImageInput.onchange = () => {
    const minvalidFeedback = $('#img-invalid-feedback');
    const mvalidFeedback = $('#img-valid-feedback');
    const allowedExtensions = ['jpg', 'jpeg', 'png', 'gif'];
    const maxFileSize = 2 * 1024 * 1024; // 2MB


    if (profileImageInput.files.length > 0) {
        const file = profileImageInput.files[0];
        const fileName = file.name.toLowerCase();
        const fileExtension = fileName.split('.').pop();

        if (allowedExtensions.includes(fileExtension) && file.size <= maxFileSize) {
            profileImageInput.classList.remove('is-invalid');
            profileImageInput.classList.add('is-valid');
            mvalidFeedback.css('display', 'block');
            minvalidFeedback.css('display', 'none');

            pstxtfld7 = true;

            settingUpdateBtnTrigger();//trigger to enable
        } else {
            profileImageInput.classList.remove('is-valid');
            profileImageInput.classList.add('is-invalid');
            minvalidFeedback.css('display', 'block');
            mvalidFeedback.css('display', 'none');

            pstxtfld7 = false;

            settingUpdateBtnTrigger();//trigger to enable
        }
    }
};

function settingUpdateBtnTrigger(){
if(pstxtfld1===true && pstxtfld2===true && pstxtfld3===true && pstxtfld4===true && pstxtfld5===true && pstxtfld6===true && pstxtfld7===true){
    // All conditions are true, enable the signup button
    $('#p_s_updateSettingbtn').prop("disabled", false);
}else{
    $('#p_s_updateSettingbtn').prop("disabled", true);
}
}
//------------------ update on backend Settings

function saveUpdatedProfileSettings(){

}