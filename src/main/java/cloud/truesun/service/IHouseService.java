package cloud.truesun.service;

import cloud.truesun.domain.House;
import cloud.truesun.domain.PageResult;


import java.util.List;

public interface IHouseService {
    List<House> getAll();
    boolean save(House house);
    int saveMuch(List<House> houses);

    public boolean update(House house);

    boolean delete(Long id);

    House selectById(Long id);

   PageResult pageSelect(int page, House conditionHouse);
}
