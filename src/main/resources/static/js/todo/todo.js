const todo = {
    init: function() {
        console.log('hello');

        const _this = this;

        $('#btn-todo-save').on('click', function() {
            _this.save();
        });

        $('.btn-todo-delete').on('click', function() {
            const id = $(this).find('input').val();
            _this.delete(id);
        });

        $('#btn-board-title').on('click', function() {
            let $board_title = $('#board-title');
            const title = $board_title.text();
            $board_title.append('<input type="text" class="form-control" id="title" value="' + title +'"/>');
            let $btn = $('#btn-board-title');
            $btn.text('수정');
            $btn.attr('id', 'temp-id');
            $btn.on('click', function() {
                _this.update(_this);
            })
        });

    },

    save: function() {
        const data = {
            title: $('#title').val(),
            author: $('#author').val()
        };

        $.ajax({
            url: '/api/v1/board',
            method: 'POST',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            window.location.href = '/menu/todo';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },
    delete: function(id) {
        $.ajax({
            url: '/api/v1/board/' + id,
            method: 'DELETE',
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            alert('삭제 완료');
            window.location.reload(true);
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },

    update: function(_this) {
        const boardId = $('#boardId').val();

        const data = {
            title: $('#title').val(),
            id: boardId
        }

        $.ajax({
            url: '/api/v1/board/' + boardId,
            method: 'PUT',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('변경완료.')
            window.location.reload(true);
            _this.init();
        }).fail(function(error) {
            alert(JSON.stringify(error));
        })
    }
}

todo.init();