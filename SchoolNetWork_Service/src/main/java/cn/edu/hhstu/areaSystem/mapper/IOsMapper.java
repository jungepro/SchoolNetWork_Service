package cn.edu.hhstu.areaSystem.mapper;

import cn.edu.hhstu.pojo.Os;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface  IOsMapper {
    //列表查询
    @Select("select * from OS")
    public List<Os> list() throws Exception;

    //详细信息
    @Select("select * from OS where id=#{id}")
    public Os detail(int id) throws Exception;

    //新增保存
    @Insert("insert into OS (id,OsName) values (#{id},#{osName})")
    public void insert(Os model) throws Exception;

    //更新保存
    @Update("update OS set OsName=#{osName} where id=#{id}")
    public void update(Os model) throws Exception;

    //删除单条记录
    @Delete("delete from OS where id=#{id}")
    public void delete(int id) throws Exception;

    //删除多条记录
    @Delete({
            "<script>",
            "delete from OS where id in",
            "<foreach collection='array' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    public void deleteByIds(int[] ids) throws Exception;
}
