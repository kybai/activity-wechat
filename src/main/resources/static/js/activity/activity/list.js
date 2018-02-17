$(function () {
    initPage(1);
});

function initPage(currentPage) {
    $.ajax({
        url: base + '/activity/list',
        method: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(new BasePage(currentPage)),
        success: function (results) {
            loadPagination(results.data, "pageList", initPage, loadPage);
        }
    });
}

function loadPage(content) {
    var html = '';
    for (var k in content) {
        var entity = content[k];
        html += '<tr>';
        html += '<td>' + getVal(entity.id) + '</td>';
        html += '<td>' + getVal(entity.title) + '</td>';
        html += '<td>' + getVal(entity.beginTime) + '</td>';
        html += '<td>' + getNum0(entity.endTime) + '</td>';
        html += '<td>' + getDate(entity.createDate) + '</td>';
        html += '<td><button type="button" class="btn-link" onclick=disabledUser("' + getVal(entity.id) + '");>' + (getBoolean(entity.active) ? "禁用" : "激活") + '</button></td>';
        html += '</tr>';
    }
    $("#bodyList").empty().html(html);
}

function disabledUser(userID) {
    $.ajax({
        url: base + '/users/info/status',
        method: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(new BaseDisabled(userID)),
        success: function (results) {
            layer.msg(results.msg);
            setTimeout(function () {
                initPage(1);
            }, 1500);
        }
    });
}

function BasePage(currentPage) {
    this.currentPage = currentPage;
    this.name = $("#searchName").val();
}

function BaseDisabled(id) {
    this.id = Number(id);
}