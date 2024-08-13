package cn.edu.hhstu.areaSystem.service;

import cn.edu.hhstu.pojo.Room;

import java.util.List;

public interface  IRoomService {
    public List<Room> list() throws Exception;

    public Room detail(String id) throws Exception;

    public void insert(Room entity) throws Exception;

    public void update(Room entity) throws Exception;

    public void delete(String id) throws Exception;
    public void deleteByIds(String[] ids) throws Exception;
}
