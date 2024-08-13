package cn.edu.hhstu.areaIp.mapper;

import cn.edu.hhstu.pojo.DeviceIp;
import cn.edu.hhstu.pojo.IpAddress;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface  IIpAddressMapper {
    //数据类表
    public List<IpAddress> list(String ip,Integer domainId,Integer openType) throws Exception;

    //设备配置ip
    public List<IpAddress> listDeviceIp(String deviceId) throws Exception;
    public List<IpAddress> deviceIps(Integer domainId,String ip,Integer openType) throws Exception;

    //未使用列表
    public List<IpAddress> listUnUse(String ip, int top) throws Exception;

    //详细信息
    @Select("select * from ip_address where id=#{id}")
    public IpAddress detail(int id) throws Exception;

    //是否存在
    @Select("select count(0) from ip_address where ip=#{ip}")
    public int existIp(String ip) throws Exception;

    //新增保存
    @Insert("insert into ip_address (id,ip,poolId,remark,creator,createTime) values (#{id},#{ip},#{poolId},#{remark},#{creator},#{createTime})")
    public int insert(IpAddress entity) throws Exception;

    //更新保存
    @Update("update ip_address set openType=#{openType},remark=#{remark} where id=#{id}")
    public int update(IpAddress entity) throws Exception;

    //更新备注，未使用
//    @Update("update ip_address set remark=#{msg} where id=#{id}")
//    public void remark(int id,String msg) throws Exception;

    //删除单条记录
    @Delete("delete from ip_address where id=#{id}")
    public void delete(int id) throws Exception;

    //删除多条记录
    public void deleteByIds(int[] ids) throws Exception;

    //增加保存设备ip
    @Insert("insert device_ip (ipAddressId,deviceId,deviceTypeId,creator,createTime) values (#{ipAddressId},#{deviceId},#{deviceTypeId},#{creator},#{createTime})")
    public int insertDeviceIp(DeviceIp entity) throws Exception;
    //删除单条设备ip
    @Delete("delete from device_ip where ipAddressId=#{ipAddressId}")
    public int deleteDeviceIp(int ipAddressId);
    //删除多条设备ip
    public int deleteDeviceIpByIds(int[] ids) throws Exception;

}
