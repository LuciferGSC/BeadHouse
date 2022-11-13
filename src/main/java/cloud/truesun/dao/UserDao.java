package cloud.truesun.dao;

import cloud.truesun.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 盖世诚
 * @since 2022-11-06
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
    @Select("select * from beadhouse.user where telephone=#{telephone}")
    User selectByTelephone(String telephone);
}
