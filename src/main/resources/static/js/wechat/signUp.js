(function(){
    var Index = {
        init: function(){
            var self = this;
            self.checkInput();
        },
        dialog: function(){

        },
        checkInput: function(){
            var self = this;
            var reg = /^1[3,4,5,7,8][0-9]{9}$/;
            $('.J_submit').on('click', function(){
                $('.form-li input[type=text]').each(function(index,el) {
                    var val = $.trim($(el).val())
                    if(val == ''){
                        $(el).focus().addClass('onfocus');
                        return false;
                    } else {
                        if($(el).attr('name') == 'tel'){
                            if(!reg.test(tel)){
                                $('.error-tip').text($(el).attr('placeholder')).removeClass('none');
                                setTimeout(function () {
                                    $('.error-tip').addClass('none');
                                }, 2000);
                            }
                        }
                        
                    }
                });
            });
        }
    };
    Index.init();
})();