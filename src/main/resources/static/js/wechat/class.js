(function(){
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