package com.vcread.pcp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Constants;
import com.vcread.pcp.util.base.VerifyCodeUtils;

/**
 * Created by yangting on 2017/8/7.
 */
@Controller
@RequestMapping("/images/")
public class CaptchaController {

    @RequestMapping("/kaptcha.jpg")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //生成4位验证码
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, verifyCode);
        //生成图片
        //生成图片
        int w = 125, h = 45;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }
}
