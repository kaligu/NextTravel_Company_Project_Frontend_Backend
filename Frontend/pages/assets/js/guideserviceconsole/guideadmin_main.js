//-----------------  varibales  ---------------------------------------
const guide_admin_main_pg_loading_model = $('#guide_admin_main_pg-loading-model');

const guide_admin_main_pg_alert_model_unauthorise_error = $('#guide_admin_main_pg_alert-model-unauthorise-error');

const guide_admin_main_pg_alert_model_error = $('#guide_admin_main_pg_alert-model-error');
const guide_admin_main_pg_alert_model_title_error =  $('#guide_admin_main_pg_alert-model-title-error');
const guide_admin_main_pg_alert_model_content_error = $('#guide_admin_main_pg_alert-model-content-error');

const guide_admin_main_pg_alert_model_done = $('#guide_admin_main_pg-alert-model-done');
const guide_admin_main_pg_alert_model_title_done =  $('#guide_admin_main_pg_alert-model-title-done');
const guide_admin_main_pg_alert_model_content_done = $('#guide_admin_main_pg_alert-model-content-done');

const guide_admin_main_pg_profile_img = $("#guide_admin_main_pg_profile_img");
const guide_admin_main_pg_top_admin_name = $("#guide_admin_main_pg_top_admin_name")
const p_s_id = $('#p_s_id');
const p_s_username = $('#p_s_username');
const p_s_email = $('#p_s_email');
const p_s_nameinitial = $('#p_s_nameinitial');
const p_s_nic = $('#p_s_nic');
const p_s_address = $('#p_s_address');
const p_s_image = $('#p_s_image')
const p_s_password = $('#p_s_password');

var profileImage_Base64String = "";

//time
const g_a_time = $('#g_a_time');

//containers
const home_container = $('#home_container');
const add_guide_container = $('#add_guide_container');
const search_guide_container = $('#search_guide_container');
const review_container = $('#review_container');

//side link icons
const home_nav_icon = $('#home_nav_icon');
const new_guide_nav_icon = $('#new_guide_nav_icon');
const view_guide_nav_icon = $('#view_guide_nav_icon');
const review_guide_nav_icon = $('#review_guide_nav_icon');

//add guide
const g_a_a_name = $('#g_a_a_name');
const g_a_a_address = $('#g_a_a_address');
const g_a_a_nic = $('#g_a_a_nic');
const g_a_a_tell = $('#g_a_a_tell');
const g_a_a_experience = $('#g_a_a_experience');
const g_a_a_age = $('#g_a_a_age');
const g_a_a_perdayfee = $('#g_a_a_perdayfee');
const g_a_a_remarks = $('#g_a_a_remarks');
const g_a_a_gender = $('#g_a_a_gender');
const g_a_a_image = $('#g_a_a_image');
const g_a_a_image_input = $('#g_a_a_image_input');
const g_a_a_nic_front_image = $('#g_a_a_nic_front_image');
const g_a_a_nic_front_image_input = $('#g_a_a_nic_front_image_input');
const g_a_a_nic_rear_image = $('#g_a_a_nic_rear_image');
const g_a_a_nic_rear_image_input = $('#g_a_a_nic_rear_image_input');
const g_a_a_savebtn = $('#g_a_a_savebtn');
var guideImage_Base64String = "";
var guidNICFrontImage_Base64String = "";
var guidNOCRearImage_Base64String = "";

//setting validations
let pstxtfld2 = false;
let pstxtfld3 = false;
let pstxtfld4 = false;
let pstxtfld5 = false;
let pstxtfld6 = false;
let pstxtfld7 = true; //image
//---------------------------------------------------------------------

//------------------- navigate containers -------------------------------------------------------

function hideAllContainers(){
    home_container.css('display','none');
    add_guide_container.css('display','none');
    search_guide_container.css('display','none');
    review_container.css('display','none');

    home_nav_icon.css('fill', 'white');
    new_guide_nav_icon.css('fill', 'white');
    view_guide_nav_icon.css('fill', 'white');
    review_guide_nav_icon.css('fill', 'white');
}

function openHomeContainer(){
    localStorage.setItem("guide_admin_console_current_state", "home");

    hideAllContainers();

    home_container.css('display','block');
    home_nav_icon.css('fill', '#00c4ff');
}

function openNewGuideContainer(){
    localStorage.setItem("guide_admin_console_current_state", "new_guide");

    hideAllContainers();

    add_guide_container.css('display','block');
    new_guide_nav_icon.css('fill', '#00c4ff');
}

function openViewGuideContainer(){
    localStorage.setItem("guide_admin_console_current_state", "view_guide");

    hideAllContainers();

    search_guide_container.css('display','block');
    view_guide_nav_icon.css('fill', '#00c4ff');

    //load data into container
    loadDataAfterOpenedViewGuideContainer();
}

function openReveiwGuideContainer(){
    localStorage.setItem("guide_admin_console_current_state", "review_guide");

    hideAllContainers();

    review_container.css('display','block');
    review_guide_nav_icon.css('fill', '#00c4ff');
}
//default state
$(document).ready(function(){
    if(localStorage.getItem("guide_admin_console_current_state")==="home"){
        openHomeContainer();
    }else if(localStorage.getItem("guide_admin_console_current_state")==="new_guide"){
        openNewGuideContainer();
    }else if(localStorage.getItem("guide_admin_console_current_state")==="view_guide"){
        openViewGuideContainer();
    }else if(localStorage.getItem("guide_admin_console_current_state")==="review_guide"){
        openReveiwGuideContainer();
    }else {
        openHomeContainer();
    }
});

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//                                          First load default
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
$(document).ready(function(){

    loadAdminProfileData();

    $('#body').css('display','block');
});



//------------------------------------------------------------------------------------
function loadAdminProfileData(){
    console.log(localStorage.getItem("secure_data_guide_admin_username"));
    console.log(localStorage.getItem("secure_data_guide_admin_access_token"));
    console.log(localStorage.getItem("secure_data_guide_admin_refresh_token"));

    //show loading model
    guide_admin_main_pg_loading_model.modal('show');

    $.ajax({
        method: "GET",
        contentType: "application/json",
        url: 'http://localhost:1010/main/guide-service/guide-admin-get-profile-data',
        async: true,
        data: {
            access_username: localStorage.getItem("secure_data_guide_admin_username"),
            access_jwt_token: localStorage.getItem("secure_data_guide_admin_access_token"),
            access_refresh_token: localStorage.getItem("secure_data_guide_admin_refresh_token")
        },
        success: function(data) {
            if (data.rspd_code === RespondCodes.Response_SUCCESS) {
                // Save tokens to localStorage
                localStorage.setItem("secure_data_guide_admin_username", data.token.access_username);
                localStorage.setItem("secure_data_guide_admin_access_token", data.token.access_jwt_token);
                localStorage.setItem("secure_data_guide_admin_refresh_token", data.token.access_refresh_token);

                // Set image from base64 data
                guide_admin_main_pg_profile_img.attr('src', data.data.profile_image);
                guide_admin_main_pg_top_admin_name.text("Mr. "+data.token.access_username+" [Admin]");

                //fill setting form
                p_s_id.text(data.data.id);
                p_s_username.text(data.data.name);
                p_s_email.val(data.data.email);
                p_s_nameinitial.val(data.data.name_with_initial);
                p_s_nic.val(data.data.nic_or_passport);
                p_s_address.val(data.data.address);
                p_s_image.attr('src', data.data.profile_image);

                profileImage_Base64String =data.data.profile_image;

                //check textflds validations after adding data
                checkSettingsAddedDataAtTextflds();

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

//--------------------------checkSettingsAddedDataAtTextflds()---------------
function checkSettingsAddedDataAtTextflds(){

    //check name with initial
    if(isNameWithInitialRegaxChecked(p_s_nameinitial.val())){
        p_s_nameinitial.removeClass('is-invalid');
        p_s_nameinitial.addClass('is-valid');
        pstxtfld2=true;
    }else{
        p_s_nameinitial.addClass('is-invalid');
        p_s_nameinitial.removeClass('is-valid');
        pstxtfld2=false;
    }

    //check email
    if(isEmailRegaxChecked(p_s_email.val())){
        p_s_email.removeClass('is-invalid');
        p_s_email.addClass('is-valid');
        pstxtfld3=true;
    }else{
        p_s_email.addClass('is-invalid');
        p_s_email.removeClass('is-valid');
        pstxtfld3=false;
    }

    //check address
    if(isAddressRegaxChecked(p_s_address.val())){
        p_s_address.removeClass('is-invalid');
        p_s_address.addClass('is-valid');
        pstxtfld4=true;
    }else{
        p_s_address.addClass('is-invalid');
        p_s_address.removeClass('is-valid');
        pstxtfld4=false;
    }

    //check nic
    if(isNICRegaxChecked(p_s_nic.val())){
        p_s_nic.removeClass('is-invalid');
        p_s_nic.addClass('is-valid');
        pstxtfld5=true;
    }else{
        p_s_nic.addClass('is-invalid');
        p_s_nic.removeClass('is-valid');
        p_s_nic.removeClass('is-valid');
        pstxtfld5=false;
    }

}

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
                profileImage_Base64String = baseString;
            };
            reader.readAsDataURL(newImageFile);
        } else {
            console.error('No file selected');
        }
    });
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
    if(pstxtfld2===true && pstxtfld3===true && pstxtfld4===true && pstxtfld5===true && pstxtfld6===true && pstxtfld7===true){
        // All conditions are true, enable the signup button
        $('#p_s_updateSettingbtn').prop("disabled", false);
    }else{
        $('#p_s_updateSettingbtn').prop("disabled", true);
    }
}

p_s_nameinitial.on('keyup', function () {
    if(isNameWithInitialRegaxChecked(p_s_nameinitial.val())){
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
    if(isEmailRegaxChecked(p_s_email.val())){
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
    if(isAddressRegaxChecked(p_s_address.val())){
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
    if(isNICRegaxChecked(p_s_nic.val())){
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
    if(isPasswordRegaxChecked(p_s_password.val())){
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
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//--------------------------------- Set time to UI------------------------------------
$(document).ready(function(){
    // Function to update the time
    function updateTime() {
        const now = new Date();
        const formattedDate = now.toLocaleString();
        g_a_time.text(formattedDate);
    }
    updateTime();
    setInterval(updateTime, 1000);
});
//------------------------------------------------------------------------------------

//---------------load profile image and username---------------------

// $(document).ready(function() {
//     console.log(localStorage.getItem("secure_data_guide_admin_username"));
//     console.log(localStorage.getItem("secure_data_guide_admin_access_token"));
//     console.log(localStorage.getItem("secure_data_guide_admin_refresh_token"));
//
//     //show loading model
//     guide_admin_main_pg_loading_model.modal('show');
//
//     $.ajax({
//         method: "GET",
//         contentType: "application/json",
//         url: 'http://localhost:1010/main/guide-service/guide-admin-get-profile-image',
//         async: true,
//         data: {
//             access_username: localStorage.getItem("secure_data_guide_admin_username"),
//             access_jwt_token: localStorage.getItem("secure_data_guide_admin_access_token"),
//             access_refresh_token: localStorage.getItem("secure_data_guide_admin_refresh_token")
//         },
//         success: function(data) {
//             if (data.rspd_code === RespondCodes.Response_SUCCESS) {
//                 // Save tokens to localStorage
//                 localStorage.setItem("secure_data_guide_admin_username", data.token.access_username);
//                 localStorage.setItem("secure_data_guide_admin_access_token", data.token.access_jwt_token);
//                 localStorage.setItem("secure_data_guide_admin_refresh_token", data.token.access_refresh_token);
//
//                 // Set image from base64 data
//                 guide_admin_main_pg_profile_img.attr('src', 'data:image/png;base64,' + data.data);
//                 guide_admin_main_pg_top_admin_name.text("Mr. "+data.token.access_username+" [Admin]");
//
//                 //hide loading model
//                 setTimeout(function () {
//                     guide_admin_main_pg_loading_model.modal('hide');
//                 }, 1000); // delay
//
//             } else {
//                 console.log("Profile image retrieval failed");
//             }
//         },
//         error: function(xhr, status, error) {
//             console.log("Profile image retrieval failed");
//         }
//     });
// });

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//                                          Profile Setting update
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//------------------ update on backend Settings if profile Update btn clicked
function saveUpdatedProfileSettings(){
    var formData = new FormData();
    formData.append("id", p_s_id.text());
    formData.append("username", p_s_username.text());
    formData.append("address", p_s_address.val());
    formData.append("email", p_s_email.val());
    formData.append("nic", p_s_nic.val());
    formData.append("password", p_s_password.val());
    formData.append("nameinitial", p_s_nameinitial.val());
    formData.append("profileImage_Base64String", profileImage_Base64String);
    formData.append("access_username", localStorage.getItem("secure_data_guide_admin_username"));
    formData.append("access_jwt_token", localStorage.getItem("secure_data_guide_admin_access_token"));
    formData.append("access_refresh_token", localStorage.getItem("secure_data_guide_admin_refresh_token"));
//show loading model
    guide_admin_main_pg_loading_model.modal('show');

    console.log("success");

    $.ajax({
        method: "POST",
        url: "http://localhost:1010/main/guide-service/guide-admin-update-profile-data",
        data: formData,
        processData: false,  // Prevent jQuery from processing data
        contentType: false,  // Set content type to false to let the browser set it
        success:function (data){
            if(data.rspd_code === RespondCodes.Respond_DATA_SAVED){


                //save tokens on local localStorage - user admin
                localStorage.setItem("secure_data_guide_admin_username", data.token.access_username);
                localStorage.setItem("secure_data_guide_admin_access_token", data.token.access_jwt_token);
                localStorage.setItem("secure_data_guide_admin_refresh_token", data.token.access_refresh_token);


                //hide loading model
                setTimeout(function () {
                    guide_admin_main_pg_loading_model.modal('hide');

                    setTimeout(function () {
                        $('#alert').show();
                        setTimeout(function () {
                            window.location.reload();
                            $('#alert').hide();
                        }, 1000); // delay
                    }, 100); // delay

                }, 1000); // delay
                console.log("fail to logout exception");


            }else{
                //hide loading model
                setTimeout(function () {
                    guide_admin_main_pg_loading_model.modal('hide');

                    guide_admin_main_pg_alert_model_title_error.text("Error has occurd!");
                    guide_admin_main_pg_alert_model_content_error.text("Try Again!");

                }, 1000); // delay
                console.log("fail to logout exception");
            }
        },
        error: function (xhr,exception){
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
    })
}

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//                                          Guide Manage
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//----------------------------------New Guide container -add guide from - guide image set to view if select using base64-------------------
$(document).ready(function() {
    g_a_a_image_input.on('change', function () {
        var newImageFile = this.files[0]; // Get the file when a change occurs in the input
        if (newImageFile) { // Check if a file is selected
            var reader = new FileReader();
            var baseString;
            reader.onload = function () {
                baseString = reader.result;
                g_a_a_image.attr('src', baseString);
                guideImage_Base64String = baseString;
            };
            reader.readAsDataURL(newImageFile);
        } else {
            console.error('No file selected');
        }
    });
});
//-------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------------New Guide container -add guide from - guide ID front view image set to view if select using base64--------------------
$(document).ready(function() {
    g_a_a_nic_front_image_input.on('change', function () {
        var newImageFile = this.files[0]; // Get the file when a change occurs in the input
        if (newImageFile) { // Check if a file is selected
            var reader = new FileReader();
            var baseString;
            reader.onload = function () {
                baseString = reader.result;
                g_a_a_nic_front_image.attr('src', baseString);
                guidNICFrontImage_Base64String= baseString;
            };
            reader.readAsDataURL(newImageFile);
        } else {
            console.error('No file selected');
        }
    });
});
//------------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------------New Guide container -add guide from - guide ID back view image set to view if select using base64--------------------
$(document).ready(function() {
    g_a_a_nic_rear_image_input.on('change', function () {
        var newImageFile = this.files[0]; // Get the file when a change occurs in the input
        if (newImageFile) { // Check if a file is selected
            var reader = new FileReader();
            var baseString;
            reader.onload = function () {
                baseString = reader.result;
                g_a_a_nic_rear_image.attr('src', baseString);
                guidNOCRearImage_Base64String = baseString;
            };
            reader.readAsDataURL(newImageFile);
        } else {
            console.error('No file selected');
        }
    });
});
//-------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------------New Guide container -add guide from - validation all fields-------------------
let g_a_a_fld_1 = false;
let g_a_a_fld_2 = false;
let g_a_a_fld_3 = false;
let g_a_a_fld_4 = false;
let g_a_a_fld_5 = false;
let g_a_a_fld_6 = false;
let g_a_a_fld_7 = false;
let g_a_a_fld_8 = false;
let g_a_a_fld_9 = false;
let g_a_a_fld_10 = false;
let g_a_a_fld_11 = false;
let g_a_a_fld_12 = false;

//validate txt flds
g_a_a_name.on('keyup', function () {
    if(isNameRegaxChecked(g_a_a_name.val())){
        g_a_a_name.removeClass('is-invalid');
        g_a_a_name.addClass('is-valid');
        g_a_a_fld_1=true;
        addGuideSaveBtnIsEnableTrigger();
    }else{
        g_a_a_name.addClass('is-invalid');
        g_a_a_name.removeClass('is-valid');
        g_a_a_fld_1=false;
        addGuideSaveBtnIsEnableTrigger();
    }
});
g_a_a_address.on('keyup', function () {
    if(isAddressRegaxChecked(g_a_a_address.val())){
        g_a_a_address.removeClass('is-invalid');
        g_a_a_address.addClass('is-valid');
        g_a_a_fld_2=true;
        addGuideSaveBtnIsEnableTrigger();
    }else{
        g_a_a_address.addClass('is-invalid');
        g_a_a_address.removeClass('is-valid');
        g_a_a_fld_2=false;
        addGuideSaveBtnIsEnableTrigger();
    }
});
g_a_a_nic.on('keyup', function () {
    if(isNICRegaxChecked(g_a_a_nic.val())){
        g_a_a_nic.removeClass('is-invalid');
        g_a_a_nic.addClass('is-valid');
        g_a_a_fld_3=true;
        addGuideSaveBtnIsEnableTrigger();
    }else{
        g_a_a_nic.addClass('is-invalid');
        g_a_a_nic.removeClass('is-valid');
        g_a_a_fld_3=false;
        addGuideSaveBtnIsEnableTrigger();
    }
});
g_a_a_tell.on('keyup', function () {
    if(isTellRegaxChecked(g_a_a_tell.val())){
        g_a_a_tell.removeClass('is-invalid');
        g_a_a_tell.addClass('is-valid');
        g_a_a_fld_4=true;
        addGuideSaveBtnIsEnableTrigger();
    }else{
        g_a_a_tell.addClass('is-invalid');
        g_a_a_tell.removeClass('is-valid');
        g_a_a_fld_4=false;
        addGuideSaveBtnIsEnableTrigger();
    }
});
g_a_a_experience.on('keyup', function () {
    if(isOnlyNumberRegaxChecked(g_a_a_experience.val())){
        g_a_a_experience.removeClass('is-invalid');
        g_a_a_experience.addClass('is-valid');
        g_a_a_fld_5=true;
        addGuideSaveBtnIsEnableTrigger();
    }else{
        g_a_a_experience.addClass('is-invalid');
        g_a_a_experience.removeClass('is-valid');
        g_a_a_fld_5=false;
        addGuideSaveBtnIsEnableTrigger();
    }
});
g_a_a_age.on('change', function () {
    if(isDOBRegaxChecked(g_a_a_age.val())){
        g_a_a_age.removeClass('is-invalid');
        g_a_a_age.addClass('is-valid');
        g_a_a_fld_6=true;
        addGuideSaveBtnIsEnableTrigger();
    }else{
        g_a_a_age.addClass('is-invalid');
        g_a_a_age.removeClass('is-valid');
        g_a_a_fld_6=false;
        addGuideSaveBtnIsEnableTrigger();
    }
});
g_a_a_perdayfee.on('keyup', function () {
    if(isOnlyNumberRegaxChecked(g_a_a_perdayfee.val())){
        g_a_a_perdayfee.removeClass('is-invalid');
        g_a_a_perdayfee.addClass('is-valid');
        g_a_a_fld_7=true;
        addGuideSaveBtnIsEnableTrigger();
    }else{
        g_a_a_perdayfee.addClass('is-invalid');
        g_a_a_perdayfee.removeClass('is-valid');
        g_a_a_fld_7=false;
        addGuideSaveBtnIsEnableTrigger();
    }
});
g_a_a_remarks.on('keyup', function () {
    if(isTextRegaxChecked(g_a_a_remarks.val())){
        g_a_a_remarks.removeClass('is-invalid');
        g_a_a_remarks.addClass('is-valid');
        g_a_a_fld_8=true;
        addGuideSaveBtnIsEnableTrigger();
    }else{
        g_a_a_remarks.addClass('is-invalid');
        g_a_a_remarks.removeClass('is-valid');
        g_a_a_fld_8=false;
        addGuideSaveBtnIsEnableTrigger();
    }
});
g_a_a_gender.on('change', function () {
    if(isGenderRegaxChecked(g_a_a_gender.val())){
        g_a_a_gender.removeClass('is-invalid');
        g_a_a_gender.addClass('is-valid');
        g_a_a_fld_9=true;
        addGuideSaveBtnIsEnableTrigger();
    }else{
        g_a_a_gender.addClass('is-invalid');
        g_a_a_gender.removeClass('is-valid');
        g_a_a_fld_9=false;
        addGuideSaveBtnIsEnableTrigger();
    }
});

//validate imgs
const g_a_a_nic_frontimageinput = document.getElementById('g_a_a_nic_front_image_input'); //front nic
g_a_a_nic_frontimageinput.onchange = () => {
    const minvalidFeedback = $('#g_a_a_nic_frontimg-invalid-feedback');
    const mvalidFeedback = $('#g_a_a_nic_frontimg-valid-feedback');
    const allowedExtensions = ['jpg', 'jpeg', 'png', 'gif'];
    const maxFileSize = 2 * 1024 * 1024; // 2MB

    if (g_a_a_nic_frontimageinput.files.length > 0) {
        const file = g_a_a_nic_frontimageinput.files[0];
        const fileName = file.name.toLowerCase();
        const fileExtension = fileName.split('.').pop();

        if (allowedExtensions.includes(fileExtension) && file.size <= maxFileSize) {
            g_a_a_nic_frontimageinput.classList.remove('is-invalid');
            g_a_a_nic_frontimageinput.classList.add('is-valid');
            mvalidFeedback.css('display','block');
            minvalidFeedback.css('display','none');
            g_a_a_fld_10=true;
            addGuideSaveBtnIsEnableTrigger();//trigger to enable
        } else {
            g_a_a_nic_frontimageinput.classList.remove('is-valid');
            g_a_a_nic_frontimageinput.classList.add('is-invalid');
            minvalidFeedback.css('display','block');
            mvalidFeedback.css('display','none');
            g_a_a_fld_10=false;
            addGuideSaveBtnIsEnableTrigger();//trigger to enable
        }
    }
};
const g_a_a_nic_rearimageinput = document.getElementById('g_a_a_nic_rear_image_input');  //rear nic
g_a_a_nic_rearimageinput.onchange = () => {
    const minvalidFeedback = $('#g_a_a_nic_rearimg-invalid-feedback');
    const mvalidFeedback = $('#g_a_a_nic_rearimg-valid-feedback');
    const allowedExtensions = ['jpg', 'jpeg', 'png', 'gif'];
    const maxFileSize = 2 * 1024 * 1024; // 2MB

    if (g_a_a_nic_rearimageinput.files.length > 0) {
        const file = g_a_a_nic_rearimageinput.files[0];
        const fileName = file.name.toLowerCase();
        const fileExtension = fileName.split('.').pop();

        if (allowedExtensions.includes(fileExtension) && file.size <= maxFileSize) {
            g_a_a_nic_rearimageinput.classList.remove('is-invalid');
            g_a_a_nic_rearimageinput.classList.add('is-valid');
            mvalidFeedback.css('display','block');
            minvalidFeedback.css('display','none');
            g_a_a_fld_11=true;
            addGuideSaveBtnIsEnableTrigger();//trigger to enable
        } else {
            g_a_a_nic_rearimageinput.classList.remove('is-valid');
            g_a_a_nic_rearimageinput.classList.add('is-invalid');
            minvalidFeedback.css('display','block');
            mvalidFeedback.css('display','none');
            g_a_a_fld_11=false;
            addGuideSaveBtnIsEnableTrigger();//trigger to enable
        }
    }
};
const g_a_a_ImageInput = document.getElementById('g_a_a_image_input');  //profile image
g_a_a_ImageInput.onchange = () => {
    const minvalidFeedback = $('#g_a_a_img-invalid-feedback');
    const mvalidFeedback = $('#g_a_a_img-valid-feedback');
    const allowedExtensions = ['jpg', 'jpeg', 'png', 'gif'];
    const maxFileSize = 2 * 1024 * 1024; // 2MB

    if (g_a_a_ImageInput.files.length > 0) {
        const file = g_a_a_ImageInput.files[0];
        const fileName = file.name.toLowerCase();
        const fileExtension = fileName.split('.').pop();

        if (allowedExtensions.includes(fileExtension) && file.size <= maxFileSize) {
            g_a_a_ImageInput.classList.remove('is-invalid');
            g_a_a_ImageInput.classList.add('is-valid');
            mvalidFeedback.css('display','block');
            minvalidFeedback.css('display','none');
            g_a_a_fld_12=true;
            addGuideSaveBtnIsEnableTrigger();//trigger to enable
        } else {
            g_a_a_ImageInput.classList.remove('is-valid');
            g_a_a_ImageInput.classList.add('is-invalid');
            minvalidFeedback.css('display','block');
            mvalidFeedback.css('display','none');
            g_a_a_fld_12=false;
            addGuideSaveBtnIsEnableTrigger();//trigger to enable
        }
    }
};


function addGuideSaveBtnIsEnableTrigger(){
    if(g_a_a_fld_1===true && g_a_a_fld_2===true && g_a_a_fld_3===true && g_a_a_fld_4===true && g_a_a_fld_5===true && g_a_a_fld_6===true && g_a_a_fld_7===true && g_a_a_fld_8===true && g_a_a_fld_9===true && g_a_a_fld_10===true&& g_a_a_fld_11===true && g_a_a_fld_12===true){
        g_a_a_savebtn.prop("disabled", false);
    }else {
        g_a_a_savebtn.prop("disabled", true);
    }
}
//---------------------------------------------------------------------------------------------------------------

//---------------------------------------------New Guide container - add guide from - save Btn Clicked send data into server-----------------------------
function saveNewGuideBtnClicked(){

    //show loading model
    guide_admin_main_pg_loading_model.modal('show');

    var newformData = new FormData();
    newformData.append("name", g_a_a_name.val());
    newformData.append("address", g_a_a_address.val());
    newformData.append("remarks", g_a_a_remarks.val());
    newformData.append("experience", g_a_a_experience.val());
    newformData.append("nic", g_a_a_nic.val());
    newformData.append("nic_front_view", guidNICFrontImage_Base64String);
    newformData.append("nic_rear_view", guidNOCRearImage_Base64String);
    newformData.append("tell", g_a_a_tell.val());
    newformData.append("gender", g_a_a_gender.val());
    newformData.append("dob", g_a_a_age.val());
    newformData.append("image", guideImage_Base64String);
    newformData.append("perday_fee", g_a_a_perdayfee.val());
    newformData.append("access_username", localStorage.getItem("secure_data_guide_admin_username"));
    newformData.append("access_jwt_token", localStorage.getItem("secure_data_guide_admin_access_token"));
    newformData.append("access_refresh_token", localStorage.getItem("secure_data_guide_admin_refresh_token"));

    $.ajax({
        method: "POST",
        url: "http://localhost:1010/main/guide-service/create-new-guide",
        data: newformData,
        processData: false,  // Prevent jQuery from processing data
        contentType: false,  // Set content type to false to let the browser set it
        success:function (data){
            if(data.rspd_code === RespondCodes.Respond_DATA_SAVED){

                //save tokens on local localStorage - user admin
                localStorage.setItem("secure_data_guide_admin_username", data.token.access_username);
                localStorage.setItem("secure_data_guide_admin_access_token", data.token.access_jwt_token);
                localStorage.setItem("secure_data_guide_admin_refresh_token", data.token.access_refresh_token);
                console.log("done");
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

            }else {
                console.log("error");
                    //hide loading model
                    setTimeout(function () {
                        guide_admin_main_pg_loading_model.modal('hide');

                        guide_admin_main_pg_alert_model_title_error.text("Error has occurd!");
                        guide_admin_main_pg_alert_model_content_error.text("Try Again!");

                    }, 1000); // delay
                    console.log("fail to logout exception");
            }
        },
        error: function (xhr,exception){
            console.log("fail to logout exception");
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
    })

}

//------------------------------------------------------------------------------------------------------------------------------------------------------

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@



//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//                                          View Guides
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//load data
function loadDataAfterOpenedViewGuideContainer(){
    //ajax request and load data into local GuideObjsLocalDB------

    //-----------------------------------------------

    //-----------------load data into table
    //clear table
    $("#adminTableBody").html("");

    //add data into table
    GuideObjsLocalDB.forEach(function(guide) {
        var newRow = $("<tr></tr>");

        newRow.html(`
        <td>
            <p class="fw-normal mb-1">${guide.getGuideName()}</p>
        </td>
        <td>
            <div class="d-flex align-items-center">
                <img src="${guide.getGuideProfileimage()}" alt="" style="width: 60px; height: 60px" class="rounded-circle" />
                <div class="ms-3">
                    <p class="fw-bold mb-1">${guide.getGuideName()}</p>
                </div>
            </div>
        </td>
        <td>
            <p class="fw-normal mb-1">${guide.getGuideAddress()}</p>
        </td>
        <td>
            <p class="fw-normal mb-1">${guide.getGuideNic()}</p>
        </td>
        <td>
            <p class="fw-normal mb-1">${guide.getGuideTell()}</p>
        </td>
        <td>
            <p class="fw-normal mb-1">${guide.getGuideExperience()}</p>
        </td>
        <td>
            <p class="text-muted mb-1">${guide.getGuideDob()}</p>
        </td>
        <td>
            <p class="fw-normal mb-1">${guide.getGuidePerdayfee()}</p>
        </td>
        <td>
            <p class="fw-normal mb-1">${guide.getGuideRemarks()}</p>
        </td>
        <td>
            <p class="fw-normal mb-1">${guide.getGuideGender()}</p>
        </td>
        <td>
            <div class="d-flex align-items-center">
                <img src="${guide.getGuideNicfrontimage()}" alt="" style="width: 280px; height: 140px" />
            </div>
        </td>
        <td>
            <div class="d-flex align-items-center">
                <img src="${guide.getGuideNicrearimage()}" alt="" style="width: 280px; height: 140px" />
            </div>
        </td>
        <td>
                <button type="button" class="btn btn-info text-white edit-btn" data-guide-id="${guide.getGuideID()}">
                    Edit
                </button>
            </td>
            <td>
                <button type="button" class="btn btn-danger delete-btn" data-guide-id="${guide.getGuideID()}">
                    Delete
                </button>
            </td>
    `);

        $("#guideTableBody").append(newRow);
    });

}

//--------------------------Edit & Delete Guide on Guide Table---------------------
// Event listener for Edit button
// Event listener for Edit button
$(document).on("click", ".edit-btn", function() {
    var guideId = $(this).data("guide-id");
    console.log("edited", guideId);
    var guideToEdit = GuideObjsLocalDB.find(guide => guide.getGuideID() === guideId);

    if (guideToEdit) {
        console.log("Name:", guideToEdit.getGuideName());
        // Perform edit actions if guideToEdit is found
    } else {
        console.log("Guide not found for ID:", guideId);
    }
});

$(document).on("click", ".delete-btn", function() {
    var guideId = $(this).data("guide-id");
    console.log("Deleted", guideId);
    var guideToDelete = GuideObjsLocalDB.find(guide => guide.getGuideID() === guideId);

    if (guideToDelete) {
        console.log("Name:", guideToDelete.getGuideName());
        // Perform delete actions if guideToDelete is found
    } else {
        console.log("Guide not found for ID:", guideId);
    }
});


//-------------------------------------------------------------------------

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@



//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//                                          Admin Profile Settings
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@



//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//                                          Review container -graph
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//line
$(document).ready(function () {
    // Get the canvas element
    var ctx = document.getElementById('lineChart').getContext('2d');

    // Sample data for the chart (replace this with your actual data)
    var chartData = {
        labels: ['January', 'February', 'March', 'April', 'May', 'June'],
        datasets: [{
            label: 'Guides Adding Frequency',
            data: [10, 20, 15, 25, 18, 30],
            borderColor: 'rgb(75, 192, 192)',
            tension: 0.1
        }]
    };

    // Create the line chart
    var lineChart = new Chart(ctx, {
        type: 'line',
        data: chartData,
        options: {
            // Additional options for customization if needed
        }
    });
});

//-----------------------------------------------------------------------------