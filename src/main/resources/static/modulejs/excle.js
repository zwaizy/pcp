$(function(){
    $("#mulu").on("click","li",function(){
        $(this).addClass("bg").siblings().removeClass("bg");
        var title = $(this).find(".title").text();
        console.log(title)
    })

    $(".upload").click(function(){
        $(".fileUpload").addClass("show");
    })
    //数据回显
    //console.log(localStorage.getItem("name"))
    var excleUrl = "/file/show?fileName="+localStorage.getItem("name")
    $.ajax({
        type: 'GET',
        url: excleUrl,
        dataType: 'json',
        data: '',
        success: function (data) {
            if (data.code == 200) {
                var arr = data.data.fileName ;
                if(arr){
                    $.each(arr, function (index, item) {
                        $("#mulu").append($('<li/>')
                            .append($('<span class="pic"></span>'))
                            .append($('<span class="title"></span>').html(item))
                        );
                    })
                }

            } else {
                return false;
            }
        }
    })
})