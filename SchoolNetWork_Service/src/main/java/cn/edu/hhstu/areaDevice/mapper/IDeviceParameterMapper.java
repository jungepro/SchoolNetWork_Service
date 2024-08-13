package cn.edu.hhstu.areaDevice.mapper;

import cn.edu.hhstu.pojo.DeviceParameter;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Mapper
@Repository
public interface  IDeviceParameterMapper {
    @Select({
            "<script>",
            "select * from device_parameter",
            "<where>",
            "<if test='deviceId!=null and deviceId!=\"\"'>deviceId=#{deviceId}</if>",
            "</where>",
            "order by orderNo",
            "</script>"
    })
    public List<DeviceParameter> list(String deviceId) throws Exception;

    @Select("select * from device_parameter where id=#{id}")
    public DeviceParameter detail(int id) throws Exception;

    @Insert("insert into device_parameter (deviceTypeId,deviceId,paramName,paramValue,unitName,orderNo,creator,createTime)"
            +" values (#{deviceTypeId},#{deviceId},#{paramName},#{paramValue},#{unitName},#{orderNo},#{creator},#{createTime})")
    public int insert(DeviceParameter entity) throws Exception;

    @Update("update device_parameter set paramValue=#{paramValue} where id=#{id}")
    public int update(DeviceParameter entity) throws Exception;

    @Delete("delete from device_parameter where id=#{id}")
    public int delete(int id) throws Exception;
}
