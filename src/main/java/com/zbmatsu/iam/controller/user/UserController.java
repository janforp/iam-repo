package com.zbmatsu.iam.controller.user;

import com.zbmatsu.iam.service.UserService;
import com.zbmatsu.iam.vo.UpdateUserBean;
import com.zbmatsu.iam.vo.UserBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(produces = "application/json;charset=utf-8")
public class UserController {

    @Autowired
    protected UserService userService;

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserBean createUser(@Validated @RequestBody UserBean userBean) {

        return userService.createUser(userBean);
    }

    @ApiOperation(value = "删除用户",notes = "删除用户")
    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUser(@PathVariable String id){

        userService.deleteUser(id);
    }

    @ApiOperation(value = "修改用户",notes = "修改用户")
    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserBean updateUser(@PathVariable String id, @Validated @RequestBody UpdateUserBean updateUserBean){

        return userService.updateUser(id, updateUserBean);
    }


    @ApiOperation(value = "修改用户状态",notes = "修改用户状态")
    @PutMapping("/user/{id}/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public UserBean updateUserStatus(@PathVariable String id, @PathVariable String status){

        UserBean userBean = userService.getUserById(id);
        userBean.setStatus(status);
        return userService.updateUser(userBean);
    }

    @ApiOperation(value = "修改用户密码",notes = "修改用户密码")
    @PutMapping("/user/{id}/password/{password}")
    @ResponseStatus(HttpStatus.OK)
    public UserBean updateUserPassword(@PathVariable String id, @PathVariable String password){

        UserBean userBean = userService.getUserById(id);
        userBean.setPassword(password);
        return userService.updateUser(userBean);
    }

    @ApiOperation(value = "修改用户角色",notes = "修改用户角色")
    @PutMapping("/user/{id}/role/{role}")
    @ResponseStatus(HttpStatus.OK)
    public UserBean updateUserRole(@PathVariable String id, @PathVariable String role){

        UserBean userBean = userService.getUserById(id);
        userBean.setRole(role);
        return userService.updateUser(userBean);
    }

    @ApiOperation(value="根据ID获取用户信息", notes="根据ID获取用户信息")
    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserBean getUserById(@PathVariable String id){

        return userService.getUserById(id);
    }

    @ApiOperation(value="根据用户名和密码获取用户信息", notes="根据用户名和密码获取用户信息")
    @GetMapping("/user/auth/.search")
    @ResponseStatus(HttpStatus.OK)
    public UserBean getUserByUserNameAndPassword(@RequestParam("userName") String userName, @RequestParam("password") String password){

        return userService.getUserByUsernameAndPassword(userName, password);
    }

    @ApiOperation(value="根据IDs批量删除", notes="根据IDs获取User列表")
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void batchDeleteUsers(@RequestBody List<String> ids){

        List<Serializable> list = new ArrayList<>();
        ids.stream().forEach(id -> list.add(id));

        userService.batchDeleteUsers(list);
    }

    @ApiOperation(value="获取所有的用户列表", notes="获取所有的用户列表")
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserBean> getThingListByIds(){

        return userService.getAllUser();
    }
}
