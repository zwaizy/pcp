$(function(){
    var userUrl = "/frameDepartment/get";
    var spUrl = "/folder"
    $.ajax({
        type: 'GET',
        url: userUrl,
        dataType: 'json',
        data: '',
        success: function (data) {
            if (data.code == 200) {
                var arr = data.data ;
                for(var i = 0 ; i < arr.length ; i++){
                    if(arr[i].dept_status == 1){
                        arr[i].dept_status = "正常"
                    }else{
                        arr[i].dept_status = "不正常"
                    }
                }
                $.each(arr, function (index, item) {
                    // 2.1 Create table column for group
                    // 2.2 Create a new row and append 3 columns (ip+url, group, tags)
                    $("#detail").append($('<tr/>')
                        .append($('<td/>').html("<input type='radio' name='radio' value='+index+'/>"))
                        .append($('<td/>').html(item.user_name))
                        .append($('<td/>').html(item.dept_name))
                        .append($('<td/>').html(item.dept_status))
                        .append($('<td/>').html(" "))
                        .append($('<td/>').html(" "))
                        .append($('<td/>').html(" "))
                        .append($('<td/>').html(item.phone))
                    );
                } )
            } else {
                return false;
            }
        }
    })
    // $(".nav").click(function(){
    //     console.log(1)
    // })
    $(".ui-menu").hover(function() {
        if($(this).find("li").length > 0){
            $(this).children("ul").stop(true, true).slideDown(100)
        }
    },function() {
        $(this).children("ul").stop(true, true).slideUp("fast");
    });
    $("#sp").click(function () {
        console.log(1)
        $.ajax({
            type: 'GET',
            url: spUrl,
            dataType: 'json',
            data: '',
            success: function (data) {

            }
        })
    })
})