//variables
const signupMainFormContainer = $('#main_signup_form_container');
const signup_username = $('#signup_name');
const checkUsernameContainer = $('#check_username_container');
const next_btn = $('#next_btn');

const invalidFeedbac_signup_username = signup_username.siblings('.invalid-feedback');

///////////////////////////////////////////////////////////////////////////////////////////////////////

//if next button clicked
function checkUsernameAlreadyExists(){
    //send and check ajax request

    //if found
    signupMainFormContainer.css('display','none');
    checkUsernameContainer.css('display','block');

    //show invalid feedback


    //if not found
    // signupMainFormContainer.css('display','block');
    // checkUsernameContainer.css('display','none');
}

$(document).ready(function () {
    const signupNameInput = $('#signup_name');
    const invalidFeedback = signupNameInput.siblings('.invalid-feedback');

    // Add an event listener to the input field to check for validation
    signupNameInput.on('input', function () {
        if (usernameIsValid(signupNameInput.val())) {
            // Username is valid
            signupNameInput.removeClass('is-invalid');
            signupNameInput.addClass('is-valid');
            invalidFeedback.hide(); // Hide the invalid feedback message

            next_btn.prop("disabled", false);
        } else {
            // Username is invalid
            signupNameInput.removeClass('is-valid');
            signupNameInput.addClass('is-invalid');
            invalidFeedback.show(); // Show the invalid feedback message

            next_btn.prop("disabled", true);
        }
    });
});

// Your validation function (replace this with your actual validation logic)
function usernameIsValid(username) {
    // Add your validation logic here
    return username.length >= 3; // Example: username should be at least 3 characters long
}





//Boostrap input fields validations
// starter JavaScript for disabling form submissions if there are invalid fields
(function () {
    'use strict';
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll('.needs-validation');

    // Loop over them and prevent submission
    Array.prototype.slice.call(forms).forEach(function (form) {
        form.addEventListener('submit', function (event) {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            } else {
                // Additional validation for the file input
                const profileImageInput = document.getElementById('signup_profile_image');
                const allowedExtensions = ['jpg', 'jpeg', 'png', 'gif'];
                const maxFileSize = 5 * 1024 * 1024; // 5MB

                if (profileImageInput.files.length > 0) {
                    const file = profileImageInput.files[0];
                    const fileName = file.name.toLowerCase();
                    const fileExtension = fileName.split('.').pop();

                    if (allowedExtensions.includes(fileExtension) && file.size <= maxFileSize) {
                        // validations done
                        ifSubmitClickedformDataValidatedDone();
                    } else {
                        event.preventDefault();
                        event.stopPropagation();
                        profileImageInput.setCustomValidity('Please choose a valid image (JPG, JPEG, PNG, or GIF format) and ensure the file size is less than 5MB.');
                    }
                }
            }
            form.classList.add('was-validated');
        }, false);
    });
})();


//send data into server if clicked signup button
function ifSubmitClickedformDataValidatedDone(){
    console.log("done")
}