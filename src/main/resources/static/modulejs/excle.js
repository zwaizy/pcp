$(function(){
    $(".content ul li").click(function(){
        $(this).addClass("bg").siblings().removeClass("bg");
        var title = $(this).find(".title").text();
        console.log(title)
    })
    console.log(localStorage.getItem("name"))
    $(".upload").click(function(){
        $(".fileUpload").addClass("show");
    })
})