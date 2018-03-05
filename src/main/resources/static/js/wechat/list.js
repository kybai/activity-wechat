(function () {
    setStorage('ACTIVITY_WECHAT_OPENID', $("#openid").val());
    var Index = {
        init: function () {
            this.clickEvent();
            this.scrollEvent();
        },
        clickEvent: function () {
            $('#title-ul').find('li').on('click', function () {
                var _this = $(this);
                _this.addClass('active').siblings('li').removeClass('active');
                $('.list .tab-li').removeClass('fixed');
                var newtitle = _this.find('span').text();
                var tabclass = 'act-list';
                if (newtitle === "活动回顾") tabclass = 'act-review';
                if (newtitle === "我的") {
                    tabclass = 'my';
                }
                $("#tab-ul").find("." + tabclass).removeClass("none").siblings("div").addClass("none");
            });
        },
        scrollEvent: function () {
            //置顶
            $(window).on('scroll', function () {
                var scrollTop = $(window).scrollTop();
                var bannerHeight = $('.list .slider').height();

                if (scrollTop > bannerHeight) {
                    $('.list .tab-li').addClass('fixed');
                } else {
                    $('.list .tab-li').removeClass('fixed');
                }
            });
        }
    };
    Index.init();
    loadData();
})();

function loadData() {
    var entity = new WechatPojo();
    $.ajax({
        url: base + '/wechat/activity/list',
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(entity),
        success: function (results) {
            loadHtml("actListInfo", results.data.wechatList);
            loadHtml("actReviewListInfo", results.data.reviewList);
            if (getVal(entity.openid) !== "") {
                loadMyHtml(results.data.myList);
            }
        }
    });
}

function loadHtml(id, list) {
    var openid = getWechatStorage('ACTIVITY_WECHAT_OPENID');
    var html = '';
    for (key in list) {
        var e = list[key];
        html += '<div class="act-info"><a href="' + base + '/wechat/activity/info/' + getVal(e.id) + '?openid=' + openid + '">';
        html += '<img class="img-box" src="' + getVal(e.coverPath) + '"/>';
        html += '<div class="title">' + getVal(e.title) + '</div>';
        html += '<div class="info">';
        html += '<div class="info-time">' + getDateM(e.beginTime) + '-' + getDateM(e.endTime) + '</div>';
        html += '<div class="info-address">' + getVal(e.address) + '</div>';
        html += '</div>';
        html += '</a></div>';
    }
    $("#" + id).empty().html(html);
}

function loadMyHtml(list) {
    var openid = getWechatStorage('ACTIVITY_WECHAT_OPENID');
    var html = '';
    for (key in list) {
        var e = list[key];
        html += '<li>';
        html += '<div class="act-info">';
        html += '<a href="' + base + '/wechat/activity/info/' + getVal(e.id) + '?openid=' + openid + '">';
        html += '<img class="img-box" src="' + getVal(e.coverPath) + '"/>';
        html += '<div class="title">' + getVal(e.title) + '</div>';
        html += '<div class="info">';
        html += '<div class="info-time">' + getDateM(e.beginTime) + '-' + getDateM(e.endTime) + '</div>';
        html += '<div class="info-address">' + getVal(e.address) + '</div>';
        html += '</div>';
        html += '</li>';
    }
    $("#mylist").html(html);
}

function WechatPojo() {
    this.districtId = $("#district").val();
    this.openid = getWechatStorage('ACTIVITY_WECHAT_OPENID');
}