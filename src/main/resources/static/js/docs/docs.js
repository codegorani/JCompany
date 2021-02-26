const docs = {
    init: function() {

        const _this = this;

        $('#btn-documents-save').on('click', function() {
            _this.save();
        });
    },

    save: function() {
        const data = {
            title: $('#title').val(),
            content: $('#content').val(),
            documentsType: $('#documentsType option:selected').val(),
            userId: $('#userId').val(),
            approvalId: $('#approvalId option:selected').val()
        };

        $.ajax({
            url: '/api/v1/docs',
            method: 'POST',
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: JSON.stringify(data)
        }).done(function() {
            alert('문서를 상신했습니다.');
            window.location.href = '/documents';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    }
}

docs.init();