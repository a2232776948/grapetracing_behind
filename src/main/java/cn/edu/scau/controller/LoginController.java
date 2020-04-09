package cn.edu.scau.controller;

import cn.edu.scau.config.VerificationCode;
import cn.edu.scau.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Api(tags = "登陆")
@RestController
public class LoginController {
    @ApiOperation("登陆")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Response login(){
        return Response.error("尚未登陆，请登录！");
    }

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/verifyCode", method = RequestMethod.GET)
    public void verifyCode(HttpSession session, HttpServletResponse resp) throws IOException {
        VerificationCode verificationCode = new VerificationCode();
        BufferedImage image = verificationCode.getImage();
        String text = verificationCode.getText();
        session.setAttribute("verify_code", text);
        VerificationCode.output(image, resp.getOutputStream());
    }

}
