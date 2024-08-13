package cn.edu.hhstu.areaSystem.service.impl;

import cn.edu.hhstu.areaSystem.mapper.IRoomMapper;
import cn.edu.hhstu.areaSystem.service.IRoomService;
import cn.edu.hhstu.pojo.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  RoomService implements IRoomService {

    @Autowired
    private IRoomMapper roomMapper;

    @Override
    public List<Room> list() throws Exception {
        return roomMapper.list();
    }

    @Override
    public Room detail(String id) throws Exception {
        return roomMapper.detail(id);
    }

    @Override
    public void insert(Room entity) throws Exception {
        roomMapper.insert(entity);
    }

    @Override
    public void update(Room entity) throws Exception {
        roomMapper.update(entity);
    }

    @Override
    public void delete(String id) throws Exception {
        roomMapper.delete(id);
    }

    @Override
    public void deleteByIds(String[] ids) throws Exception {
        roomMapper.deleteByIds(ids);
    }
}
