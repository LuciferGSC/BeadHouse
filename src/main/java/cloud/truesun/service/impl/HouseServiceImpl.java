package cloud.truesun.service.impl;

import cloud.truesun.dao.HouseDao;
import cloud.truesun.domain.House;
import cloud.truesun.domain.PageResult;
import cloud.truesun.domain.User;
import cloud.truesun.service.IHouseService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements IHouseService {
    @Autowired
    HouseDao houseDao;

    /**
     * 获取所有养老院信息
     *
     * @return
     */
    @Override
    public List<House> getAll() {
        List<House> houses = houseDao.selectList(null);
        return houses;
    }

    /**
     * 增加一个养老院信息
     *
     * @param house
     * @return
     */
    public boolean save(House house) {
        int insert = houseDao.insert(house);
        return insert > 0;
    }

    /**
     * 批量增加养老院信息
     *
     * @param houses
     * @return
     */
    public int saveMuch(List<House> houses) {
        int count = 0;
        for (House house : houses) {
            count += houseDao.insert(house);
        }
        return count;
    }

    /**
     * 更改养老院信息
     *
     * @param house
     * @return
     */
    @Override
    public boolean update(House house) {
        int count = houseDao.updateById(house);

        return count > 0;
    }

    /**
     * 删除养老院信息
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Long id) {
        int i = houseDao.deleteById(id);
        return i > 0;
    }

    /**
     * 差一个养老院信息
     *
     * @param id
     * @return
     */
    @Override
    public House selectById(Long id) {
        House house = houseDao.selectById(id);
        return house;
    }

    /**
     * 分页查询
     *
     * @param page
     * @param conditionHouse
     * @return
     */
    @Override
    public PageResult pageSelect(int page, House conditionHouse) {
        //1 创建IPage分页对象,设置分页参数,1为当前页码，12为每页显示的记录数
        IPage<House> result = new Page<>(page, 12);

        // 2 分页查询条件
        LambdaQueryWrapper<House> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(conditionHouse.getHousename() != null, House::getHousename, conditionHouse.getHousename())
                .like(conditionHouse.getAreas() != null, House::getAreas, conditionHouse.getAreas())
                .gt(null != conditionHouse.getMin(), House::getMin, conditionHouse.getMin())
                .lt(null != conditionHouse.getMax(), House::getMax, conditionHouse.getMax())
        ;
        //3 执行分页查询
        IPage<House> houseIPage = houseDao.selectPage(result, queryWrapper);


        return new PageResult(
                houseIPage.getCurrent(),
                houseIPage.getSize(),
                houseIPage.getPages(),
                houseIPage.getTotal(),
                houseIPage.getRecords()
        );
    }
}
