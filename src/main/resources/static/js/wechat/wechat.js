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

function getBoolean(val) {
    return (val === true || val === "true" || val === "1" || val === "是");
}

function getDate(d) {
    return new Date(d).format("yyyy-MM-dd hh:mm");
}

function getDateD(d) {
    return new Date(d).format("yyyy-MM-dd");
}

function getDateM(d) {
    return new Date(d).format("MM月dd日");
}

function toTimestamp(str) {
    return new Date(str.replace("-", "/")).valueOf();
}

//获取微信OPENID，正常和授权俩种
function getWechatStorage() {
    return getStorage('ACTIVITY_WECHAT_OPENID');
}

function getStorage(key) {
    return getVal(localStorage[key]);
}

function setStorage(key, val) {
    localStorage[key] = getVal(val);
}

//判断是否是微信浏览器
function isWeiXin() {
    var ua = window.navigator.userAgent.toLowerCase();
    return ua.match(/MicroMessenger/i) === 'micromessenger';
}

//若是微信浏览器，并且openid不为空，无须用户点击确认
// function grantAuth() {
//     if (isWeiXin() && getWechatStorage() !== "") {
//         window.href.location = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + "&redirect_uri=" + base + "/activity/wechat/portal/index" + "&response_type=code&scope=snsapi_base&state=INDEX#wechat_redirect";
//     }
// }

//若是微信浏览器，并且openid不为空，需要用户点击确认
function grantAuthInfo() {
    if (isWeiXin() && getWechatStorage() !== "") {
        window.href.location = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + "&redirect_uri=" + base + "/activity/wechat/portal/index" + "&response_type=code&scope=snsapi_userinfo&state=INDEX#wechat_redirect";
    }
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