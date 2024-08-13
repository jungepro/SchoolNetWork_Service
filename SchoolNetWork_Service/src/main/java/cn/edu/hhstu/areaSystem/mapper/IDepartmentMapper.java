package cn.edu.hhstu.areaSystem.mapper;

import cn.edu.hhstu.pojo.Department;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface  IDepartmentMapper {
    //列表查询
    @Select({
        "<script>",
        "select * from department",
        "<where>",
        "<if test='departmentName!=null and departmentName!=\"\"'>and departmentName LIKE concat(\"%\",#{departmentName},\"%\")</if>",
        "</where>",
        "</script>"
    })
    public List<Department> list(@Param(value = "departmentName") String departmentName) throws Exception;
    public List<Department> list() throws Exception;

    //详细信息
    @Select("select * from department where id=#{id}")
    public Department detail(String id) throws Exception;

    //新增保存
    @Insert("insert into department (id,departmentName,remark) values (#{id},#{departmentName},#{remark})")
    public void insert(Department model) throws Exception;

    //更新保存
    @Update("update department set departmentName=#{departmentName},remark=#{remark} where id=#{id}")
    public void update(Department model) throws Exception;

    //删除单条记录
    @Delete("delete from department where id=#{id}")
    public void delete(String id) throws Exception;

    //删除多条记录
    @Delete({
            "<script>",
            "delete from department where id in",
            "<foreach collection='array' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    public void deleteByIds(String[] ids) throws Exception;
}
