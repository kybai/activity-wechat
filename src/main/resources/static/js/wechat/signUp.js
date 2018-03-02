//保存报名资料
function saveEnroll() {
    if (!checkForm()) {
        return false;
    }
    var entity = new ActivityEnroll();
    $.ajax({
        url: base + '/wechat/activity/enroll',
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(entity),
        success: function (results) {
            if (results.status === 100) {
                $('.error-tip').text(results.msg).removeClass('none');
                setTimeout(function () {
                    $('.error-tip').addClass('none');
                }, 3000);
            } else if (results.status === 200) {
                window.location.href = base + '/wechat/activity/enroll/' + entity.activityId + '/success?openid=' + getVal($("#openid").val());
            }
        }
    });
}

function checkForm() {
    var check = true;
    var reg = /^1[3,4,5,7,8,9][0-9]{9}$/;
    $('.form-li input[type=text]').each(function (index, el) {
        var val = $.trim($(el).val());
        if (val === '') {
            $(el).focus().addClass('onfocus');
            check = false;
            return false;
        } else {
            if ($(el).attr('name') === 'tel') {
                if (!reg.test(val)) {
                    $('.error-tip').text("请正确填写联系方式").removeClass('none');
                    $(el).focus().addClass('onfocus');
                    setTimeout(function () {
                        $('.error-tip').addClass('none');
                    }, 2000);
                    check = false;
                    return false;
                }
            }
        }
    });
    return check;
}

$("#cardBack, #cardFront").on("change", function () {
    var $this = $(this);
    var formData = new FormData();
    formData.append("uploadFile", $(this)[0].files[0]);
    $.ajax({
        url: base + '/wechat/activity/upload',
        type: 'POST',
        dataType: 'json',
        enctype: 'multipart/form-data',
        data: formData,
        processData: false,  // 告诉jQuery不要去处理发送的数据
        contentType: false,  // 告诉jQuery不要去设置Content-Type请求头
        success: function (results) {
            $this.attr("fileid", results.data);
        }
    });
});

function ActivityEnroll() {
    this.name = $("#name").val();
    this.sex = $("#man").prop("checked") ? "男" : "女";
    this.cardFace = $("#cardFront").attr("fileid");
    this.cardBack = $("#cardBack").attr("fileid");
    this.political = $("#political").val();
    this.phone = $("#tel").val();
    this.company = $("#company").val();
    this.job = $("#work").val();
    this.profile = $("#introduce").val();
    this.activityId = $("#activityId").val();
    this.userId = $("#userId").val();
}