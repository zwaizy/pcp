$(function(){
    var spExcle = "/file/show?fileName="
    var flag = ""
    $("#mulu ").on("click","li",function(){
        $(this).addClass("bg").siblings().removeClass("bg");
        var title = $(this).find(".title").text();
        localStorage.setItem("name",title);
        console.log(flag)
        if(!flag){
            var exUrl = "/excle_sp"
        }else{
            var exUrl = "/excle"
        }
        $("#ex").show().attr("href",exUrl);
        $("#oldex").hide();
        console.log(title)
    })
    var fileUrl = "/file/show" ;
    //数据回显
    $.ajax({
        type: 'GET',
        url: fileUrl,
        dataType: 'json',
        data: '',
        success: function (data) {
            if (data.code == 200) {
                var arr = data.data.fileName ;
                flag = data.data.flag;
                $.each(arr, function (index, item) {
                    $("#mulu").append($('<li/>')
                        .append($('<span class="pic"></span>'))
                        .append($('<span class="title"></span>').html(item))
                    );
                })
            } else {
                return false;
            }
        }
    })
})