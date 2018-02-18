$(function () {
    initDate();
    initCourseTime();
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
    html += '<input type="text" class="form-control course-name" placeholder="课程名称"/>';
    html += '</div>';
    html += '<div class="col-md-4">';
    html += '<input type="text" class="form-control course-time begin-time" placeholder="开始时间"/>';
    html += '</div>';
    html += '<div class="col-md-4">';
    html += '<input type="text" class="form-control course-time end-time" placeholder="结束时间"/>';
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
        type: 'datetime'
    });
    laydate.render({
        elem: '#endTime',
        type: 'datetime'
    });
}

function initCourseTime() {
    lay(".course-time").each(function () {
        laydate.render({
            elem: this,
            type: 'datetime',
            closeStop: this
        });
    });
}