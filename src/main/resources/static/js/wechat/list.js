(function () {
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
                    var needAuth = $("#needAuth").attr("needauth");
                    if (getBoolean(needAuth) && isWeiXin()) {
                        window.location.href = $("#redirectUrl").val();
                        return false;
                    } else if (!getBoolean(needAuth) && isWeiXin()) {
                        loadMyData();
                    }
                }
                $("#tab-ul").find("." + tabclass).removeClass("none").siblings("div").addClass("none");
            });
        },
        scrollEvent: function () {
            //置顶
            $(window).on('scroll', function () {
                var scrollTop = $(window).scrollTop();
                var bannerHeight = $('.list .slider').height() + 20;

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
    var selVal = $('.add option:selected').text();
    $('.select_down span').text(selVal);
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
            if (isWeiXin()) loadMylistHtml(results.data.mylist);
        }
    });
}

function loadHtml(id, list) {
    var html = '';
    if (getVal(list) !== "" && list.length < 1) {
        html += '<div class="no-class"><img src="' + base + '/img/wechat/nojoin.png"/><span>暂无活动</span></div>';
    } else {
        for (key in list) {
            var e = list[key];
            html += '<div class="act-info"><a href="' + base + '/wechat/activity/info/' + getVal(e.id) + '">';
            html += '<img class="img-box" src="' + getVal(e.coverPath) + '"/>';
            html += '<div class="title">' + getVal(e.title) + '</div>';
            html += '<div class="info">';
            html += '<div class="info-time">' + getDateM(e.beginTime) + '-' + getDateM(e.endTime) + '</div>';
            html += '<div class="info-address">' + getVal(e.address) + '</div>';
            html += '</div>';
            html += '</a></div>';
        }
    }
    $("#" + id).empty().html(html);
}

function loadMyData() {
    var entity = new WechatPojo();
    $.ajax({
        url: base + '/wechat/activity/mylist',
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(entity),
        success: function (results) {
            loadMyInfo(results.data.user);
            loadMylistHtml(results.data.mylist);
        }
    });
}

function loadMylistHtml(list) {
    if (getVal(list) !== "" && list.length < 1) {
        $("#mylist").empty().html('<div class="no-class"><img src="' + base + '/img/wechat/nojoin.png"/><span>尚未参加任何活动</span></div>');
        return false;
    }
    var html = '';
    for (key in list) {
        var e = list[key];
        html += '<li>';
        html += '<div class="act-info">';
        html += '<a href="' + base + '/wechat/course/info/' + getVal(e.id) + '">';
        html += '<img class="img-box" src="' + getVal(e.coverPath) + '"/>';
        html += '<div class="title">' + getVal(e.title) + '</div>';
        html += '<div class="info">';
        html += '<div class="info-time">' + getDateM(e.beginTime) + '-' + getDateM(e.endTime) + '</div>';
        html += '<div class="info-address">' + getVal(e.address) + '</div>';
        html += '</div>';
        html += '</li>';
    }
    $("#mylist").html('<ul class="scroll-tab">' + html + '</ul>');
}

function loadMyInfo(user) {
    $("#myName").empty().html(getVal(user.name));
    $("#myScore").empty().html(getVal(user.score) === "" ? 0 : getVal(user.score));
    $("#myImg").empty().attr("src", getVal(user.headImgUrl));
}

function WechatPojo() {
    this.districtId = $("#district").val();
}