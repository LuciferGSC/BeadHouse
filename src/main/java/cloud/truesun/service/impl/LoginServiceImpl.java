package cloud.truesun.service.impl;

import cloud.truesun.dao.UserDao;
import cloud.truesun.domain.Code;
import cloud.truesun.domain.Result;
import cloud.truesun.domain.User;
import cloud.truesun.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/check")
@CrossOrigin
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private UserDao userDao;

    @PostMapping
    public Result checkLogin(@RequestBody User user) {

        User user1 = userDao.selectByTelephone(user.getTelephone());
        if (user1 == null) {
            return new Result(Code.LOGIN_ERR, null, "没有此用户");
        } else {
            if (user.getUserpwd().equals(user1.getUserpwd())) {
                return new Result(Code.LOGIN_OK, user1, "登陆成功");
            } else {
                return new Result(Code.PASSWORD_ERR, null, "密码错误");
            }
        }
    }


}
