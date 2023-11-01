//-----------------  varibales  ---------------------------------------
const guide_admin_main_pg_loading_model = $('#guide_admin_main_pg-loading-model');

const guide_admin_main_pg_profile_img = $("#guide_admin_main_pg_profile_img");
const guide_admin_main_pg_top_admin_name = $("#guide_admin_main_pg_top_admin_name")

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

