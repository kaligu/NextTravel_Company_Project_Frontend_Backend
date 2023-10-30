//-----------------varibales--------
const user_admin_main_pg_loading_model = $('#user_admin_main_pg-loading-model');

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
    // user_admin_main_pg_loading_model.modal('show');

    $.ajax({
        method: "GET",
        contentType: "application/json",
        url: 'http://localhost:1010/main/user-service/user-admin-get-profile-image',
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
                user_admin_main_pg_profile_img.attr('src', 'data:image/png;base64,' + data.data);
                user_admin_main_pg_top_admin_name.text("Mr. "+data.token.access_username+" [Admin]");

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
}

//---------------load admin manage window search all admin views ----------
const user_admin_main_pg_admin_mng_search_admins_txtfld = $("#user_admin_main_pg_admin_mng_search_admins_txtfld");

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
                    <img src="data:image/png;base64, ${admin.profile_image}" alt="Profile Image" style="width: 45px; height: 45px" class="rounded-circle" />
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
                    $('#admin-manage-container-edit-admin-model').find('#e_m_n_a_a_username').val(admin.name);
                    $('#admin-manage-container-edit-admin-model').find('#e_m_n_a_a_nameinitial').val(admin.signup_name_with_initial);
                    $('#admin-manage-container-edit-admin-model').find('#e_m_n_a_a_email').val(admin.email);
                    $('#admin-manage-container-edit-admin-model').find('#e_m_n_a_a_nic').val(admin.nic_or_passport);
                    $('#admin-manage-container-edit-admin-model').find('#e_m_n_a_a_address').val(admin.address);
                    $('#admin-manage-container-edit-admin-model').find('#e_m_n_a_a_salary').val(admin.salary);
                    // Change this line to correctly set the 'src' attribute of the image
                    $('#admin-manage-container-edit-admin-model').find('#e_m_n_a_a_image').attr('src', `data:image/png;base64, ${admin.profile_image}`);


                    // Set other fields as needed

                    $('#admin-manage-container-edit-admin-model').modal('show');
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
    });console.log(user_admin_main_pg_admin_mng_search_admins_txtfld.val());
}