/*sidebar侧边栏固定效果*/
function listenSidebar() {
    var sidHeight = $('.sidebar').height(),
        winHeight = $(window).scrollTop();

    if (winHeight > sidHeight) {
        $('.sidebar').addClass('fixed').css('top', '0px');
    } else if (winHeight === 0) {
        $('.sidebar').removeClass('fixed');
    }
}

setInterval(function () {
    $(document).on('scroll', function () {
        listenSidebar();
    });
}, 1500);

/*loading效果*/
var loadHtml = '<div class="loading"><div class="loader">Loading...</div></div>';
$('body').append(loadHtml);
var loading = $('.loading');
$(document).ajaxStart(function () {
    loading.show();
});
$(document).ajaxStop(function () {
    loading.hide();
});
$(document).ajaxError(function () {
    layer.msg("请求内容不存在");
});

function getVal(val) {
    if (val === null || val === undefined) {
        return "";
    }
    return val;
}

function getValLen(val) {
    var value = getVal(val);
    if (value.length > 5) {
        return value.substring(0, 5) + "...";
    }
    return value;
}

function getNum0(val) {
    var number = Number(val);
    if (isNaN(number)) {
        return 0;
    }
    return parseInt(number);
}

function getNumNull(val) {
    var number = Number(val);
    if (isNaN(number)) {
        return null;
    }
    return parseInt(number);
}

function getBoolean(val) {
    return (val === true || val === "true" || val === "1" || val === "是");
}

function getDate(d) {
    return new Date(d).format("yyyy-MM-dd hh:mm");
}

function getDateD(d) {
    return new Date(d).format("yyyy-MM-dd");
}

function toTimestamp(str) {
    return new Date(str.replace("-", "/")).valueOf();
}

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1,               //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()              //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};