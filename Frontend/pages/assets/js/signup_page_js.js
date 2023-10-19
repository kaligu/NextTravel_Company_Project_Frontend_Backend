
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