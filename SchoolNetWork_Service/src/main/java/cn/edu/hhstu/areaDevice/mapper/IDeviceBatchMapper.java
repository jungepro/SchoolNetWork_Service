package cn.edu.hhstu.areaDevice.mapper;

import cn.edu.hhstu.entity.Excel.DeviceBatchExcel;
import cn.edu.hhstu.pojo.DeviceBatch;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface  IDeviceBatchMapper {
    //查询列表
    public List<DeviceBatch> list(DeviceBatch entity) throws Exception;
    public List<DeviceBatchExcel> listExcel(DeviceBatch entity) throws Exception;

    //详细信息
//    @Select("select * from device_batch where id=#{id}")
    public DeviceBatch detail(String id) throws Exception;

    //新增保存
    @Insert("insert into device_batch (id,batchNo,deviceTypeId,manufacturerId,model,amount,inTime,inMan,comeFrom,remark,creator,createTime) " +
            "values (#{id},#{batchNo},#{deviceTypeId},#{manufacturerId},#{model},#{amount},#{inTime},#{inMan},#{comeFrom},#{remark},#{creator},#{createTime})")
    public int insert(DeviceBatch entity) throws Exception;

    //更新保存
    @Update("update device_batch set deviceTypeId=#{deviceTypeId},manufacturerId=#{manufacturerId}," +
            "model=#{model},amount=#{amount},inTime=#{inTime},inMan=#{inMan},comeFrom=#{comeFrom},remark=#{remark} where id=#{id}")
    public int update(DeviceBatch entity) throws Exception;

    //删除单条记录
    @Delete("delete from device_batch where id=#{id}")
    public int delete(String id) throws Exception;

    //获取最后一个批次编码
    @Select("select * from device_batch where batchNo like CONCAT(#{batchNo},YEAR(NOW()),'%') ORDER BY batchNo DESC limit 1")
    public DeviceBatch lastBatchNo(String batchNo) throws Exception;
}
