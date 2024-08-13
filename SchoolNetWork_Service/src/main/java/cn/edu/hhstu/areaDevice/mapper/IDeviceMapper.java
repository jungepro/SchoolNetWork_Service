package cn.edu.hhstu.areaDevice.mapper;

import cn.edu.hhstu.entity.Excel.DeviceExcel;
import cn.edu.hhstu.pojo.Device;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface  IDeviceMapper {
    //查询列表
    public List<Device> list(HashMap params) throws Exception;
    //查询导出
    public List<DeviceExcel> listExcel(HashMap params) throws Exception;

    //详细信息
    public Device detail(String id) throws Exception;

    //新增保存
    @Insert("insert into device (id,deviceBatchId,serverNo,serverName,seriesNumber,isTrust,departmentId,linkMan,phone,level," +
            "status,roomId,position,remark,creator,createTime,isVm,osId,osVersion,availableStorage,switchType) " +
            "values (#{id},#{deviceBatchId},#{serverNo},#{serverName},#{seriesNumber},#{isTrust},#{departmentId},#{linkMan},#{phone},#{level}," +
            "#{status},#{roomId},#{position},#{remark},#{creator},#{createTime},#{isVm},#{osId},#{osVersion},#{availableStorage},#{switchType})")
    public int insert(Device entity) throws Exception;

    //更新保存
    @Update("update device set serverName=#{serverName},seriesNumber=#{seriesNumber},isTrust=#{isTrust},departmentId=#{departmentId}," +
            "linkMan=#{linkMan},phone=#{phone},level=#{level},status=#{status},roomId=#{roomId},position=#{position},remark=#{remark}," +
            "isVm=#{isVm},osId=#{osId},osVersion=#{osVersion},availableStorage=#{availableStorage},switchType=#{switchType} where id=#{id}")
    public int update(Device entity) throws Exception;

    //获取最后一个编码
    @Select("select * from device where deviceBatchId=#{deviceBatchId} ORDER BY serverNo DESC limit 1")
    public Device lastDeviceNo(String deviceBatchId) throws Exception;
    //删除单条记录
//    @Delete("delete from device where id=#{id}")
//    public int delete(String id) throws Exception;
//
//    //获取最后一个编码
//    @Select("select * from device where serverNo like CONCAT(#{serverNo},YEAR(NOW()),'%') ORDER BY serverNo DESC limit 1")
//    public DeviceEntity lastServerNo(String number) throws Exception;
}
