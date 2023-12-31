

const user_admin_main_pg_profile_img = $("#user_admin_main_pg_profile_img");
const user_admin_main_pg_top_admin_name = $("#user_admin_main_pg_top_admin_name")
//---------------------------------------------

$(document).ready(function() {
    //admin manage window
    loadUserAdminProfileImageAndUsername();
    loadAdminMngWindoewSearchAdminTabel();
});

//---------------load profile image and username ---------------------


function loadUserAdminProfileImageAndUsername(){
    // console.log(localStorage.getItem("secure_data_user_admin_username"));
    // console.log(localStorage.getItem("secure_data_user_admin_access_token"));
    // console.log(localStorage.getItem("secure_data_user_admin_refresh_token"));

    //show loading model
    user_admin_main_pg_loading_model.modal('show');

    $.ajax({
        method: "GET",
        contentType: "application/json",
        url: 'http://localhost:1010/main/user-service/user-admin-get-profile-data',
        async: true,
        data: {
            access_username: localStorage.getItem("secure_data_user_admin_username"),
            access_jwt_token: localStorage.getItem("secure_data_user_admin_access_token"),
            access_refresh_token: localStorage.getItem("secure_data_user_admin_refresh_token")
        },
        success: function(data) {
            if (data.rspd_code === RespondCodes.Response_SUCCESS) {
                // Save tokens to localStorage
                localStorage.setItem("secure_data_user_admin_username", data.token.access_username);
                localStorage.setItem("secure_data_user_admin_access_token", data.token.access_jwt_token);
                localStorage.setItem("secure_data_user_admin_refresh_token", data.token.access_refresh_token);

                // Set image from base64 data
                user_admin_main_pg_profile_img.attr('src', data.data.profile_image);
                user_admin_main_pg_top_admin_name.text("Mr. "+data.token.access_username+" [Admin]");

                //fill setting form
                p_s_username.val(data.data.name);
                p_s_email.val(data.data.email);
                p_s_nameinitial.val(data.data.name_with_initial);
                p_s_nic.val(data.data.nic_or_passport);
                p_s_address.val(data.data.address);
                p_s_image.attr('src', data.data.profile_image);


                //check textflds validations after adding data
                checkSettingsAddedDataAtTextflds();

                //hide loading model
                setTimeout(function () {
                    user_admin_main_pg_loading_model.modal('hide');
                }, 1000); // delay

            } else {
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

//---------------load admin manage window search all admin views ----------
const user_admin_main_pg_admin_mng_search_admins_txtfld = $("#user_admin_main_pg_admin_mng_search_admins_txtfld");
const admin_manage_container_edit_admin_model = $("#admin-manage-container-edit-admin-model");

user_admin_main_pg_admin_mng_search_admins_txtfld.on('keyup', function () {
    loadAdminMngWindoewSearchAdminTabel();
});

function loadAdminMngWindoewSearchAdminTabel(){
    //show loading model
    // user_admin_main_pg_loading_model.modal('show');

    $.ajax({
        method: "GET",
        contentType: "application/json",
        url: 'http://localhost:1010/main/user-service/admin-mng-get-all-admins',
        async: true,
        data: {
            search_keyword: user_admin_main_pg_admin_mng_search_admins_txtfld.val(),
            access_username: localStorage.getItem("secure_data_user_admin_username"),
            access_jwt_token: localStorage.getItem("secure_data_user_admin_access_token"),
            access_refresh_token: localStorage.getItem("secure_data_user_admin_refresh_token")
        },
        success: function(data) {
            if (data.rspd_code === RespondCodes.Response_SUCCESS) {
                // Save tokens to localStorage
                localStorage.setItem("secure_data_user_admin_username", data.token.access_username);
                localStorage.setItem("secure_data_user_admin_access_token", data.token.access_jwt_token);
                localStorage.setItem("secure_data_user_admin_refresh_token", data.token.access_refresh_token);

                //clear table
                $("#adminTableBody").html("");
                // Assuming data.data is an array of admins
                data.data.forEach(function(admin) {

                    var newRow = $("<tr></tr>");

                    newRow.html(`
            <td>
                <p class="fw-bold mb-1">${admin.id}</p>
            </td>
            <td>
                <p class="fw-bold mb-1">${admin.role_type}</p>
            </td>
            <td>
                <div class="d-flex align-items-center">
                    <img src="${admin.profile_image}" alt="Profile Image" style="width: 45px; height: 45px" class="rounded-circle" />
                    <div class="ms-3">
                        <p class="fw-bold mb-1">${admin.name}</p>
                    </div>
                </div>
            </td>
            <td>
                <p class="fw-normal mb-1">${admin.signup_name_with_initial}</p>
            </td>
            <td>
                <p class="text-muted mb-1">${admin.email}</p>
            </td>
            <td>
                <p class="fw-normal mb-1">${admin.nic_or_passport}</p>
            </td>
            <td>
                <p class="fw-normal mb-1">${admin.address}</p>
            </td>
            <td>
                <p class="fw-normal mb-1">${admin.salary}</p>
            </td>
            <td>
            <button type="button" id="edit-btn${admin.id}" class="btn btn-link btn-sm btn-rounded edit-btn" data-admin-id="${admin.id}">Edit</button>
        </td>
    
        `);
                    $("#adminTableBody").append(newRow);
                });//end array loop

                $(".edit-btn").on('click', function() {
                    var adminId = $(this).data('admin-id');

                    // Assuming 'adminData' is the array of admin objects
                    var admin = data.data.find(function(item) {
                        return item.id === adminId;
                    });

                    // Populate modal with admin's information
                    // Set modal field values with corresponding admin data
                    admin_manage_container_edit_admin_model.find('#e_m_n_a_a_username').val(admin.name);
                    admin_manage_container_edit_admin_model.find('#e_m_n_a_a_nameinitial').val(admin.signup_name_with_initial);
                    admin_manage_container_edit_admin_model.find('#e_m_n_a_a_email').val(admin.email);
                    admin_manage_container_edit_admin_model.find('#e_m_n_a_a_nic').val(admin.nic_or_passport);
                    admin_manage_container_edit_admin_model.find('#e_m_n_a_a_address').val(admin.address);
                    admin_manage_container_edit_admin_model.find('#e_m_n_a_a_salary').val(admin.salary);

                    // Change this line to correctly set the 'src' attribute of the image
                    admin_manage_container_edit_admin_model.find('#e_m_n_a_a_image').attr('src', `${admin.profile_image}`);


                    admin_manage_container_edit_admin_model.modal('show');
                });
                //hide loading model
                // setTimeout(function () {
                //     user_admin_main_pg_loading_model.modal('hide');
                // }, 1000); // delay

            } else {
                console.log("Profile image retrieval failed");
            }
        },
        error: function(xhr, status, error) {
            console.log("Profile image retrieval failed");
        }
    });
    console.log(user_admin_main_pg_admin_mng_search_admins_txtfld.val());
}

const newImageFile = $('#plus-image')[0].files[0];

//if ecit save btn clicked action
function saveEditAdmin(){
    // Retrieve data from text fields
    var username = $('#e_m_n_a_a_username').val();
    var nameWithInitial = $('#e_m_n_a_a_nameinitial').val();
    var email = $('#e_m_n_a_a_email').val();
    var nicOrPassport = $('#e_m_n_a_a_nic').val();
    var address = $('#e_m_n_a_a_address').val();
    var salary = $('#e_m_n_a_a_salary').val();
    //image


    console.log("image "+$('#e_m_n_a_a_image').attr('src'));
    console.log("new "+newImageFile);
    // For demonstration, printing the retrieved data to the console
    console.log('Username:', username);
    console.log('Name with Initial:', nameWithInitial);
    console.log('Email:', email);
    console.log('NIC / Passport:', nicOrPassport);
    console.log('Physical Address:', address);
    console.log('Salary:', salary);

    //check image added or not
    if (newImageFile) {

    } else {

    }
}

// admin edit save form image add after action
$(document).ready(function() {
    $('#plus-image').on('change', function () {
        var newImageFile = this.files[0]; // Get the file when a change occurs in the input

        if (newImageFile) { // Check if a file is selected
            var reader = new FileReader();
            var baseString;
            reader.onload = function () {
                baseString = reader.result;

                // Update the 'src' attribute of the image element
                $('#e_m_n_a_a_image').attr('src', baseString);
            };
            reader.readAsDataURL(newImageFile);
        } else {
            console.error('No file selected');
        }
    });
});


//--------------------------checkSettingsAddedDataAtTextflds()---------------
function checkSettingsAddedDataAtTextflds(){
    //check username
    if(isUsernameCheckedRegex(p_s_username.val())){
        p_s_username.removeClass('is-invalid');
        p_s_username.addClass('is-valid');
        pstxtfld1 = true;
    }else{
        p_s_username.addClass('is-invalid');
        p_s_username.removeClass('is-valid');
        pstxtfld1 = false;
    }

    //check name with initial
    if(isSignUpNameWithInitialCheckedRegex(p_s_nameinitial.val())){
        p_s_nameinitial.removeClass('is-invalid');
        p_s_nameinitial.addClass('is-valid');
        pstxtfld2=true;
    }else{
        p_s_nameinitial.addClass('is-invalid');
        p_s_nameinitial.removeClass('is-valid');
        pstxtfld2=false;
    }

    //check email
    if(isSignUpEmailCheckedRegex(p_s_email.val())){
        p_s_email.removeClass('is-invalid');
        p_s_email.addClass('is-valid');
        pstxtfld3=true;
    }else{
        p_s_email.addClass('is-invalid');
        p_s_email.removeClass('is-valid');
        pstxtfld3=false;
    }

    //check address
    if(isSignUpNAddressCheckedRegex(p_s_address.val())){
        p_s_address.removeClass('is-invalid');
        p_s_address.addClass('is-valid');
        pstxtfld4=true;
    }else{
        p_s_address.addClass('is-invalid');
        p_s_address.removeClass('is-valid');
        pstxtfld4=false;
    }

    //check nic
    if(isSignUpNicOrPassportCheckedRegex(p_s_nic.val())){
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

function isUsernameCheckedRegex(username) {
    const regex = /^[a-zA-Z0-9_.-]{5,30}$/;
    return regex.test(username);
}

function isSignUpEmailCheckedRegex(email) {
    const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return regex.test(email);
}

function isSignUpNameWithInitialCheckedRegex(name) {
    const regex = /^[A-z._\-]+$/;
    return regex.test(name);
}

function isSignUpPasswordCheckedRegex(password) {
    const regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
    return regex.test(password);
}

function isSignUpNicOrPassportCheckedRegex(nic) {
    const regex = /^[a-zA-Z0-9_-]+\S{8,11}$/;
    return regex.test(nic);
}
function isSignUpNAddressCheckedRegex(addresse) {
    const regex = /^\S+\s*[a-zA-Z0-9,.-]+\S{0,48}$/;
    return regex.test(addresse);
}