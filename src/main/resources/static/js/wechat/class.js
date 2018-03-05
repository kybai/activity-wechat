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
    grantAuthInfo();
})();

function compareTime() {
    var currentTime = new Date().valueOf();
    $(".compare-time").each(function () {
       var $this = $(this);
       var time = toTimestamp($this.attr("time"));
       if (time < currentTime) {
           $this.addClass("orange").html("缺勤");
       } else {
           $this.html("未开始");
       }
    });
}