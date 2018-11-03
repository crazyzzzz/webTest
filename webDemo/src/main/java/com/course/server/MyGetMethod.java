package com.course.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="/",description="这是我全部的get方法")
public class MyGetMethod {

    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    @ApiOperation(value="通过这个方法可以获取到cookies",httpMethod = "GET")
    public String getCookies(HttpServletResponse httpResponse) {
        Cookie cookie = new Cookie("login", "true");
        httpResponse.addCookie(cookie);
        return "恭喜你获得Cookies";
    }

    @RequestMapping(value = "/getWithCookies", method = RequestMethod.GET)
    @ApiOperation(value="你必须携带cookies信息来访问",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (Objects.isNull(cookies)) {
            return "你必须携带cookies信息来";
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                return "访问cookies信息成功";
            }
        }
        return "你必须携带cookies信息来";
    }

    @RequestMapping(value = "/getWithParams", method = RequestMethod.GET)
    @ApiOperation(value="第一种带参数的访问",httpMethod = "GET")
    public Map<String, Integer> getWithParams(@RequestParam Integer start,
            @RequestParam Integer end) {
        Map<String, Integer> myList= new HashMap<String, Integer>();
        myList.put("方便面", 1);
        myList.put("衣服", 200);
        myList.put("运动鞋", 300);
        return myList;
    }
    
    @RequestMapping(value="/getWithVariableParams/{start}/{end}")
    @ApiOperation(value="第二种带参数的访问",httpMethod = "GET")
    public Map<String, Integer> getWithVariableParams(@PathVariable Integer start,@PathVariable Integer end){
        Map<String, Integer> myList= new HashMap<String, Integer>();
        myList.put("方便面", 1);
        myList.put("衣服", 200);
        myList.put("运动鞋", 300);
        return myList; 
    }
    
}
