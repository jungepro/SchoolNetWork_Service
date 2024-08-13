package cn.edu.hhstu.areaSystem.service.impl;

import cn.edu.hhstu.areaSystem.mapper.IManufacturerMapper;
import cn.edu.hhstu.areaSystem.service.IManufacturerService;
import cn.edu.hhstu.pojo.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  ManufacturerService implements IManufacturerService {

    @Autowired
    private IManufacturerMapper manufacturerMapper;

    @Override
    public List<Manufacturer> list() throws Exception {
        return manufacturerMapper.list();
    }

    @Override
    public Manufacturer detail(int id) throws Exception {
        return manufacturerMapper.detail(id);
    }

    @Override
    public void insert(Manufacturer entity) throws Exception {
        manufacturerMapper.insert(entity);
    }

    @Override
    public void update(Manufacturer entity) throws Exception {
        manufacturerMapper.update(entity);
    }

    @Override
    public void delete(int id) throws Exception {
        manufacturerMapper.delete(id);
    }

    @Override
    public void deleteByIds(int[] ids) throws Exception {
        manufacturerMapper.deleteByIds(ids);
    }
}
