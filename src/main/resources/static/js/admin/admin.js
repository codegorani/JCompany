const admin = {
    init: function() {
        const _this = this;

        $('.btn-admin-user-delete').on('click', function() {
            const userId = $(this).data('userId');
            if(confirm('정말로 삭제하시겠습니까?')) {
                _this.userDelete(userId);
            }
        });

        $('.btn-admin-board-delete').on('click', function() {
            const boardId = $(this).data('boardId');
            if(confirm('정말로 삭제하시겠습니까?')) {
                _this.boardDelete(boardId);
            }
        });

        $('.btn-admin-user-password').on('click', function() {
            const userId = $(this).data('userId');
            _this.userPasswordReset(userId);
        })
    },
    userDelete: function(userId) {
        $.ajax({
            url: '/admin/member/' + userId,
            method: 'DELETE',
            contentType: 'application/json; charset=utf-8'
        }).done(function() {
            alert('삭제 완료');
            window.location.href= '/admin/member';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        })
    },
    userPasswordReset: function(userId) {
        $.ajax({
            url: '/admin/member/' + userId,
            method: 'PUT',
            contentType: 'application/json; charset=utf-8',
            dataType: 'json'
        }).done(function() {
            alert('초기화 완료');
            window.location.href= '/admin/member';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        })
    },
    boardDelete: function(boardId) {
        $.ajax({
            url: '/api/v1/board/' + boardId,
            method: 'DELETE',
            contentType: 'application/json; charset=utf-8'
        }).done(function() {
            alert('삭제 완료');
            window.location.href= '/admin/board';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        })
    }
}

admin.init();