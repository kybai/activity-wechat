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
        html += '<td><a href="' + base + '/activity/info/' + entity.id + '">' + getVal(entity.title) + '</a></td>';
        html += '<td>' + getDate(entity.beginTime) + '</td>';
        html += '<td>' + getDate(entity.endTime) + '</td>';
        html += '<td><a href="#">签到码</a></td>';
        html += '<td><a href="#">已报名人数</a></td>';
        html += '<td>点赞/访问</td>';
        html += '<td>' + getDateD(entity.createDate) + '</td>';
        html += '<td>'
            + '<button type="button" class="btn-link" onclick=edit("' + entity.id + '");>编辑</button>'
            + '<button type="button" class="btn-link" onclick=disabledActivity("' + getVal(entity.id) + '");>' + (getBoolean(entity.active) ? "失效" : "生效") + '</button>'
            + '</td>';
        html += '</tr>';
    }
    $("#bodyList").empty().html(html);
}

function edit(activityId) {
    window.location.href = base + '/activity/edit/' + activityId;
}

function disabledActivity(userID) {
    $.ajax({
        url: base + '/activity/info/status',
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