package cloud.truesun;

import cloud.truesun.dao.HouseDao;
import cloud.truesun.dao.UserDao;
import cloud.truesun.domain.House;
import cloud.truesun.domain.PageResult;
import cloud.truesun.domain.User;
import cloud.truesun.service.IHouseService;
import org.apache.ibatis.jdbc.Null;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BeadHouseApplicationTests {
    @Autowired
    private UserDao userDao;
    @Autowired
    private IHouseService houseService;

    @Test
    void contextLoads() {
    }

    @Test
    void testSelectAll() {
        List<User> users = userDao.selectList(null);
        System.out.println(users.toString());
    }

    @Test
    void testHouse() {
        List<House> all = houseService.getAll();
        for (House house : all) {
            System.out.println(house.getDescription());
        }
    }

    @Test
    void testAddOne() {
        House house = new House();
        house.setAddress("guangxi");
        house.setMax(100);
        house.setMin(10);
        house.setHousename("glut");
        house.setAreas("guilin");
        house.setDescription("asodfjasskdfhas");

        houseService.save(house);
    }

    @Test
    void testPage() {
        PageResult pageResult = houseService.pageSelect(1, null);

        System.out.println(pageResult.getPages());
        System.out.println(pageResult.getRecords());
    }

    @Test
    void testGetAllHouses(){
        List<House> all = houseService.getAll();
        System.out.println(all.toString());

    }
}
