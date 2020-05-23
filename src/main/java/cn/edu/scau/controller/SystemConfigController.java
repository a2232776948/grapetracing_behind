package cn.edu.scau.controller;

import cn.edu.scau.model.Menu;
import cn.edu.scau.model.User;
import cn.edu.scau.service.IMenuService;
import cn.edu.scau.service.impl.MenuServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "系统")
@RestController
@RequestMapping("/system/config")
public class SystemConfigController {
    @Autowired
    private IMenuService menuServiceImpl;

    @ApiOperation("获取菜单")
    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public List<Menu> getMenusByUserId(){
        //HttpServletRequest req = (HttpServletRequest) servletRequest;
        //req.getSession().getAttribute("");
        return menuServiceImpl.getMenuByUserId();
    }
}
