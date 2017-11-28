$(function(){
    var codeUrl = "/images/kaptcha.jpg";
    var url_login = "/loginPost"
    // 验证码图片赋值
    $('#num').attr('src', codeUrl);
    $('.shua').on('click', function () {
        $("#num").attr('src', codeUrl+'?'+Math.random());
    })
    // 点击登录按钮时进行判断
    $('.btn').on('click', function () {
        var user = $('.login input[name="username"]').val().trim(); // 用户名
        var pwd = $('.login input[name="password"]').val().trim(); // 密码
        var code = $('.login input[name="captcha"]').val().trim(); // 验证码

        if (user == '') {
            $('.login .error').show().html('请输入账号');
            return false;
        } else if (user.length < 2) {
            $('.login .error').show().html('账号长度在2到20个字符');
            return false;
        } else if (pwd == '') {
            $('.login .error').show().html('请输入密码');
            return false;
        } else if (pwd.length < 6) {
            $('.login .error').show().html('密码长度在6到20个字符');
            return false;
        } else if (code == '') {
            $('.login .error').show().html('请输入验证码');
            return false;
        } else {
            //$('.login form').submit();
            var password = $.md5(pwd)
            $.ajax({
                type: 'POST',
                url: url_login,
                dataType: 'json',
                data: {
                    'username': user,
                    'password': password,
                    'captcha': code
                },
                success: function (data) {
                    if (data.code == 200) {

                        if(data.data == false){

                        }else{

                        }
                    } else {
                        $('.login .error').show().html(data.message);
                        return false;
                    }
                }
            })
        }
    })
})