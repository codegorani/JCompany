const survey = {
    init: function() {
        const _this = this;

        $('.btn-progress').on('click', function() {
            _this.surveyProgress($(this).data('type'));
        });

        $('#btn-survey-delete').on('click', function() {
            _this.surveyDelete();
        });

        $('.btn-survey-process').on('click', function() {
            _this.surveyProcess();
        });
    },
    surveyProgress: function(type) {
        const dataGroup = $('#data-group');
        const userId = dataGroup.data('userId');
        const surveyId = dataGroup.data('surveyId');
        const data = {
            'surveyId': surveyId,
            'userId': userId,
            'type': type
        };
        console.log(data);

        $.ajax({
            url: '/survey/progress',
            method: 'POST',
            contentType: 'application/json; charset=UTF-8',
            dataType: 'json',
            data: JSON.stringify(data)
        }).done(function(data) {
            if (data === -1) {
                alert('이미 설문에 참여하셨습니다.');
            } else if (data === -2) {
                alert('설문이 시작되지 않았습니다.');
            } else if (data === -3) {
                alert('종료된 설문입니다.');
            } else {
                alert('설문 참여 완료');
                window.location.reload(true);
            }
        }).fail(function(error) {
            alert(JSON.stringify(error));
        })
    },
    surveyDelete: function() {
        const surveyId = $('#data-group').data('surveyId');

        $.ajax({
            url: '/survey/' + surveyId,
            method: 'DELETE',
            contentType: 'application/json; charset=UTF-8'
        }).done(function() {
            alert('삭제가 완료되었습니다.');
            window.location.href = '/survey/home';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },
    surveyProcess: function() {
        const dataGroup = $('#data-group');
        const surveyStatus = dataGroup.data('surveyStatus');
        const surveyId = dataGroup.data('surveyId');

        $.ajax({
            url: '/survey/process/' + surveyId,
            method: 'PUT',
            contentType: 'text/plain; charset=utf-8',
            dataType: 'text',
            data: surveyStatus
        }).done(function(data) {
            if (data === 'PROGRESS') {
                alert('설문이 시작되었습니다.')
                window.location.reload(true);
            } else if(data === 'DONE') {
                alert('설문이 종료되었습니다.');
                window.location.reload(true);
            }
        }).fail(function(error) {
            alert(JSON.stringify(error))
        });
    }
}
survey.init();