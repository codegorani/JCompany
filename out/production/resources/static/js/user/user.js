let isAuth = false;
let isEmail = false;
let isPassword = false;
let isConfirmPassword = false;
let isValidDate = false;
let isName = false;
const user = {
    init: function () {
        const _this = this;

        $('#btn-inactive-check').on('click', function() {
            _this.inactiveCheck();
        })

        $('#email').on('keyup', function () {
            _this.emailValidate();
        });

        $('#password').on('keyup', function () {
            _this.passwordValidate();
        });

        $('#password-confirm').on('keyup', function () {
            _this.passwordEqual();
        });

        $('#newPass').on('keyup', function() {
            _this.passwordChangeValidate();
        });

        $('#newPassCheck').on('keyup', function() {
            _this.passwordChangeEqual();
        });

        $('#btn-password-change').on('click', function() {
            _this.changePassword();
        })

        $('#birth-day').on('change', function () {
            _this.birthValidate();
        });
        $('#birth-month').on('change', function () {
            _this.birthValidate();
        });
        $('#birth-year').on('focus', function () {
            _this.birthValidate();
        });

        $('#name').on('blur', function () {
            _this.nameValidate();
        })

        $('#btn-email-valid').on('click', function () {
            _this.emailValid();
        });

        /*$('#password-reset').on('click', function () {
            $('#password').attr('readonly', false);
            $('#password').val('');
            $('#password-confirm').val('');
            $('#password-warn').css('display', 'block');
            $('#password-confirm-good').css('display', 'none');
            $('#btn-user-save').attr('disabled', true);
            $('#password-warning').css('display', 'block');
            $('#password-length-warning').css('display', 'block');
            $('#password-good').css('display', 'none');
        });*/

        $('#btn-user-delete').on('click', function () {
            const userEmail = $('#email').val();
            const userInputEmail = prompt('정말로 삭제하시겠습니까? 삭제하시려면 이메일을 다시 입력하십시오.');
            if (userEmail === userInputEmail) {
                _this.deleteUser();
            }
        });

        $('#btn-user-save').on('click', function(e) {
            if((isConfirmPassword && isEmail && isName && isPassword && isValidDate) !== true) {
                if(!isEmail) {
                    _this.emailValidate();
                    $('#email').focus();
                } else if(!isPassword) {
                    _this.passwordValidate();
                    $('#password').focus();
                } else if(!isConfirmPassword) {
                    _this.passwordEqual();
                    $('#password-confirm').focus();
                } else if(!isName){
                    _this.nameValidate();
                    $('#name').focus();
                }else if(!isValidDate) {
                    _this.birthValidate();
                    $('#birth-year').focus();
                }
                alert('작성 항목을 다시 검토하세요.');
                e.preventDefault();
                return;
            }
            if(!isAuth) {
                alert('이메일 인증을 먼저 진행하세요.')
                e.preventDefault();
            }
            if(!confirm('가입하시겠습니까?')) {
                e.preventDefault();
                alert('가입이 완료되었습니다.');
            }
        });

        $('#btn-forgot-email').on('click', function() {
            _this.forgotEmail();
        });

        $('#btn-email-exist').on('click', function() {
            _this.emailExist();
        });

        $('#btn-forgot-password').on('click', function() {
            _this.forgotPassword();
        });

        $('#btn-password-forgot-change').on('click', function() {
            _this.changePasswordAsForgot();
        });
    }
    ,
    passwordEqual: function () {
        const myPassword = $('#password').val();
        const confirmPassword = $('#password-confirm').val();
        if (myPassword !== confirmPassword) {
            $('#password-warn').css('display', 'block');
            $('#password-confirm-good').css('display', 'none');
            isConfirmPassword = false;
        } else {
            $('#password-warn').css('display', 'none');
            $('#password-confirm-good').css('display', 'block');
            //$('#password').attr('readonly', true);
            isConfirmPassword = true;
        }
    },
    passwordValidate: function () {
        const password = $('#password').val();

        const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;

        let isGoodPattern;
        let isGoodLength;

        if (passwordPattern.test(password)) {
            $('#password-warning').css('display', 'none');
            isGoodPattern = true;
        } else {
            $('#password-warning').css('display', 'block');
            isGoodPattern = false;
        }
        if (password.length < 9 || password.length > 13) {
            $('#password-length-warning').css('display', 'block');
            isGoodLength = false;
        } else {
            $('#password-length-warning').css('display', 'none');
            isGoodLength = true;
        }

        if (isGoodLength && isGoodPattern) {
            $('#password-good').css('display', 'block');
            isPassword = true;
        } else {
            $('#password-good').css('display', 'none');
            isPassword = false;
        }

    },
    passwordChangeEqual: function () {
        const myPassword = $('#newPass').val();
        const confirmPassword = $('#newPassCheck').val();
        if (myPassword !== confirmPassword) {
            $('#password-warn').css('display', 'block');
            $('#password-confirm-good').css('display', 'none');
            isConfirmPassword = false;
        } else {
            $('#password-warn').css('display', 'none');
            $('#password-confirm-good').css('display', 'block');
            //$('#password').attr('readonly', true);
            isConfirmPassword = true;
        }
    },
    passwordChangeValidate: function () {
        const password = $('#newPass').val();

        const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;

        let isGoodPattern;
        let isGoodLength;

        if (passwordPattern.test(password)) {
            $('#password-warning').css('display', 'none');
            isGoodPattern = true;
        } else {
            $('#password-warning').css('display', 'block');
            isGoodPattern = false;
        }
        if (password.length < 9 || password.length > 13) {
            $('#password-length-warning').css('display', 'block');
            isGoodLength = false;
        } else {
            $('#password-length-warning').css('display', 'none');
            isGoodLength = true;
        }

        if (isGoodLength && isGoodPattern) {
            $('#password-good').css('display', 'block');
            isPassword = true;
        } else {
            $('#password-good').css('display', 'none');
            isPassword = false;
        }

    },
    birthValidate: function () {
        const birthYear = $('#birth-year option:selected').val();
        const birthMonth = $('#birth-month option:selected').val();
        const birthDay = $('#birth-day option:selected').val();
        if (birthYear === 'none' || birthMonth === 'none' || birthDay === 'none') {
            $('#birth-warn').css('display', 'block');
            $('#birth-good').css('display', 'none');
            isValidDate = false;
        } else {
            $('#birth-warn').css('display', 'none');
            $('#birth-good').css('display', 'block');
            let strBirthDay = birthDay
            let strBirthMonth = birthMonth
            if (parseInt(birthDay) < 10) {
                strBirthDay = '0' + birthDay;
            }
            if (parseInt(birthMonth) < 10) {
                strBirthMonth = '0' + birthMonth;
            }

            $('#birth').val(birthYear + '' + strBirthMonth + '' + strBirthDay);
            isValidDate = true;
        }
    },

    emailValidate: function () {
        const email = $('#email').val();
        const emailRegex = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
        if (!emailRegex.test(email)) {
            $('#email-warning').css('display', 'block');
            $('#email-good').css('display', 'none');
            isEmail = false;
        } else {
            $('#email-warning').css('display', 'none');
            $('#email-good').css('display', 'block');
            isEmail = true;
        }
    },
    emailValid: function () {
        const data = $('#email').val();


        $.ajax({
            url: '/emailValid',
            method: 'POST',
            data: data,
            dataType: 'text',
            contentType: 'text/plain; charset=utf-8',
            success: function(data) {
                if (data === 'valid') {
                    alert('사용 가능한 이메일 입니다.');
                    $('#btn-email-valid').attr('disabled', true);
                    $('#email-valid').css('display', 'block');
                    isAuth = true;
                } else {
                    alert('이미 가입된 이메일 입니다.');
                    $('#email').focus();
                }
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
    nameValidate: function () {
        const nameValue = $('#name').val();
        if (nameValue.length <= 1) {
            $('#name-warning').css('display', 'block');
            $('#name-good').css('display', 'none');
            isName = false;
        } else {
            $('#name-warning').css('display', 'none');
            $('#name-good').css('display', 'block');
            isName = true;
        }
    },
    deleteUser: function () {
        const userId = $('#userId').val();

        $.ajax({
            url: '/api/v1/user/' + userId,
            method: 'DELETE',
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            alert('계정 삭제 완료');
            window.location.href = '/logout';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    changePassword: function() {
        const id = $('#btn-password-change').data('id');

        const data = {
            'id' : id,
            'password' : $('#curPass').val(),
            'newPassword' : $('#newPass').val()
        };

        $.ajax({
            url: '/user/password',
            method: 'POST',
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: JSON.stringify(data)
        }).done(function(data) {
            if(data === 0 || data === '0') {
                alert('패스워드가 일치하지 않습니다. 다시확인해주세요.');
            } else {
                alert('패스워드가 변경되었습니다. 다시 로그인 해주세요.')
                window.location.href = '/logout';
            }
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },
    forgotEmail : function() {
        const data = {
            'name' : $('#name').val(),
            'birth' : $('#birth').val()
        };

        $.ajax({
            url: '/forgot/email/req',
            method: 'POST',
            contentType: 'application/json; charset=utf-8',
            dataType: 'text',
            data: JSON.stringify(data)
        }).done(function(data) {
            if(data === '1' || data === 1) {
                alert('이메일이 존재하지 않습니다.')
            } else if(data === '0' || data === 0) {
                alert('정보를 다시 확인해주세요.')
            } else {
                alert('이메일 주소는 ' + data + ' 입니다. 로그인해주세요');
                window.location.href = '/login/' + data;
            }
        }).fail(function(error) {
            alert(JSON.stringify(error));
        })
    },
    emailExist: function() {
        const email = $('#email').val();

        $.ajax({
            url: '/forgot/password/email',
            method: 'POST',
            contentType: 'text/plain; charset=utf-8',
            dataType: 'text',
            data: email
        }).done(function(data) {
            if(data === '0' || data === 0) {
                const find = confirm('존재하지 않는 이메일입니다. 이메일을 찾으시겠습니까?')
                if(find) {
                    window.location.href = '/forgot/email';
                }
            } else {
                $('#question').attr('value', data);
                $('#div-email-isExist').css('display', 'block');
                $('#btn-forgot-password').css('display', 'block');
                alert('질문에 답변을 해주세요');
            }
        }).fail(function(error) {
            alert(JSON.stringify(error));
        })
    },
    forgotPassword: function() {
        const data = {
            'email': $('#email').val(),
            'answer': $('#answer').val()
        };

        $.ajax({
            url: '/forgot/password/req',
            method: 'POST',
            contentType: 'application/json; charset=utf-8',
            dataType: 'text',
            data: JSON.stringify(data)
        }).done(function(data) {
            if(data === '0' || data === 0) {
                alert('질문과 답변이 일치하지 않습니다.');
            } else {
                window.location.href = '/forgot/password/page/' + data;
            }
        })
    },
    changePasswordAsForgot: function() {
        const data = {
            'email': $('#email').val(),
            'password': $('#newPass').val()
        };

        $.ajax({
            url: '/forgot/password/recreate',
            method: 'POST',
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: JSON.stringify(data)
        }).done(function() {
            alert('패스워드 변경 완료 로그인 해주세요');
            window.location.href = '/login';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        })


    },
    inactiveCheck: function() {
        const code = $('#code').val();
        const id = $('#btn-inactive-check').data('id');
        $.ajax({
            url: '/inactive/' + id,
            method: 'POST',
            contentType: 'text/plain; charset=utf-8',
            dataType: 'text',
            data: code
        }).done(function(data) {
            if(data === '0' || data === 0) {
                window.location.href = '/inactive/reset/' + id;
            } else if(data === '-1' || data === -1) {
                alert('보안 코드를 다시 확인해주세요.')
            }
        }).fail(function(error) {
            alert(JSON.stringify(error));
        })
    }

}

user.init();