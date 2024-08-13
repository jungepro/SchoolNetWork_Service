package cn.edu.hhstu.areaIp.mapper;

import cn.edu.hhstu.pojo.DeviceIp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface  IDeviceIpMapper {

    public List<DeviceIp> list(String ip, Integer deviceTypeId,Integer openType) throws Exception;

    //增加保存设备ip
    @Insert("insert device_ip (ipAddressId,deviceId,deviceTypeId,creator,createTime) values (#{ipAddressId},#{deviceId},#{deviceTypeId},#{creator},#{createTime})")
    public int insertDeviceIp(DeviceIp entity) throws Exception;
    //删除单条设备ip
    @Delete("delete from device_ip where ipAddressId=#{ipAddressId}")
    public int deleteDeviceIp(int ipAddressId);
    //删除多条设备ip
    public int deleteDeviceIpByIds(int[] ids) throws Exception;
}
