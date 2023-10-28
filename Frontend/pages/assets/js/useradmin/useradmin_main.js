//-----------field variables


//------------load root methods
getUsernameProfileimageAndAddIntoUI();

//---------------

//-----------------get username and profile image and add into UI
function getUsernameProfileimageAndAddIntoUI(){


    $.ajax({
        method: "GET",
        contentType: "application/json",
        async: true,
        url: 'http://localhost:1010/main/user-service/user-admin-get-profile-image',
        data: JSON.stringify({
            "access_username": localStorage.getItem("secure_data_username"),
            "access_jwt_token": localStorage.getItem("secure_data_access_token"),
            "access_refresh_token": localStorage.getItem("secure_data_refresh_token")
        }),
            success: function (data) {
                console.log("rspd_code: " + data.rspd_code);
                console.log("rspd_message: " + data.rspd_message);
                console.log("access_username: " + data.access_username);
                console.log("access_token: " + data.token.access_jwt_token);
                console.log("refresh_token: " + data.token.access_refresh_token);
                console.log("data: " + data.data);
            },
            error: function (xhr, exception) {
                console.log("AJAX Error: " + error);
            }
        });
}
