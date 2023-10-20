///////////////////////////////////////////////////////////////////////////////////////////--variables
const signupMainFormContainer = $('#main_signup_form_container');
const signup_username = $('#signup_name');
const checkUsernameContainer = $('#check_username_container');
const next_btn = $('#next_btn');


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--check username interface

//if next button clicked
function nextBtnClicked(){
    signupMainFormContainer.css('display','block');
    checkUsernameContainer.css('display','none');
}

//auto validate & verify Username-checking-form's username
$(document).ready(function () {
    const invalidFeedback = signup_username.siblings('.invalid-feedback');
    const validFeedback = signup_username.siblings('.valid-feedback');

    // Add an event listener to the input field to check for validation
    signup_username.on('input', function () {
        if (usernameIsValid(signup_username.val()) ) {
            // Username is valid
            signup_username.removeClass('is-invalid');
            signup_username.addClass('is-valid');
            validFeedback.hide(); // Hide the invalid feedback message
            invalidFeedback.hide(); // Hide the invalid feedback message


            //check with database
            // URL for the backend endpoint
            const url = 'http://localhost:1010/main/auth/ischeck-username?username=' + encodeURIComponent(usernameIsValid(signup_username.val()));

            // Send a GET request to the backend
            fetch(url, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
            })
                .then((response) => {
                    if (response.ok) {
                        return response.json(); // Assuming your response is in JSON format
                    } else {
                        throw new Error(`Request failed with status: ${response.status}`);
                    }
                })
                .then((data) => {
                    if (data.data === false){
                        validFeedback.text("Sounds Great! Click Next ...")
                        validFeedback.show(); // Show the valid feedback message
                        next_btn.prop("disabled", false);
                    }else{
                        // Username is invalid
                        signup_username.removeClass('is-valid');
                        signup_username.addClass('is-invalid');
                        invalidFeedback.text("This Username Already taken! Enter a another username here...")
                        invalidFeedback.show(); // Show the invalid feedback message
                    }

                })
                .catch((error) => {
                    console.error(error);
                    // Username is invalid
                    signup_username.removeClass('is-valid');
                    signup_username.addClass('is-invalid');
                    invalidFeedback.text("This Username Already taken! Enter a another username here...")
                    invalidFeedback.show(); // Show the invalid feedback message
                });
        } else {
            // Username is invalid
            signup_username.removeClass('is-valid');
            signup_username.addClass('is-invalid');
            invalidFeedback.text(" Please enter a valid Username here! It must include over the three characters...")
            invalidFeedback.show(); // Show the invalid feedback message

            next_btn.prop("disabled", true);
        }
    });
});

// validation function
function usernameIsValid(username) {
    // validation logic
    return username.length >= 3; //
}

///////////////////////////////////////////////////////////////////////////////////////////////////////signup form submitting interface





// //Boostrap input fields validations
// // starter JavaScript for disabling form submissions if there are invalid fields
// (function () {
//     'use strict';
//     // Fetch all the forms we want to apply custom Bootstrap validation styles to
//     var forms = document.querySelectorAll('.needs-validation');
//
//     // Loop over them and prevent submission
//     Array.prototype.slice.call(forms).forEach(function (form) {
//         form.addEventListener('submit', function (event) {
//             if (!form.checkValidity()) {
//                 event.preventDefault();
//                 event.stopPropagation();
//             } else {
//                 // Additional validation for the file input
//                 const profileImageInput = document.getElementById('signup_profile_image');
//                 const allowedExtensions = ['jpg', 'jpeg', 'png', 'gif'];
//                 const maxFileSize = 5 * 1024 * 1024; // 5MB
//
//                 if (profileImageInput.files.length > 0) {
//                     const file = profileImageInput.files[0];
//                     const fileName = file.name.toLowerCase();
//                     const fileExtension = fileName.split('.').pop();
//
//                     if (allowedExtensions.includes(fileExtension) && file.size <= maxFileSize) {
//                         // validations done
//
//                     } else {
//                         event.preventDefault();
//                         event.stopPropagation();
//                         profileImageInput.setCustomValidity('Please choose a valid image (JPG, JPEG, PNG, or GIF format) and ensure the file size is less than 5MB.');
//                     }
//                 }
//             }
//             form.classList.add('was-validated');
//         }, false);
//     });
// })();
