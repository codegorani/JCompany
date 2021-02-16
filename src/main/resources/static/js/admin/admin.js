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
        });

        $('#searchType').on('change', function() {
            $('#searchValue').remove();
            let option = $('#searchType option:selected').val();
            if(option === 'userLevel') {
                $(this).after('<select id="searchValue" name="searchValue" class="form-control">\n' +
                    '                        <option value="DD_INTERN">인턴</option>\n' +
                    '                        <option value="DC_STAFF" selected>사원</option>\n' +
                    '                        <option value="DB_ASSOCIATE">주임</option>\n' +
                    '                        <option value="DA_ASSOCIATE_MANAGER">대리</option>\n' +
                    '                        <option value="CC_MANAGER">과장</option>\n' +
                    '                        <option value="CB_SENIOR_MANAGER">차장</option>\n' +
                    '                        <option value="CA_EXECUTIVE_MANAGER">부장</option>\n' +
                    '                        <option value="BC_ASSOCIATE_EXECUTIVE_DIRECTOR">이사</option>\n' +
                    '                        <option value="BB_EXECUTIVE_DIRECTOR">상무이사</option>\n' +
                    '                        <option value="BA_EXECUTIVE_VICE_PRESIDENT">전무이사</option>\n' +
                    '                        <option value="AB_SENIOR_EXECUTIVE_VICE_PRESIDENT">부사장</option>\n' +
                    '                        <option value="AA_PRESIDENT">사장</option>\n' +
                    '                    </select>');
                $('#searchValue').focus();
            } else if(option === 'userTeam') {
                $(this).after('<select id="searchValue" name="searchValue" class="form-control">\n' +
                    '                        <option value="STRATEGIC_PLANNING" selected>전락기획팀</option>\n' +
                    '                        <option value="MANAGEMENT_SUPPORT">경영지원팀</option>\n' +
                    '                        <option value="FINANCE">재무팀</option>\n' +
                    '                        <option value="QUALITY_GUARANTIED">품질보증팀</option>\n' +
                    '                        <option value="AIR_MANAGEMENT_SYSTEM">항공관리시스템팀</option>\n' +
                    '                        <option value="AIR_SALES_SYSTEM">항공영업시스템팀</option>\n' +
                    '                        <option value="ONLINE_PLATFORM">온라인플랫폼팀</option>\n' +
                    '                        <option value="LCC_AIRPORT_BUSINESS">LCC/공항사업팀</option>\n' +
                    '                        <option value="LCC_AIRPORT_CONSULTING">LCC/공항컨설팅팀</option>\n' +
                    '                        <option value="SYSTEM_TECHNOLOGY_MANAGEMENT">시스템기술관리팀</option>\n' +
                    '                    </select>');
                $('#searchValue').focus();
            } else if(option === 'name') {
                $(this).after('<input type="text" id="searchValue" name="searchValue" class="form-control"/>');
                $('#searchValue').focus();
            } else if(option === 'none') {
                $(this).after('<input type="hidden" id="searchValue" name="searchValue"/>');
                $('btn-search').focus();
            }
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