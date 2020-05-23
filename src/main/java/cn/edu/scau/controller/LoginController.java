package cn.edu.scau.controller;

import cn.edu.scau.config.CompanyConfig;
import cn.edu.scau.config.VerificationCode;
import cn.edu.scau.model.Company;
import cn.edu.scau.model.User;
import cn.edu.scau.service.impl.UserService;
import cn.edu.scau.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    public CompanyConfig companyConfig;
    @Autowired
    private UserService userService;
    @ApiOperation("登陆")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Response appLogin(String username,String password){
        if(username == null || password == null){
            return Response.error("用户名或密码不能为空");
        }
        User user = userService.getUserByName(username);
        if(user == null){
            return Response.error("用户不存在");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!encoder.matches(password, user.getPassword())){
            return Response.error("密码错误");
        }
        return Response.ok("登录成功！");
    }

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/verifyCode", method = RequestMethod.GET)
    public void verifyCode(HttpSession session, HttpServletResponse resp) throws IOException {
        VerificationCode verificationCode = new VerificationCode();
        BufferedImage image = verificationCode.getImage();
        String text = verificationCode.getText();
        session.setAttribute("verify_code", text);
        companyConfig.setVerifCode(text);
        VerificationCode.output(image, resp.getOutputStream());
    }

}
