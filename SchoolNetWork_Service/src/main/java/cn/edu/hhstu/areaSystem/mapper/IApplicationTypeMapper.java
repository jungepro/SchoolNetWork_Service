package cn.edu.hhstu.areaSystem.mapper;

import cn.edu.hhstu.pojo.ApplicationType;
import cn.edu.hhstu.pojo.Department;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface  IApplicationTypeMapper {
    //列表查询
    @Select("select * from application_type")
    public List<ApplicationType> list() throws Exception;

    //详细信息
    @Select("select * from application_type where id=#{id}")
    public ApplicationType detail(int id) throws Exception;

    //新增保存
    @Insert("insert into application_type (applicationTypeName) values (#{applicationTypeName})")
    public int insert(ApplicationType model) throws Exception;

    //更新保存
    @Update("update application_type set applicationTypeName=#{applicationTypeName} where id=#{id}")
    public int update(ApplicationType model) throws Exception;

    //删除单条记录
    @Delete("delete from application_type where id=#{id}")
    public int delete(int id) throws Exception;

    //删除多条记录
    @Delete({
            "<script>",
            "delete from application_type where id in",
            "<foreach collection='array' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    public int deleteByIds(int[] ids) throws Exception;
}
