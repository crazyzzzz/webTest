package com.course.control;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/v2")
@Api(value = "/", description = "这是我的第一个版本的demo")
public class Demo extends SqlSessionDaoSupport   {

    @RequestMapping(value = "/getUserCount", method = RequestMethod.GET)
    @ApiOperation(value = "可以获取到用户总数", httpMethod = "GET")
    public int getUserCount() {
        int count = this.getSqlSession().selectOne("getUserCount");
        return count;
    }
    
    @RequestMapping(value="/addUser",method=RequestMethod.POST)
    @ApiOperation(value = "新增用户数", httpMethod = "POST")
    public int addUser(@RequestBody User user) {
        int result = this.getSqlSession().insert("addUser", user);
        System.out.println("新增用户："+result+"名，成功！");
        return result;
    }
    
    @RequestMapping(value="/updateUser",method=RequestMethod.POST)
    @ApiOperation(value = "更新用户", httpMethod = "POST")
    public int updateUser(@RequestBody User user) {
        int result = this.getSqlSession().update("updatesUser", user);
        System.out.println("更新用户："+result+"名，成功！");
        return result;
    }
    
    @RequestMapping(value="/deleteUser",method=RequestMethod.GET)
    @ApiOperation(value = "删除用户", httpMethod = "GET")
    public int updateUser(@RequestParam Integer id) {
        int result = this.getSqlSession().delete("deletesUser", id);
        System.out.println("删除用户："+result+"名，成功！");
        return result;
    }
    
    
    
    
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}
