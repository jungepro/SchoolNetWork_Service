package cn.edu.hhstu.areaStatistics.mapper;

import cn.edu.hhstu.pojo.DeviceLog;import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface IDeviceLogMapper {

    public List<DeviceLog> list(HashMap params) throws Exception;

    @Select("select * from device_log where deviceId=#{deviceId} order by createTime")
    public List<DeviceLog> list(String deviceId) throws Exception;

    @Select("select * from device_log where id=#{id}")
    public DeviceLog detail(int id) throws Exception;

    @Insert("insert into device_log (logType,logTypeDesc,deviceId,deviceName,outMan,outTime,outPhone,outCreator,remark,creator,createTime) " +
            "values (#{logType},#{logTypeDesc},#{deviceId},#{deviceName},#{outMan},#{outTime},#{outPhone},#{outCreator},#{remark},#{creator},#{createTime})")
    public int insert(DeviceLog entity) throws Exception;

    @Update("update device_log set outMan=#{outMan},outTime=#{outTime},outPhone=#{outPhone},outCreator=#{outCreator},remark=#{remark} where id=#{id}")
    public int update(DeviceLog entity) throws Exception;

    @Delete("delete from device_log where id=#{id}")
    public int delete(int id) throws Exception;

}
