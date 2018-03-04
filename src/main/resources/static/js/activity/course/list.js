$(function () {
    initPage(1);
});

function initPage(currentPage) {
    $.ajax({
        url: base + '/course/list',
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
        html += '<td>' + getVal(entity.name) + '</td>';
        html += '<td>' + getVal(entity.signTotal) + '/' + getVal(entity.enrollTotal) + '</td>';
        html += '<td><a href="javascript:void(0);" onclick="catCode(this);">' + base + '/wechat/portal/sign/' + entity.id + '</a></td>';
        html += '</tr>';
    }
    $("#bodyList").empty().html(html);
}

function catCode(obj) {
    var $this = $(obj);
    $("#imgCode").qrcode($this.html());
    $("#imgModal").modal("show");
}

function goHistory() {
    window.location.href = base + '/activity/list';
}

function BasePage(currentPage) {
    this.currentPage = currentPage;
    this.otherId = $("#activityId").val();
}