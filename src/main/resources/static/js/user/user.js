const user = {
    init: function() {
        const _this = this;

        $('#email').on('blur', function() {
            _this.emailValidate();
        });

        $('#password').on('keyup', function() {
            _this.passwordValidate();
        });

        $('#password-confirm').on('keyup', function() {
            _this.passwordEqual();
        });

        $('#birth-day').on('blur', function() {
            _this.birthValidate();
        });

        $('#name').on('blur', function() {
            _this.nameValidate();
        })
/*
        $('#btn-email-valid').on('click', function() {
            _this.emailAjax();
        });
*/
        $('#password-reset').on('click', function() {
            $('#password').attr('readonly', false);
            $('#password').val('');
            $('#password-confirm').val('');
            $('#password-warn').css('display', 'block');
            $('#password-confirm-good').css('display', 'none');
            $('#btn-user-save').attr('disabled', true);
            $('#password-warning').css('display', 'block');
            $('#password-length-warning').css('display', 'block');
            $('#password-good').css('display', 'none');
        })

    },
    passwordEqual: function() {
        const myPassword = $('#password').val();
        const confirmPassword = $('#password-confirm').val();
        if(myPassword !== confirmPassword) {
            $('#password-warn').css('display', 'block');
            $('#password-confirm-good').css('display', 'none');
            $('#btn-user-save').attr('disabled', true);
        } else {
            $('#password-warn').css('display', 'none');
            $('#password-confirm-good').css('display', 'block');
            $('#btn-user-save').attr('disabled', false);
            $('#password').attr('readonly', true);
        }
    },
    passwordValidate: function() {
        const password = $('#password').val();

        const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;

        let isGoodPattern;
        let isGoodLength;

        if(passwordPattern.test(password)) {
            $('#password-warning').css('display', 'none');
            isGoodPattern = true;
        } else {
            $('#password-warning').css('display', 'block');
            isGoodPattern = false;
        }
        if(password.length < 9 || password.length > 13) {
            $('#password-length-warning').css('display', 'block');
            isGoodLength = false;
        } else {
            $('#password-length-warning').css('display', 'none');
            isGoodLength = true;
        }

        if(isGoodLength && isGoodPattern) {
            $('#password-good').css('display', 'block');
            $('#btn-user-save').attr('disabled', false);
        } else {
            $('#password-good').css('display', 'none');
            $('#btn-user-save').attr('disabled', true);

        }

    },
    birthValidate: function() {
        const birthYear = $('#birth-year option:selected').val();
        const birthMonth = $('#birth-month option:selected').val();
        const birthDay = $('#birth-day option:selected').val();
        if(birthYear === 'none' || birthMonth === 'none' || birthDay === 'none'){
            $('#birth-warn').css('display', 'block');
            $('#birth-good').css('display', 'none');
            $('#btn-user-save').attr('disabled', true);
        } else {
            $('#birth-warn').css('display', 'none');
            $('#birth-good').css('display', 'block');
            $('#btn-user-save').attr('disabled', false);
        }
        $('#birth').val(birthYear + '' + birthMonth + '' + birthDay);
    },

    emailValidate: function() {
        const email = $('#email').val();
        const emailRegex = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
        if(!emailRegex.test(email)) {
            $('#email-warning').css('display', 'block');
            $('#email-good').css('display', 'none');
            $('#btn-user-save').attr('disabled', true);
        } else {
            $('#email-warning').css('display', 'none');
            $('#email-good').css('display', 'block');
            $('#btn-user-save').attr('disabled', false);
        }
    },
    emailAjax: function() {
        const email = $('#email').val();

        $.ajax({
            url: '/emailValid',
            method: '/post',
            data: email,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function(data) {
            alert('사용 가능한 이메일 입니다.');
            $('#btn-email-valid').attr('disabled', true);
        }).fail(function() {

        })
    },
    nameValidate: function() {
        const nameValue = $('#name').val();
        if(nameValue.length <= 1) {
            $('#name-warning').css('display', 'block');
            $('#name-good').css('display', 'none');
            $('#btn-user-save').attr('disabled', false);
        } else {
            $('#name-warning').css('display', 'none');
            $('#name-good').css('display', 'block');
            $('#btn-user-save').attr('disabled', false);
        }
    }
}

user.init();