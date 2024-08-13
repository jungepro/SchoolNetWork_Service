package cn.edu.hhstu.areaSystem.mapper;

import cn.edu.hhstu.pojo.Room;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.annotation.ManagedBean;
import java.util.List;

@Mapper
@Repository
public interface  IRoomMapper {
    //列表查询
    @Select("select * from Room")
    public List<Room> list() throws Exception;

    //详细信息
    @Select("select * from Room where id=#{id}")
    public Room detail(String id) throws Exception;

    //新增保存
    @Insert("insert into Room (id,roomName,co2Fire,powderFire,foamFire,remark) values (#{id},#{roomName},#{co2Fire},#{powderFire},#{foamFire},#{remark})")
    public void insert(Room model) throws Exception;

    //更新保存
    @Update("update Room set roomName=#{roomName},co2Fire=#{co2Fire},powderFire=#{powderFire},foamFire=#{foamFire},remark=#{remark} where id=#{id}")
    public void update(Room model) throws Exception;

    //删除单条记录
    @Delete("delete from Room where id=#{id}")
    public void delete(String id) throws Exception;

    //删除多条记录
    @Delete({
            "<script>",
            "delete from Room where id in",
            "<foreach collection='array' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    public void deleteByIds(String[] ids) throws Exception;
}
