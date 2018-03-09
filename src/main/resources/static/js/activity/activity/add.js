$('#addForm').validationEngine({promptPosition: 'centerRight', scroll: false, binded: false});
$(function () {
    initDate();
    initCourseTime();
});

function save() {
    if (!$("#addForm").validationEngine('validate')) {
        return false;
    }
    $.ajax({
        url: base + '/activity/save',
        method: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(new ActivityPojo()),
        success: function (results) {
            layer.msg(results.msg);
            setTimeout(function () {
                goHistory();
            }, 1500);
        }
    });
}

$("#uploadFileID").on("change", function () {
    var formData = new FormData();
    var files = $("#uploadFileID")[0].files;
    if (getVal(files) === "" || files.length <= 0) {
        $("#uploadFileID").attr("fileid", null);
        return false;
    }
    formData.append("uploadFile", files[0]);
    formData.append("fileType", "ACTIVITY");

    $.ajax({
        url: base + '/file/upload',
        type: 'POST',
        dataType: 'json',
        enctype: 'multipart/form-data',
        data: formData,
        processData: false,  // 告诉jQuery不要去处理发送的数据
        contentType: false,  // 告诉jQuery不要去设置Content-Type请求头
        success: function (results) {
            var file = results.data;
            $("#uploadFileID").attr("fileid", file.id);
        }
    });
});

function copyCourseTr(obj) {
    var $this = $(obj);
    var $section = $this.closest("section");
    if ($section.find(".course-remove").length === 0) {
        $this.parent().append('<button type="button" class="btn btn-default course-remove" onclick="removeCourseTr(this);">删除</button>');
    }
    $("#courseDiv").append(getCourseHtml());
    appendCourseAddBtn();
}

function removeCourseTr(obj) {
    var $this = $(obj);
    $this.closest('section').remove();
    appendCourseAddBtn();
}

function appendCourseAddBtn() {
    var $div = $("#courseDiv");
    $div.find(".course-add").remove();
    $div.find(".group-btn:last").prepend('<button type="button" class="btn btn-default course-add margin-right-15" onclick="copyCourseTr(this);">增加</button>');
    if ($div.find(".course-tr").length === 1) {
        $div.find(".course-remove").remove();
    }
    initCourseTime();
}

//不能使用clone函数, 因laydate不支持
function getCourseHtml() {
    var html = '';
    html += '<section class="form-group col-md-12 course-tr">';
    html += '<div class="col-sm-9 padding-left-0">';
    html += '<div class="col-md-4 padding-left-0">';
    html += '<input type="text" class="form-control course-name validate[required]" placeholder="课程名称"/>';
    html += '</div>';
    html += '<div class="col-md-4">';
    html += '<input type="text" class="form-control course-time begin-time validate[required]" placeholder="开始时间"/>';
    html += '</div>';
    html += '<div class="col-md-4">';
    html += '<input type="text" class="form-control course-time end-time validate[required]" placeholder="结束时间"/>';
    html += '</div>';
    html += '</div>';
    html += '<div class="col-sm-3 group-btn">';
    html += '<button type="button" class="btn btn-default course-remove" onclick="removeCourseTr(this);">删除</button>';
    html += '</div>';
    html += '</section>';
    return html;
}

function initDate() {
    laydate.render({
        elem: '#beginTime',
        type: 'datetime',
        format: 'yyyy-MM-dd HH:mm'
    });
    laydate.render({
        elem: '#endTime',
        type: 'datetime',
        format: 'yyyy-MM-dd HH:mm'
    });
}

function initCourseTime() {
    lay(".course-time").each(function () {
        laydate.render({
            elem: this,
            type: 'datetime',
            format: 'yyyy-MM-dd HH:mm',
            closeStop: this
        });
    });
}

function ActivityPojo() {
    this.activity = new ActivityEntity();
    this.desc = getUEContent();
    this.activityTag = new ActivityTag();
    this.courseList = getCourseList();
}

function ActivityEntity() {
    this.title = $("#title").val();
    this.districtId = $("#district").val();
    this.address = $("#address").val();
    this.beginTime = toTimestamp($("#beginTime").val());
    this.endTime = toTimestamp($("#endTime").val());
    this.maxLimit = getNum0($("#maxLimit").val());
    this.uploadFileId = getNum0($("#uploadFileID").attr("fileid"));
}

function ActivityTag() {
    this.useName = $("#useName").prop("checked");
    this.useSex = $("#useSex").prop("checked");
    this.usePhone = $("#usePhone").prop("checked");
    this.usePolitical = $("#usePolitical").prop("checked");
    this.useCompany = $("#useCompany").prop("checked");
    this.useJob = $("#useJob").prop("checked");
    this.useCard = $("#useCard").prop("checked");
    this.useProfile = $("#useProfile").prop("checked");
}

function ActivityCourse(name, beginTime, endTime) {
    this.name = name;
    this.beginTime = toTimestamp(beginTime);
    this.endTime = toTimestamp(endTime);
}

function getCourseList() {
    var arr = [];
    $("#courseDiv").find(".course-tr").each(function () {
        var $this = $(this);
        var name = $this.find(".course-name").val();
        var beginTime = $this.find(".begin-time").val();
        var endTime = $this.find(".end-time").val();
        arr.push(new ActivityCourse(name, beginTime, endTime));
    });
    return arr;
}

function goHistory() {
    window.location.href = base + '/activity/list';
}