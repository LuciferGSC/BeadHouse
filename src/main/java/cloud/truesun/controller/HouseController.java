package cloud.truesun.controller;

import cloud.truesun.domain.Code;
import cloud.truesun.domain.House;
import cloud.truesun.domain.PageResult;
import cloud.truesun.domain.Result;

import cloud.truesun.service.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/houses")
@CrossOrigin
public class HouseController {
    @Autowired
    private IHouseService houseService;


    @PostMapping
    public Result save(@RequestBody House house) {
        boolean flag = houseService.save(house);
        if (flag) {
            return new Result(Code.SAVE_OK, null, "保存成功");
        } else {
            return new Result(Code.SAVE_ERR, null, "失败");
        }
    }

    //    插入多条数据时
    @PostMapping("/much")
    public Result saveMuch(@RequestBody List<House> houses) {
        int count = houseService.saveMuch(houses);
        if (count > 0) {
            return new Result(Code.SAVE_OK, null, "保存个数" + count);
        } else {
            return new Result(Code.SAVE_ERR, null, "保存失败");
        }
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean flag = houseService.delete(id);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }

    @PutMapping
    public Result update(@RequestBody House house) {
        boolean flag = houseService.update(house);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        House house = houseService.selectById(id);
        Integer code = house != null ? Code.GET_OK : Code.GET_ERR;
        String msg = house != null ? "" : "数据查询失败，请重试！";
        return new Result(code, house, msg);
    }

    @GetMapping
    public Result getAll() {
        List<House> houseList = houseService.getAll();
        Integer code = houseList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = houseList != null ? "" : "数据查询失败，请重试！";
        return new Result(code, houseList, msg);
    }

    @PostMapping("/{pageCount}")
    public Result pageSelect(@PathVariable("pageCount") int pageCount, @RequestBody House conditionHouse) {
        PageResult pageResult = houseService.pageSelect(pageCount, conditionHouse);
        return new Result(pageResult == null ? Code.PAGES_ERR : Code.PAGES_OK,
                pageResult, pageResult == null ? "查询失败或为空" : "查询成功");
    }

}
