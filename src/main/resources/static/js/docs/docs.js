const docs = {
    init: function() {

        const _this = this;

        $('#btn-documents-save').on('click', function() {
            _this.save();
        });

        $('#btn-documents-confirm').on('click', function() {
            const docId = $('#btn-documents-confirm').data('docId');
            _this.documentConfirm(docId);
        })
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
    },

    documentConfirm: function(docId) {
        $.ajax({
            url: '/api/v1/docs/confirm' + docId,
            method: 'POST',
            contentType: 'application/json; charset=utf-8'
        }).done(function() {
            alert('결재를 완료했습니다.')
            window.location.reload(true);
        }).fail(function(error) {
            alert(JSON.stringify(error));
        })
    }
}

docs.init();