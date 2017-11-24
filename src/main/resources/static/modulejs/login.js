//用户添加表单的异步提交--编辑操作
$(function() {
	$("#img_captcha").attr("src", "/images/kaptcha.jpg");
	$("#img_captcha").click(
			function() {
				$("#img_captcha").attr(
						"src",
						"/images/kaptcha.jpg?seed="
								+ Math.random());
			});
	// 文本框获得焦点 显示清楚图标
	$('.c_right ul li span input').focus(function() {
		$(this).parents('li').addClass('bor');
		$(this).siblings('a').show();
	})
	$('.c_right ul li span input').blur(function() {
		$(this).parents('li').removeClass('bor');
	});
	$('.c_right ul li span a').on('click', function() {
		$(this).siblings('input').val('');
		$(this).hide();
	});

	$("body").keydown(function() {
		if (event.keyCode == "13") {// keyCode=13是回车键
			$('#submit').click();
		}
	});
});
