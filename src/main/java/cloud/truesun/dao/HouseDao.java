package cloud.truesun.dao;

import cloud.truesun.domain.House;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HouseDao extends BaseMapper<House> {
}
