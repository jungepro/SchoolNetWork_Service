package cn.edu.hhstu.areaSystem.service;

import cn.edu.hhstu.pojo.Manufacturer;

import java.util.List;

public interface  IManufacturerService {
    public List<Manufacturer> list() throws Exception;

    public Manufacturer detail(int id) throws Exception;

    public void insert(Manufacturer entity) throws Exception;

    public void update(Manufacturer entity) throws Exception;

    public void delete(int id) throws Exception;

    public void deleteByIds(int[] ids) throws Exception;
}
