$('#addForm, #editForm').validationEngine({promptPosition: 'centerRight', scroll: false});

$(function () {
    initPage(1);
});

function initPage(currentPage) {
    $.ajax({
        url: base + '/district/list',
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
        html += '<td>' + getDateD(entity.createDate) + '</td>';
        html += '<td>'
            + '<button type="button" class="btn-link" onclick="edit(this);">编辑</button>'
            + '<button type="button" class="btn-link" onclick=disabledStatus("' + getVal(entity.id) + '");>' + (getBoolean(entity.active) ? "禁用" : "激活") + '</button>'
            + '</td>';
        html += '</tr>';
    }
    $("#bodyList").empty().html(html);
}

function edit(obj) {
    var $tr = $(obj).closest("tr");
    var id = $tr.find("td:eq(0)").html();
    var name = $tr.find("td:eq(1)").html();
    $("#editID").val(id);
    $("#editName").val(name);
    $("#editModal").modal("show");
}

function save() {
    if (!$("#addForm").validationEngine('validate')) {
        return false;
    }
    var name = $("#name").val();
    saveOrUpdate(new DistrictEntity(null, name), "POST");
}

function update() {
    if (!$("#editForm").validationEngine('validate')) {
        return false;
    }
    var id = $("#editID").val();
    var name = $("#editName").val();
    saveOrUpdate(new DistrictEntity(id, name), "PUT");
}

function saveOrUpdate(entity, method) {
    $.ajax({
        url: base + '/district/info',
        method: method,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(entity),
        success: function (results) {
            layer.msg(results.msg);
            initPage(1);
            setTimeout(function () {
                if (method === "POST") {
                    $("#addModal").modal("hide");
                    document.getElementById("addForm").reset();
                } else {
                    $("#editModal").modal("hide");
                    document.getElementById("editForm").reset();
                }
            }, 1500);
        }
    });
}

function disabledStatus(id) {
    $.ajax({
        url: base + '/district/info/status',
        method: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(new BaseDisabled(id)),
        success: function (results) {
            layer.msg(results.msg);
            initPage(1);
        }
    });
}

function BasePage(currentPage) {
    this.currentPage = currentPage;
    this.name = $("#searchName").val();
}

function BaseDisabled(id) {
    this.id = parseInt(id);
}

function DistrictEntity(id, name, active) {
    this.id = parseInt(id);
    this.name = name;
    this.active = Boolean(active);
}