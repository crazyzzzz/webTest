package com.course.server;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.bean.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "/", description = "这是我全部的post请求")
@RequestMapping(value = "/v1")
public class MyPostMethod {

    @RequestMapping(value = "/getcookies", method = RequestMethod.POST)
    @ApiOperation(value = "通过这个Post方法可以获取到cookies", httpMethod = "POST")
    public String getCookies(HttpServletResponse httpServletResponse,
            @RequestParam(value = "userName", required = true) String userName,
            @RequestParam(value = "password", required = true) String password) {
        if (userName.equals("zhangshan") && password.equals("123456")) {
            Cookie cookie = new Cookie("login", "true");
            httpServletResponse.addCookie(cookie);
            return "获取到cookies信息成功";
        }
        return "用户名或者密码不对";
    }

    @RequestMapping(value="/getUserList",method=RequestMethod.POST)
    @ApiOperation(value="获取用户列表",httpMethod="POST")
    public String userList(HttpServletRequest httpServletRequest, @RequestBody User u) {
        // 获取cookies
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if ("login".equals(cookie.getName()) && "true".equals(cookie.getValue())
                    && "zhangshan".equals(u.getUserName()) && "123456".equals(u.getPassword())) {
                User user = new User();
                user.setName("lisi");
                user.setAge("18");
                user.setSex("man");
                return user.toString();
            }
        }

        return "参数不合法";
    }

}
