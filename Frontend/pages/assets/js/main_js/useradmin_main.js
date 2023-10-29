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
                document.getElementById("adminTableBody").innerHTML = "";
                // Assuming data.data is an array of admins
                data.data.forEach(function(admin) {
                    // Example of accessing admin properties
                    // Assuming data.data is an array of admins
                    // Create a new table row
                    var newRow = document.createElement("tr");

                    // Add table data for each admin property
                    newRow.innerHTML = `
        <td>
            <p class="fw-normal mb-1">${admin.id}</p>
        </td>
        <td>
            <p class="fw-normal mb-1">${admin.role_type}</p>
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
            <button type="button" class="btn btn-link btn-sm btn-rounded">Edit</button>
        </td>
        <td>
            <button type="button" class="btn btn-link btn-sm btn-rounded">Delete</button>
        </td>
    `;

                    // Append the new row to the table body
                    document.getElementById("adminTableBody").appendChild(newRow);
                    // Process each admin as needed and populate in the UI
                    // For example, add them to a table or generate HTML elements to display admin information.
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