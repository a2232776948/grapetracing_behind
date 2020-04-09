package cn.edu.scau.controller;

import cn.edu.scau.model.User;
import cn.edu.scau.service.IRoleService;
import cn.edu.scau.service.impl.UserService;
import cn.edu.scau.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Api(tags = "用户")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private IRoleService roleServiceImpl;

    @ApiOperation("获取所有用户信息")
    @GetMapping("/getAllUsers")
    public Response getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return Response.ok("查找成功", allUsers);
    }

    @ApiOperation("根据用户名模糊查询")
    @GetMapping("/getUserByKeywords")
    public Response getUserByKeywords(String keywords) {
        return Response.ok("查找成功", userService.getUserByKeywords(keywords));
    }

    @ApiOperation("获取当前用户权限")
    @GetMapping("/getAllRoles")
    public Response getAllRoles() {
        return Response.ok("查找成功", roleServiceImpl.getAllRoles());
    }

    @ApiOperation("更改用户")
    @PostMapping("/changeUser")
    public Response changeUser(@RequestBody User user) {
        if (userService.updateUser(user) == 1)
            return Response.ok("更新成功");
        else
            return Response.error("更新失败");
    }

    @ApiOperation("更改权限")
    @PutMapping("/changeRole")
    public Response changeRole(Integer userId, Integer[] rids) {
        if (userService.updateUserRole(userId, rids)) {
            return Response.ok("更新成功!");
        }
        return Response.error("更新失败!");
    }

    @ApiOperation("删除用户")
    @PostMapping("/delete")
    public Response deleteUser(Integer userId) {
        if (userService.deleteByUserId(userId) == 1)
            return Response.ok("删除成功");
        else
            return Response.error("删除失败");
    }

    @ApiOperation("添加用户")
    @PostMapping("/addUser")
    public Response addUser(@RequestBody User user) {
        if (userService.addUser(user) == 1)
            return Response.ok("删除成功");
        else
            return Response.error("删除失败");
    }

    @ApiOperation("获取当前登录用户信息")
    @GetMapping("/info")
    public Response info(Authentication authentication) {
        return Response.ok("查找成功", authentication.getPrincipal());
    }

    @ApiOperation("更改头像 暂时不可用")
    @PostMapping("/userface")
    public Response updateUserface(MultipartFile file, Integer id, Authentication authentication) {
        int n;
        String name = file.getName();
        File file1 = new File(name);
        try (InputStream inputStream = file.getInputStream(); FileOutputStream outputStream = new FileOutputStream(file1)) {
            byte[] bytes = new byte[4096];
            while ((n = inputStream.read(bytes, 0, 4096)) != -1) {
                outputStream.write(bytes, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] buffer = new byte[4096];
        return Response.ok("修改成功");
    }

    @ApiOperation("更新用户信息")
    @PostMapping("/update")
    public Response update(@RequestBody User user, Authentication authentication) {
        if (userService.updateUser(user) == 1) {
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(),authentication.getAuthorities()));
        return Response.ok("更新成功");
        }
        return Response.error("更新失败");
    }

    @ApiOperation("更改密码")
    @PostMapping("/changePwd")
    public Response changePwd(@RequestBody Map<String, Object> info) {
        String oldpwd = (String) info.get("oldpwd");
        String pwd = (String) info.get("pwd");
        Integer userId = (Integer) info.get("user_id");
        System.out.println(oldpwd);
        System.out.println(pwd);
        System.out.println(userId);
        if (userService.updatePwd(oldpwd, pwd, userId)) {
            return Response.ok("更新成功!");
        }
        return Response.error("更新失败!");
    }
}
