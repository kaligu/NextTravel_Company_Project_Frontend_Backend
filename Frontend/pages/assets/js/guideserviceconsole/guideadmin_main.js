//-----------------  varibales  ---------------------------------------
const guide_admin_main_pg_loading_model = $('#guide_admin_main_pg-loading-model');

const guide_admin_main_pg_profile_img = $("#guide_admin_main_pg_profile_img");
const guide_admin_main_pg_top_admin_name = $("#guide_admin_main_pg_top_admin_name")

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

//------------------------------------------------------------------------------------

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
    console.log("profile name"+g_a_a_name.val());
    console.log("profile address"+g_a_a_address.val());
    console.log("profile nic"+g_a_a_nic.val());
    console.log("profile tell"+g_a_a_tell.val());
    console.log("profile expereience"+g_a_a_experience.val());
    console.log("profile dob"+g_a_a_age.val());
    console.log("profile perdayfee"+g_a_a_perdayfee.val());
    console.log("profile remarks"+g_a_a_remarks.val());
    console.log("profile gender"+g_a_a_gender.val());
    console.log("profile image"+guideImage_Base64String);
    console.log("profile image nic front"+guidNICFrontImage_Base64String);
    console.log("profile image nic rear"+guidNOCRearImage_Base64String);
    g_a_a_nic_rear_image.attr('src', guideImage_Base64String);
}
//------------------------------------------------------------------------------------------------------------------------------------------------------

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@



//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//                                          View Guides
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//-------------Hotel Objects array

let GuideObjsLocalArray = new Array();

//---------------------------


//-----------------------------------Regax Checking Methods---------------------------------

//------------------------------------------------------------------------------------------