package cn.edu.hhstu.areaSystem.mapper;

import cn.edu.hhstu.pojo.DeviceType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface  IDeviceTypeMapper {
    //列表查询
    @Select({
            "<script>",
            "select * from device_type",
            "<where>",
            "<if test='kind!=null'>id &lt; #{kind}</if>",
            "</where>",
            "</script>"
    })
    public List<DeviceType> list(Integer kind) throws Exception;
//    @Select("select * from device_type")
    public List<DeviceType> list() throws Exception;

    //详细信息
    @Select("select * from device_type where id=#{id}")
    public DeviceType detail(int id) throws Exception;
}
