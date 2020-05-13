
function setPasswordConfirmValidity(str) {
        const password1 = document.getElementById('password1');
        const password2 = document.getElementById('password2');

        if (password1.value === password2.value) {
             password2.setCustomValidity('');
        } else {
            password2.setCustomValidity('parolele nu se potrivesc');
        }
    }