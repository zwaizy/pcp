$(function(){
    var userUrl = "/frameDepartment/get";
    var spUrl = "/folder"
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
    $.ajax({
        type: 'GET',
        url: userUrl,
        dataType: 'json',
        data: '',
        success: function (data) {
            if (data.code == 200) {
                var sta = data.data[0].dept_status;
                var status = '';
                if(sta==1){
                    status = "正常"
                }else{
                    status = "不正常"
                }
                var content = $("\
                    <p class='clearfix'> \
                        <span class='title'>登录账号</span>\
                        <span class='con'>"+data.data[0].user_name+"</span>\
                    </p>\
                    <p class='clearfix'> \
                        <span class='title'>名称</span>\
                        <span class='con'>"+data.data[0].dept_name+"</span>\
                    </p>\
                    <p class='clearfix'> \
                        <span class='title'>级别</span>\
                        <span class='con'></span>\
                    </p>\
                    <p class='clearfix'> \
                        <span class='title'>状态</span>\
                        <span class='con'>"+status+"</span>\
                    </p>\
                    <p class='clearfix'> \
                        <span class='title'>开始流程</span>\
                        <span class='con'></span>\
                    </p>\
                    <p class='clearfix'> \
                        <span class='title'>结束流程</span>\
                        <span class='con'></span>\
                    </p>\
                    <p class='clearfix'> \
                        <span class='title'>允许操作的时间段</span>\
                        <span class='con'></span>\
                    </p>\
                    <p class='clearfix'> \
                        <span class='title'>注册时间</span>\
                        <span class='con'></span>\
                    </p>\
                    <p class='clearfix'> \
                        <span class='title'>CDMA手机号</span>\
                        <span class='con'>"+data.data[0].phone+"</span>\
                    </p>\
                    <p class='clearfix'>\
                        <span class='title'>备注</span>\
                        <span class='con'></span>\
                    </p>\
           ")
                $("#detail").append(content);
            } else {
                return false;
            }
        }
    })
    $(".ui-menu").hover(function() {
        if($(this).find("li").length > 0){
            $(this).children("ul").stop(true, true).slideDown(100)
        }
    },function() {
        $(this).children("ul").stop(true, true).slideUp("fast");
    });
})