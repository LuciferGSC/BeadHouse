package cloud.truesun.service.impl;

import cloud.truesun.domain.User;
import cloud.truesun.dao.UserDao;
import cloud.truesun.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 盖世诚
 * @since 2022-11-06
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDao userDao;

    @Override
    public boolean save(User user) {
        boolean flag;
        //查询是否有这个用户电话
        User user1 = userDao.selectByTelephone(user.getTelephone());
        if (user1 == null) {        //用户不存在
            userDao.insert(user);
            flag = true;
        } else {
            // 用户存在
            flag = false;
        }

        return flag;

    }

    @Override
    public boolean update(User user) {
        int count = userDao.updateById(user);

        return count > 0;
    }

    @Override
    public boolean delete(Long id) {
        int count = userDao.deleteById(id);
        return count > 0;
    }

    @Override
    public User selectByTelephone(User user) {
        User aUser = userDao.selectByTelephone(user.getTelephone());

        return aUser;
    }

    @Override
    public User selectById(Long id) {
        User user = userDao.selectById(id);
        return user;
    }


    @Override
    public List<User> getAll() {
        List<User> users = userDao.selectList(null);
        return users;
    }

}
