package cloud.truesun.controller;


import cloud.truesun.dao.UserDao;
import cloud.truesun.domain.Code;
import cloud.truesun.domain.Result;
import cloud.truesun.domain.User;
import cloud.truesun.service.IUserService;
import cloud.truesun.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 盖世诚
 * @since 2022-11-06
 */
@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    @Autowired
    private IUserService userService;


    @PostMapping
    public Result save(@RequestBody User user) {
        // 创建用户
        boolean flag = userService.save(user);
        if (flag) {
            return new Result(Code.SAVE_OK, null, "注册成功");
        } else {
            return new Result(Code.SAVE_ERR, null, "用户已存在");
        }

    }

    @PutMapping
    public Result update(@RequestBody User user) {
        boolean flag = userService.update(user);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean flag = userService.delete(id);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        User user = userService.selectById(id);
        Integer code = user != null ? Code.GET_OK : Code.GET_ERR;
        String msg = user != null ? "" : "数据查询失败，请重试！";
        return new Result(code,user,msg);
    }

    @GetMapping
    public Result getAll() {
        List<User> userList = userService.getAll();
        Integer code = userList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = userList != null ? "" : "数据查询失败，请重试！";
        return new Result(code, userList, msg);
    }


}

