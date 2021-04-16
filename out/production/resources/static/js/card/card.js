const card = {
    init: function() {
        const _this = this;

        $('.btn-card-delete').on('click', function() {
            const id = $(this).find('input').val();
            _this.delete(id);
        });

        $('#btn-card-save').on('click', function() {
            _this.save();
        });

        $('#btn-card-update').on('click', function() {
            _this.update();
        });

    },

    delete: function(id) {
        $.ajax({
            url: '/api/v1/card/' + id,
            method: 'DELETE',
            contentType: 'application/json; charset=utf-8',
        }).done(function(){
            alert('Todo 완료');
            window.location.reload(true);
        }).fail(function(error) {
            alert(JSON.stringify(error));
        })
    },

    save: function() {
        const data = {
            cardType: $('#cardType option:selected').val(),
            title: $('#title').val(),
            content: $('#content').val(),
            boardId: $('#boardId').val()
        };

        $.ajax({
            url: '/api/v1/card',
            method: 'POST',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            window.location.href = '/menu/todo/' + data.boardId;
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

    update: function() {
        const data = {
            title: $('#title').val(),
            content: $('#content').val(),
            cardType: $('#cardType option:selected').val()
        };

        const id = $('#id').val();
        const boardId = $('#boardId').val();

        $.ajax({
            url: '/api/v1/card/' + id,
            method: 'PUT',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('수정 성공');
            window.location.href = '/menu/todo/' + boardId;
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    }
}

card.init();