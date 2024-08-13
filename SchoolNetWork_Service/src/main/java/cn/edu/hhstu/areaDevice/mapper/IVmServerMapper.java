package cn.edu.hhstu.areaDevice.mapper;

import cn.edu.hhstu.entity.Excel.VmServerExcel;
import cn.edu.hhstu.pojo.VmServer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface  IVmServerMapper {
    //查询列表
    public List<VmServer> list(HashMap params) throws Exception;
    //查询导出
    public List<VmServerExcel> listExcel(HashMap params) throws Exception;

    //详细信息
    public VmServer detail(String id) throws Exception;

    //新增保存
    @Insert("insert into vm_server (id,deviceId,serverNo,serverName,osId,osVersion,departmentId,linkMan,phone,level," +
            "status,inTime,remark,creator,createTime) " +
            "values (#{id},#{deviceId},#{serverNo},#{serverName},#{osId},#{osVersion},#{departmentId},#{linkMan},#{phone},#{level}," +
            "#{status},#{inTime},#{remark},#{creator},#{createTime})")
    public int insert(VmServer entity) throws Exception;

    //更新保存
    @Update("update vm_server set deviceId=#{deviceId},serverName=#{serverName},osId=#{osId},osVersion=#{osVersion},departmentId=#{departmentId}," +
            "linkMan=#{linkMan},phone=#{phone},level=#{level},inTime=#{inTime},remark=#{remark}" +
            " where id=#{id}")
    public int update(VmServer entity) throws Exception;

    //获取最后一个编码
    @Select("select * from vm_server where serverNO like concat('_',cast(DATE_FORMAT(NOW(), '%Y') as char),'%') ORDER BY serverNo DESC limit 1")
    public VmServer lastDeviceNo() throws Exception;

    //虚机上是否部署有应用
    @Select("select count(0) from application where deviceId=#{deviceId}")
    public int haveApplication(String deviceId) throws Exception;
    //下架虚机
    //@Update("delete from device_ip where id=#{id}; update vm_server set status=0 where id=#{id};")
    @Select("call p_vmserver_offline(#{id,mode=IN,jdbcType=VARCHAR})")
    public int offline(String id) throws Exception;
    //删除虚机
//    @Delete("delete from device_ip where deviceId=#{id}; " +
//            "delete from device_parameter where deviceId=#{id}; " +
//            "delete from vm_server where id=#{id};")
    @Select("call p_vmserver_delete(#{id,mode=IN,jdbcType=VARCHAR})")
    public int delete(String id) throws Exception;
}
