///////////////////////////////////////////////////////////////////////////////////////////--variables

const signupMainFormContainer = $('#main_signup_form_container');
const checkUsernameContainer = $('#check_username_container');

const loadingModel = $('#loading-model');

const alertModel = $('#alert-model');
const alertModel_title =  $('#alert-model-title');
const alertModel_content = $('#alert-model-content');

//////////////////////////////////////////////////////////// //////////////////////////////////////////////////////////////--check username interface

//if next button clicked
function nextBtnClicked(){
    signupMainFormContainer.css('display','block');
    checkUsernameContainer.css('display','none');
}

//-------------------------------------------------------------check Username txtfld validations
const signup_username = $('#signup_name');
const invalidFeedback = signup_username.siblings('.invalid-feedback');
const validFeedback = signup_username.siblings('.valid-feedback');
const next_btn = $('#next_btn'); // Replace with the actual button selector
const invalid_para_dbcheck = $('#invalid-para-dbcheck');

// Add an event listener to the input field to check for validation
signup_username.on('input', async function () {
    const username = signup_username.val();
    if (isUsernameCheckedRegex(username)) {
        signup_username.removeClass('is-invalid');
        signup_username.addClass('is-valid');
        validFeedback.hide();
        invalidFeedback.hide();

        // Check if the username is available in the database
        try {
            const isAvailable = await isUsernameAvailable(username);
            if (isAvailable) {
                signup_username.addClass('is-valid');
                signup_username.removeClass('is-invalid');

                invalid_para_dbcheck.hide(); // Use .show()
                next_btn.prop("disabled", false); // Enable the button
            } else {
                signup_username.removeClass('is-valid');
                signup_username.addClass('is-invalid');

                invalid_para_dbcheck.show(); // Use .hide()
                next_btn.prop("disabled", true); // Disable the button
            }
        } catch (error) {
            console.log("Error checking username availability:", error);
        }
    } else {
        invalid_para_dbcheck.hide(); // Use .hide()
        // Username is invalid
        signup_username.removeClass('is-valid');
        signup_username.addClass('is-invalid');
        invalidFeedback.text("Please enter a valid Username here! It must include over three characters...");
        invalidFeedback.show();

        next_btn.prop("disabled", true); // Disable the button
    }
});

function isUsernameCheckedRegex(username) {
    const regex = /^[a-zA-Z0-9_.-]{5,30}$/;
    return regex.test(username);
}

async function isUsernameAvailable(username) {
    try {
        const url = 'http://localhost:1010/main/auth/ischeck-username?username=' + encodeURIComponent(username);
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (response.ok) {
            // console.log("ok");
            return true;  //you can save this
        }else {
            return false;
        }
    } catch (error) {
        console.log("Error: " + error.message);
        return false;
    }
}


//---------------------------------------------------------------------

///////////////////////////////////////////////////////////////////////////////////////////////////////signup form submitting interface

    //fields variables
    const signup_name_with_initial =  $('#signup_name_with_initial');
    const signup_email = $('#signup_email');
    const signup_password = $('#signup_password');
    const signup_nic_or_passport = $('#signup_nic_or_passport');
    const signup_address = $('#signup_address');

    //disable signup button
    const user_reg_signup_btn = $('#user_reg_signup_btn');
    user_reg_signup_btn.prop("disabled", true); //disable

    let txtfld1=false;
    let txtfld2=false;
    let txtfld3=false;
    let txtfld4=false;
    let txtfld5=false;
    let txtfld6=false;

function isSignUpEmailCheckedRegex(email) {
    const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return regex.test(email);
}

function isSignUpNameWithInitialCheckedRegex(name) {
    const regex = /([A-Z])\w+\s([A-Z])\w*\s*([A-Z])*(?=,*)/;
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

//check regax action signup_email
signup_email.on('keyup', function () {
    const tinvalidFeedback = signup_email.siblings('.invalid-feedback');
    const tvalidFeedback = signup_email.siblings('.valid-feedback');
    if (isSignUpEmailCheckedRegex(signup_email.val())) {
        signup_email.removeClass('is-invalid');
        signup_email.addClass('is-valid');
        tvalidFeedback.show();
        tinvalidFeedback.hide();

        txtfld1=true;

        readyToEnableSignupBtn();//trigger to enable
    } else {
        signup_email.removeClass('is-valid');
        signup_email.addClass('is-invalid');
        tinvalidFeedback.show();
        tvalidFeedback.hide();

        txtfld1=false;

        readyToEnableSignupBtn();//trigger to enable
    }
});

//check regax action signup_name_with_initial
signup_name_with_initial.on('keyup', function () {
    const tinvalidFeedback = signup_name_with_initial.siblings('.invalid-feedback');
    const tvalidFeedback = signup_name_with_initial.siblings('.valid-feedback');
    if (isSignUpNameWithInitialCheckedRegex(signup_name_with_initial.val())) {
        signup_name_with_initial.removeClass('is-invalid');
        signup_name_with_initial.addClass('is-valid');
        tvalidFeedback.show();
        tinvalidFeedback.hide();

        txtfld2=true;

        readyToEnableSignupBtn();//trigger to enable
    } else {
        signup_name_with_initial.removeClass('is-valid');
        signup_name_with_initial.addClass('is-invalid');
        tinvalidFeedback.show();
        tvalidFeedback.hide();

        txtfld2=false;

        readyToEnableSignupBtn();//trigger to enable
    }
});

//check regax action signup_password
signup_password.on('keyup', function () {
    const tinvalidFeedback = signup_password.siblings('.invalid-feedback');
    const tvalidFeedback = signup_password.siblings('.valid-feedback');
    if (isSignUpPasswordCheckedRegex(signup_password.val())) {
        signup_password.removeClass('is-invalid');
        signup_password.addClass('is-valid');
        tvalidFeedback.show();
        tinvalidFeedback.hide();

        txtfld3=true;

        readyToEnableSignupBtn();//trigger to enable
    } else {
        signup_password.removeClass('is-valid');
        signup_password.addClass('is-invalid');
        tinvalidFeedback.show();
        tvalidFeedback.hide();

        txtfld3=false;

        readyToEnableSignupBtn();//trigger to enable
    }
});

//check regax action signup_nic_or_passport
signup_nic_or_passport.on('keyup', function () {
    const tinvalidFeedback = signup_nic_or_passport.siblings('.invalid-feedback');
    const tvalidFeedback = signup_nic_or_passport.siblings('.valid-feedback');
    if (isSignUpNicOrPassportCheckedRegex(signup_nic_or_passport.val())) {
        signup_nic_or_passport.removeClass('is-invalid');
        signup_nic_or_passport.addClass('is-valid');
        tvalidFeedback.show();
        tinvalidFeedback.hide();

        txtfld4=true;

        readyToEnableSignupBtn();//trigger to enable
    } else {
        signup_nic_or_passport.removeClass('is-valid');
        signup_nic_or_passport.addClass('is-invalid');
        tinvalidFeedback.show();
        tvalidFeedback.hide();

        txtfld4=false;

        readyToEnableSignupBtn();//trigger to enable
    }
});

//check regax action signup_password
signup_address.on('keyup', function () {
    const tinvalidFeedback = signup_address.siblings('.invalid-feedback');
    const tvalidFeedback = signup_address.siblings('.valid-feedback');
    if (isSignUpNAddressCheckedRegex(signup_address.val())) {
        signup_address.removeClass('is-invalid');
        signup_address.addClass('is-valid');
        tvalidFeedback.show();
        tinvalidFeedback.hide();

        txtfld5=true;

        readyToEnableSignupBtn();//trigger to enable
    } else {
        signup_address.removeClass('is-valid');
        signup_address.addClass('is-invalid');
        tinvalidFeedback.show();
        tvalidFeedback.hide();

        txtfld5=false;

        readyToEnableSignupBtn();//trigger to enable
    }
});

//validate image
const profileImageInput = document.getElementById('signup_profile_image');
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
            mvalidFeedback.css('display','block');
            minvalidFeedback.css('display','none');

            txtfld6=true;

            readyToEnableSignupBtn();//trigger to enable
        } else {
            profileImageInput.classList.remove('is-valid');
            profileImageInput.classList.add('is-invalid');
            minvalidFeedback.css('display','block');
            mvalidFeedback.css('display','none');

            txtfld6=false;

            readyToEnableSignupBtn();//trigger to enable
        }
    }
};

function readyToEnableSignupBtn() {
    if (txtfld1 === true && txtfld2 === true && txtfld3 === true && txtfld4 === true && txtfld5 === true && txtfld6 === true) {
        // All conditions are true, enable the signup button
        user_reg_signup_btn.prop("disabled", false);
    }
}

function sendSignupDataIntoServer(){

    //show loading model
    loadingModel.modal('show');

    //fields variables
    let form_signup_name = signup_username.val();
    let form_signup_name_with_initial = signup_name_with_initial.val();
    let form_signup_email = signup_email.val();
    let form_signup_password = signup_password.val();
    let form_signup_nic_or_passport = signup_nic_or_passport.val();
    let form_signup_address = signup_address.val();
    let form_signup_profile_image = $('#signup_profile_image')[0].files[0];

    var formData = new FormData();
    formData.append("signup_name", form_signup_name);
    formData.append("signup_name_with_initial", form_signup_name_with_initial);
    formData.append("signup_email", form_signup_email);
    formData.append("signup_password", form_signup_password );
    formData.append("signup_nic_or_passport", form_signup_nic_or_passport);
    formData.append("signup_address", form_signup_address);
    formData.append("signup_profile_image", form_signup_profile_image);

    console.log(form_signup_name);
    console.log(form_signup_name_with_initial);
    console.log(form_signup_email);
    console.log(form_signup_password);
    console.log(form_signup_nic_or_passport);
    console.log(form_signup_address);
    console.log(form_signup_profile_image);

    //send JSON to signup
    // Send the form data to the server
    $.ajax({

        method: "POST",
        headers: {'Authorization':'dddd'},
        async: true,
        url: "http://localhost:1010/main/auth/signup-guestuser",

        processData: false,
        contentType: false,
        data: formData,
        success: function (data) {
            location.reload();

            //hide loading model
            loadingModel.modal('hide');

            loadingModel.on('hidden.bs.modal', function () {
                // Show alert after the modal is completely hidden
                alertModel_title.text("Done");
                alertModel_content.text("New User registered successfully!");
                alertModel.modal('show');

                // Remove the event listener to avoid multiple executions
                loadingModel.off('hidden.bs.modal');
            });
        },
        error: function (xhr, exception) {
            location.reload();

            //hide loading model
            loadingModel.modal('hide');

            loadingModel.on('hidden.bs.modal', function () {
                // Show alert after the modal is completely hidden

                alertModel_title.text("Error has occurred!");
                alertModel_content.text(xhr + ", " + exception);
                alertModel.modal('show');

                // Remove event listener to avoid multiple executions
                loadingModel.off('hidden.bs.modal');
            });
        }
    })
}

////////////////////////-------------------------------------------------------------close alert model
//when click alert model's close button close the model
function closeAlertModel(){
    alertModel.modal('hide');
}