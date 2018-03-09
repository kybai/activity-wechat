(function(){
    compareTime();
    var Index = {
        init: function(){
            this.clickEvent();
        },
        clickEvent: function(){
            $('.J_sign').on('click', function(){
                $('.overbg').removeClass('none');
                $('.sign-aciton-con').removeClass('none');
            });
            $('.close').on('click', function(){
                $('.overbg').addClass('none');
                $('.sign-aciton-con').addClass('none');
            });
        }
    };
    Index.init();
})();

function compareTime() {
    var currentTime = new Date().getTime();
    $(".compare-time").each(function (index,el) {
       var $el = $(el);
       var time = new Date($el.attr("time")).getTime();
       if (time < currentTime) {
           $el.addClass("orange").html("缺勤");
       } else {
           $el.html("未开始");
       }
    });
}