const mail = {
    init: function() {
        const _this = this;

        $('#btn-mail-send').on('click', function() {
            _this.sendEmail();
        });
    },
    sendEmail: function() {
        const data = {
            'address': $('#address').val(),
            'title': $('#title').val(),
            'message': $('#message').val()
        };

        $.ajax({
            url: '/api/v1/mail',
            method: 'POST',
            dataType: 'text',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(data) {
            if(data === '0' || data === 0) {
                alert('메일 전송에 성공했습니다.');
                window.location.href = '/';
            } else if(data === '-1' || data === -1) {
                alert('예상치 못한 오류가 발생했습니다. 다시 시도해주세요.')
            }
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    }
}

mail.init();