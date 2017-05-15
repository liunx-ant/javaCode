/**
 * Created by joyC on 2016/4/28.
 */
$(function(){
    $(".search-more i").removeClass("fa-angle-up").addClass("fa-angle-down");
    searchHide();
    $(".search-more").bind("click",function(){
        if($(".search-more .fa-angle-down").length!=0){
            $(".form-group-Wdate").fadeIn();
            $(".form-group").fadeIn();
            $(this).children("i").removeClass("fa-angle-down").addClass("fa-angle-up");
        }else{
            $(this).children("i").removeClass("fa-angle-up").addClass("fa-angle-down");
            searchHide();
        }
    })
    function searchHide() {
        $(".clear-left").removeClass("clear-left");
        var searchWidth= 0,_len=1;
        $(".tb_search>div>div").each(function () {
            $(this).attr("style","");
            $(this).find("label").attr("style","");
            $(this).show();
            $(this).hasClass("form-group")&&$(this).removeClass("form-group-Wdate");
            searchWidth+=$(this).width() + 20;
            if($(".iframe-cot").width()-64<searchWidth){
                _len++;
                if(_len==2){
                    $(this).addClass("clear-left");
                }
                if($(".tb_search>div>div:nth-child("+ _len+")").hasClass("form-group-Wdate")){
                    $(this).addClass("form-group-Wdate");
                }
                if($(".tb_search>div>div:nth-child("+ _len+")").find("label").width()>$(this).find("label").width()){
                    $(this).find("label").width(($(".tb_search>div>div:nth-child("+ _len+")").find("label").width()+1) + "px").css("text-align","right");
                }else{
                    $(".tb_search>div>div:nth-child("+ _len+")").find("label").width(($(this).find("label").width()+1) + "px").css("text-align","right");
                }
                if($(".tb_search>div>div:nth-child("+ _len+")").width()>$(this).width()){
                    $(this).width(($(".tb_search>div>div:nth-child("+ _len+")").width()+1) + "px");
                }else{
                    $(".tb_search>div>div:nth-child("+ _len+")").width(($(this).width()+1) + "px");
                }
                $(this).hide();
            }
        });
        if(_len==1){
            $(".search-more").hide();
        }else {
            $(".search-more").show();
        }
    }
    $(window).resize(function () {
        searchHide();
    })
});
