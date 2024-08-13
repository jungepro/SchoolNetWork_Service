package cn.edu.hhstu.areaApplication.mapper;

import cn.edu.hhstu.entity.DeviceEntity;
import cn.edu.hhstu.entity.Excel.ApplicationExcel;
import cn.edu.hhstu.pojo.Application;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface  IApplicationMapper {
    //查询列表
    public List<Application> list(HashMap params) throws Exception;
    public List<ApplicationExcel> listExcel(HashMap params) throws Exception;

    //详细信息
    public Application detail(String id) throws Exception;

    //新增保存
    @Insert("insert into application (id,pid,deviceId,applicationTypeId,appName,version,installTime,company,contact,departmentId," +
            "linkMan,phone,email,port,record,level,remark,creator,createTime) " +
            "values (#{id},#{pid},#{deviceId},#{applicationTypeId},#{appName},#{version},#{installTime},#{company},#{contact},#{departmentId}," +
            "#{linkMan},#{phone},#{email},#{port},#{record},#{level},#{remark},#{creator},#{createTime})")
    public int insert(Application entity) throws Exception;

    //更新保存
    @Update("update application set applicationTypeId=#{applicationTypeId},appName=#{appName},version=#{version},installTime=#{installTime}," +
            "company=#{company},contact=#{contact},departmentId=#{departmentId},linkMan=#{linkMan},phone=#{phone},email=#{email}," +
            "port=#{port},record=#{record},level=#{level},remark=#{remark}" +
            " where id=#{id}")
    public int update(Application entity) throws Exception;

    //清除
    @Delete("delete from application where id=#{id} or pid=#{id}")
    public int delete(String id) throws Exception;

    //下架
    @Update("update application set status=0,deviceId=null where id=#{id} or pid=#{id}")
    public int offline(String id) throws Exception;
    //上架
    @Update("update application set status=1 where id=#{id}")
    public int online(String id) throws Exception;

    /**************************************/
    //所在设备
    public DeviceEntity deviceDetail(String id) throws Exception;
    //设备列表
    public List<DeviceEntity> deviceList(String serverName,Integer deviceTypeId) throws Exception;
    //更新所在设备
    @Update("update application set deviceId=#{deviceId} where id=#{id}")
    public int alterDevice(String id, String deviceId) throws Exception;
}
