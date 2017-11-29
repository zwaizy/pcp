$(function(){
    var spExcle = "/file/show?fileName="
    $("#mulu ").on("click","li",function(){
        $(this).addClass("bg").siblings().removeClass("bg");
        var title = $(this).find(".title").text();
        localStorage.setItem("name",title);
        $("#ex").show().attr("href","/excle");
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