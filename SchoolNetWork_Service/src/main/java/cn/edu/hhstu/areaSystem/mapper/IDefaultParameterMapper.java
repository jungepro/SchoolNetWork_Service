package cn.edu.hhstu.areaSystem.mapper;

import cn.edu.hhstu.pojo.DefaultParameter;
import cn.edu.hhstu.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Mapper
@Repository
public interface  IDefaultParameterMapper {
//    @Select({
//            "<script>",
//            "select * from default_parameter",
//            "<where>",
//            "<if test='deviceTypeId!=null'>deviceTypeId=#{deviceTypeId}</if>",
//            "</where>",
//            "</script>"
//    })
    @Select("select * from default_parameter where deviceTypeId=#{deviceTypeId} and paramName not in (select paramName from device_parameter where deviceId=#{deviceId})")
    public List<DefaultParameter> unuseList(Integer deviceTypeId,String deviceId) throws Exception;
    @Select("select * from default_parameter")
    public List<DefaultParameter> list() throws Exception;
}
