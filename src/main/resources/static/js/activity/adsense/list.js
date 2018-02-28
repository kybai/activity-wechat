$('#addForm, #editForm').validationEngine({promptPosition: 'centerRight', scroll: false});

$(function () {
    initPage(1);
});

function initPage(currentPage) {
    $.ajax({
        url: base + '/adsense/list',
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
    for (key in content) {
        var entity = content[key];
        html += '<tr fileid="' + getVal(entity.uploadFileId) + '">';
        html += '<td>' + getVal(entity.id) + '</td>';
        html += '<td><a href="#" onclick=catImg("' + getVal(entity.uploadFileId) + '");>查看图片</a></td>';
        html += '<td>' + getVal(entity.url) + '</td>';
        html += '<td>' + getDateD(entity.createDate) + '</td>';
        html += '<td><button type="button" class="btn-link" onclick="edit(this);">编辑</button>' +
            '<button type="button" class="btn-link" onclick=disabledStatus("' + getVal(entity.id) + '");>' + (getBoolean(entity.active) ? "禁用" : "激活") + '</button>' +
            '</td>';
        html += '</tr>';
    }
    $("#bodyList").empty().html(html);
}

function save() {
    if (!$("#addForm").validationEngine('validate')) {
        return false;
    }
    var fileid = $("#uploadFileID").attr("fileid");
    if (getVal(fileid) === "") {
        layer.msg("请上传图片");
        return false;
    }
    var url = $("#url").val();
    saveOrUpdate(new EntityPojo(null, fileid, url));
}

function edit(obj) {
    var $tr = $(obj).closest("tr");
    $("#editID").val($tr.find("td:eq(0)").html());
    $("#editUrl").val($tr.find("td:eq(2)").html());
    $("#editUploadFileID").attr("fileid", $tr.attr("fileid"));
    $("#editModal").modal("show");
}

function update() {
    if (!$("#editForm").validationEngine('validate')) {
        return false;
    }
    var id = $("#editID").val();
    var fileid = $("#editUploadFileID").attr("fileid");
    var url = $("#editUrl").val();
    saveOrUpdate(new EntityPojo(id, fileid, url));
}

function disabledStatus(id) {
    $.ajax({
        url: base + '/adsense/disabled/' + id,
        method: "POST",
        dataType: 'json',
        contentType: 'application/json',
        success: function (results) {
            layer.msg(results.msg);
            initPage(1);
        }
    });
}

function saveOrUpdate(entity) {
    $.ajax({
        url: base + '/adsense/save',
        method: "POST",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(entity),
        success: function (results) {
            layer.msg(results.msg);
            initPage(1);
            setTimeout(function () {
                $("#addModal, #editModal").modal("hide");
                if (getVal(entity.id) === "") {
                    document.getElementById("addForm").reset();
                }
            }, 1500);
        }
    });
}

$("#uploadFileID").on("change", function () {
    var formData = new FormData();
    formData.append("uploadFile", $("#uploadFileID")[0].files[0]);
    formData.append("fileType", "ADSENSE");
    uploadImg(formData, "uploadFileID");
});

$("#editUploadFileID").on("change", function () {
    var formData = new FormData();
    formData.append("uploadFile", $("#editUploadFileID")[0].files[0]);
    formData.append("fileType", "ADSENSE");
    uploadImg(formData, "editUploadFileID");
});

function uploadImg(formData, id) {
    $.ajax({
        url: base + '/file/upload',
        type: 'POST',
        dataType: 'json',
        enctype: 'multipart/form-data',
        data: formData,
        processData: false,  // 告诉jQuery不要去处理发送的数据
        contentType: false,  // 告诉jQuery不要去设置Content-Type请求头
        success: function (results) {
            $("#" + id).attr("fileid", results.data);
        }
    });
}

function catImg(id) {
    $.ajax({
        url: base + '/file/download/' + id,
        type: 'POST',
        success: function (result) {
            $("#imgFile").attr("src", result);
            $("#imgModal").modal("show");
        }
    });
}

function BasePage(currentPage) {
    this.currentPage = currentPage;
    this.name = "广告图";
}

function EntityPojo(id, fileid, url) {
    this.id = id;
    this.uploadFileId = fileid;
    this.url = url;
    this.type = '广告图';
}