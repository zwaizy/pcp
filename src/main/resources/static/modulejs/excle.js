$(function(){
    var title = "";
    $("#mulu").on("click","li",function(){
        $(this).addClass("bg").siblings().removeClass("bg");
        title = $(this).find(".title").text();
        var data = localStorage.getItem("name");
        $(".download").attr("href","/file/downloadFile?dirName="+data+"&fileName="+title)
    })

    $(".upload").click(function(){
        $(".fileUpload").addClass("show");
    })
    $(".close").click(function(){
        $(".fileUpload").removeClass("show");
    })
    var timeUrl = "/time/now";
    //时间回显
    $.ajax({
        type: 'GET',
        url: timeUrl,
        dataType: 'json',
        data: '',
        success: function (data) {
            if (data.code == 200) {
                $(".time").html(data.data)
            } else {
                return false;
            }
        }
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
    //删除
    $("#remove").on("click",function(){
        alert(1)
    });
    //提交
    $('#btn').on('click', function () {
        var data = localStorage.getItem("name")
        $('.fileUpload input[name="dirName"]').val(data) ;
        $('.fileUpload form').ajaxSubmit({ success:function(data){
            //console.log(arguments);
            if(data.message == "SUCCESS"){
                window.location.reload();
            }
        } });
    })
    //返回
    $("#back").click(function(){
        window.history.back()
    })
    // 删除
    $(".removebth").click(function(){
        var data = localStorage.getItem("name")
        $('.remove input[name="dirName"]').val(data) ;
        $('.remove input[name="fileName"]').val(title) ;
        $('.remove ').ajaxSubmit({ success:function(data){
            console.log(arguments);
            if(data.message == "SUCCESS"){
                window.location.reload();
            }
        } });
    })
})