package cloud.truesun.service;

import cloud.truesun.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 盖世诚
 * @since 2022-11-06
 */
@Transactional
public interface IUserService {
    /**
     * 保存
     *
     * @param user
     * @return
     */
    public boolean save(User user);

    /**
     * 修改
     *
     * @param user
     * @return
     */
    public boolean update(User user);

    /**
     * 按id删除
     *
     * @param id
     * @return
     */
    public boolean delete(Long id);

    /**
     * 通过电话查询
     * @param user
     * @return
     */
    User selectByTelephone(User user);

    User selectById(Long id);
    /**
     * 查询全部
     *
     * @return
     */
    public List<User> getAll();

}
