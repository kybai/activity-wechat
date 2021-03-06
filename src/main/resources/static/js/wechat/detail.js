//活动点赞
function thumbup() {
    $.ajax({
        url: base + '/wechat/activity/thumbup',
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(new WechatPojo()),
        success: function (results) {
            if (results.status === 200) {
                var $thumbup = $("#thumbup");
                var total = $("#thumbupTotal").html();
                if ($thumbup.hasClass("active")) {
                    $thumbup.removeClass("active");
                    $("#thumbupTotal").html(Number(total) - 1);
                } else {
                    $thumbup.addClass("active");
                    $("#thumbupTotal").html(Number(total) + 1);
                }
            }
        }
    });
}

//我要报名
function enrollActivity() {
    var activityId = $("#activityId").val();
    window.location.href = base + '/wechat/activity/enroll/' + activityId+"?state=INDEX";
}

function WechatPojo() {
    this.activityId = $("#activityId").val();
}