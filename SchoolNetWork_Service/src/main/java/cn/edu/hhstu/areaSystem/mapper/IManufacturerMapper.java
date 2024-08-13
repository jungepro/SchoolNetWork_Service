package cn.edu.hhstu.areaSystem.mapper;

import cn.edu.hhstu.pojo.Manufacturer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface  IManufacturerMapper {
    //列表查询
    @Select("select * from Manufacturer")
    public List<Manufacturer> list() throws Exception;

    //详细信息
    @Select("select * from Manufacturer where id=#{id}")
    public Manufacturer detail(int id) throws Exception;

    //新增保存
    @Insert("insert into Manufacturer (manufacturerName,briefName,phone,remark) values (#{manufacturerName},#{briefName},#{phone},#{remark})")
    public void insert(Manufacturer model) throws Exception;

    //更新保存
    @Update("update Manufacturer set manufacturerName=#{manufacturerName},briefName=#{briefName},phone=#{phone},remark=#{remark} where id=#{id}")
    public void update(Manufacturer model) throws Exception;

    //删除单条记录
    @Delete("delete from Manufacturer where id=#{id}")
    public void delete(int id) throws Exception;

    //删除多条记录
    @Delete({
            "<script>",
            "delete from Manufacturer where id in",
            "<foreach collection='array' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    public void deleteByIds(int[] ids) throws Exception;
}
