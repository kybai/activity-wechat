(function () {
    var Index = {
        init: function () {
            this.scrollEvent();
        },
        scrollEvent: function () {
            //置顶
            $(window).on('scroll', function () {
                var scrollTop = $(window).scrollTop();
                var bannerHeight = $('.list .top').height();
                if (scrollTop > bannerHeight) {
                    $('.list .slider').addClass('none');
                    $('.list .top').addClass('fixed');
                    $('.list .tab-li').addClass('fixed');
                } else {
                    $('.list .slider').removeClass('none');
                    $('.list .top').removeClass('fixed');
                    $('.list .tab-li').removeClass('fixed');
                }
            });
        }
    };
    Index.init();
    loadData();
})();

function showTabAndLoadData(obj) {
    var $this = $(obj);
    $("#title-ul").find(".active").removeClass("active");
    $this.addClass("active");
    $("#tab-ul>div").each(function () {
        $(this).addClass("none");
    });
    var tag = $this.attr("id");
    $("#" + tag + "Tab").removeClass("none");
}

function loadData() {
    $.ajax({
        url: base + '/wechat/activity/list',
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(new WechatPojo()),
        success: function (results) {
            loadHtml("actListInfo", results.data.wechatList);
            loadHtml("actReviewListInfo", results.data.reviewList);
        }
    });
}

function loadHtml(id, list) {
    var html = '';
    for (key in list) {
        var e = list[key];
        html += '<a href="' + base + '/wechat/activity/info/' + getVal(e.id) + '">';
        html += '<img class="img-box" src="' + getVal(e.coverPath) + '"/>';
        html += '<div class="title">' + getVal(e.title) + '</div>';
        html += '<div class="info">';
        html += '<div class="info-time">' + getDateM(e.beginTime) + '-' + getDateM(e.endTime) + '</div>';
        html += '<div class="info-address">' + getVal(e.address) + '</div>';
        html += '</div>';
        html += '</a>';
    }
    $("#" + id).empty().html(html);
}

function WechatPojo() {
    this.districtId = $("#district").val();
    this.openid = $("#openid").val();
}