const todo = {
    init: function() {
        console.log('hello');

        const _this = this;

        $('#btn-todo-save').on('click', function() {
            _this.save();
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
    }
}

todo.init();