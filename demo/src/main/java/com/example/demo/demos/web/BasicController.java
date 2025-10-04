/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.demo.demos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.demos.web.User;
import com.example.demo.dao.UserDao;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@RestController
//@Controller
public class BasicController {
    @Resource
    UserDao userDao;
    // http://127.0.0.1:8080/hello?name=lisi
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        System.out.println("前端传来的 name 参数：" + name);
        return "Hello " + name;
    }

    // http://127.0.0.1:8080/user
    @RequestMapping("/user")
    @ResponseBody
    public User user() {
        User user = new User();
        user.setName("theonefx");
        user.setAge(666);
        return user;
    }

    // http://127.0.0.1:8080/save_user?name=newName&age=11
    @RequestMapping("/save_user")
    @ResponseBody
    public String saveUser(User u) {
        userDao.insert(u);
        return "user will save: name=" + u;
    }

    // http://127.0.0.1:8080/html
    @RequestMapping("/html")
    public String html() {
        return "index.html";
    }

    @ModelAttribute
    public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name
            , @RequestParam(name = "age", defaultValue = "12") Integer age, User user) {
        user.setName("zhangsan");
        user.setAge(18);
    }
    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:8081")
    public Map<String, Object> login(@RequestBody User loginUser) {
        Map<String, Object> result = new HashMap<>();

        // 直接用用户名查用户
        User user = userDao.selectOne(
                new QueryWrapper<User>().eq("name", loginUser.getName())
        );

        if (user != null && user.getPassword().equals(loginUser.getPassword())) {
            result.put("success", true);
            result.put("message", "登录成功");
            result.put("user", user); // 也可以只返回 name、age，不要密码
            System.out.println("登录成功");
        } else {
            result.put("success", false);
            result.put("message", "用户名或密码错误");
            System.out.println("登录失败");
        }
        return result;
    }
    @PostMapping("/get")
    public Map<String, Object> get(@RequestBody User requestUser) {
        Map<String, Object> result0 = new HashMap<>();

        // 直接用用户名查用户
        User user = userDao.selectOne(
                new QueryWrapper<User>().eq("name", requestUser.getName())
        );

        if (user != null ) {
            result0.put("success", true);
            result0.put("message", "查询成功");
            result0.put("user", user); // 也可以只返回 name、age，不要密码
            System.out.println("查询成功");
        } else {
            result0.put("success", false);
            result0.put("message", "用户名或密码错误");
            System.out.println("查询失败");
        }
        return result0;
    }
}
