$(function(){
    var userUrl = "/frameDepartment/get";
    $.ajax({
        type: 'GET',
        url: userUrl,
        dataType: 'json',
        data: {},
        success: function (data) {
            if (data.code == 200) {
                console.log(data)
            } else {
                return false;
            }
        }
    })
})