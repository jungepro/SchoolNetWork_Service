package cn.edu.hhstu.areaSystem.mapper;

import cn.edu.hhstu.pojo.ApplicationKind;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface  IApplicationKindMapper {
    //列表查询
    @Select("select * from application_kind")
    public List<ApplicationKind> list() throws Exception;

    //详细信息
    @Select("select * from application_kind where id=#{id}")
    public ApplicationKind detail(int id) throws Exception;

    //新增保存
    @Insert("insert into application_kind (id,dictName) values (#{id},#{dictName})")
    public int insert(ApplicationKind model) throws Exception;

    //更新保存
    @Update("update application_kind set dictName=#{dictName} where id=#{id}")
    public int update(ApplicationKind model) throws Exception;

    //删除单条记录
    @Delete("delete from application_kind where id=#{id}")
    public int delete(int id) throws Exception;

    //删除多条记录
    @Delete({
            "<script>",
            "delete from application_kind where id in",
            "<foreach collection='array' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    public int deleteByIds(int[] ids) throws Exception;
}
