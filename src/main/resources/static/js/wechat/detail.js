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
    var openid = getWechatStorage('ACTIVITY_WECHAT_OPENID');
    window.location.href = base + '/wechat/activity/enroll/' + activityId + '?openid=' + openid;
}

function WechatPojo() {
    this.activityId = $("#activityId").val();
    this.openid = getWechatStorage('ACTIVITY_WECHAT_OPENID');
}