$(function () {
    initPage(1);
});

function initPage(currentPage) {
    $.ajax({
        url: base + '/enroll/list',
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
        html += '<td>' + getVal(entity.ranking) + '</td>';
        html += '<td>' + getDate(entity.createDate) + '</td>';
        if (getVal($("#useName").html()) !== "")
            html += '<td>' + getVal(entity.name) + '</td>';
        if (getVal($("#useSex").html()) !== "")
            html += '<td>' + getVal(entity.sex) + '</td>';
        if (getVal($("#usePhone").html()) !== "")
            html += '<td>' + getVal(entity.phone) + '</td>';
        if (getVal($("#usePolitical").html()) !== "")
            html += '<td>' + getVal(entity.political) + '</td>';
        if (getVal($("#useCompany").html()) !== "")
            html += '<td>' + getVal(entity.company) + '</td>';
        if (getVal($("#useJob").html()) !== "")
            html += '<td>' + getVal(entity.job) + '</td>';
        if (getVal($("#useProfile").html()) !== "")
            html += '<td title="' + getVal(entity.profile) + '">' + getValLen(entity.profile) + '</td>';
        if (getVal($("#useCard").html()) !== "") {
            html += '<td><a href="#" onclick=catImg("' + getVal(entity.cardFace) + '")>查看图片</a></td>';
            html += '<td><a href="#" onclick=catImg("' + getVal(entity.cardBack) + '")>查看图片</a></td>';
        }
        html += '<td><button type="button" class="btn-link" onclick=updateStatus("' + getVal(entity.id) + '");>删除</button></td>';
        html += '</tr>';
    }
    $("#bodyList").empty().html(html);
}

function updateStatus(id) {
    $.ajax({
        url: base + '/enroll/status/' + id,
        method: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        success: function (results) {
            layer.msg(results.msg);
            initPage(1);
        }
    });
}

function catImg(id) {
    if (getVal(id) === "") {
        layer.msg("图片编号不存在");
        return false;
    }
    $.ajax({
        url: base + '/file/download/' + id,
        type: 'POST',
        success: function (result) {
            $("#imgFile").attr("src", result);
            $("#imgModal").modal("show");
        }
    });
}

function exportExcel() {
    var activityId = $("#activityId").val();
    window.location.href = base + '/enroll/export/' + activityId;
}

function goHistory() {
    window.location.href = base + '/activity/list';
}

function BasePage(currentPage) {
    this.currentPage = currentPage;
    this.otherId = $("#activityId").val();
    this.name = $("#searchName").val();
}