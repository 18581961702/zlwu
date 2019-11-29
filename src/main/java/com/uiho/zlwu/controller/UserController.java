package com.uiho.zlwu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uiho.zlwu.model.User;
import com.uiho.zlwu.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userservice;

    @RequiresPermissions(value = "user:find")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User findUser(@PathVariable Integer id){
        return userservice.findUser(id);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestBody User user){
        if (user == null){
            return "请传入正确的json数据";
        }
        log.info("登录用户的信息："+ JSON.toJSONString(user));
        JSONObject jsonObject = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(),user.getPassword());
        try{
            subject.login(token);
            jsonObject.put("token",subject.getSession().getId());
            jsonObject.put("msg","登录成功_yes");
        }catch (IncorrectCredentialsException e){
            jsonObject.put("msg","密码错误");
        }catch (LockedAccountException e){
            jsonObject.put("msg","登录失败，该用户被冻结");
        }catch (AuthenticationException e){
            log.info("用户不存在");
            jsonObject.put("msg","用户不存在");
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @RequiresPermissions(value = "user:list")
    public PageInfo<User> listUser(){
        log.info("获取用户");
        Integer pageNum = 1;
        Integer pageSize = 3;

        List<User> list;
        PageInfo<User> pageInfo = null;
        try{
            Page<User> page = PageHelper.startPage(pageNum,pageSize);
            list = userservice.findUsers();
            log.info(JSON.toJSONString(list));
            pageInfo = new PageInfo<>(list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageInfo;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    @RequiresPermissions(value = "user:edit")
    public String editUser(){
        log.info("更改用户");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg","更改用户");
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @RequiresPermissions(value = "user:delete")
    public String delUser(){
        log.info("删除用户");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg","删除用户");
        return jsonObject.toJSONString();
    }

    @RequestMapping("/unauth")
    public Object unauth(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "1000000");
        map.put("msg", "未登录");
        return map;
    }
}
