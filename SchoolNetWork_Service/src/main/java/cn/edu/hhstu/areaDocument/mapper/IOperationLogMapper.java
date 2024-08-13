package cn.edu.hhstu.areaDocument.mapper;

import cn.edu.hhstu.pojo.OperationLog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface  IOperationLogMapper {

    public List<OperationLog> list(String operator,String deviceName,Integer operationLogTypeId) throws Exception;

    public List<OperationLog> listByDevice(String deviceId) throws Exception;

    public OperationLog detail(String id) throws Exception;

    @Insert("insert into operation_log (id,operator,deviceTypeId,deviceName,deviceId,ip,operationLogTypeId,contents,operationDate,creator,createTime) " +
            "values (#{id},#{operator},#{deviceTypeId},#{deviceName},#{deviceId},#{ip},#{operationLogTypeId},#{contents},#{operationDate},#{creator},#{createTime})")
    public int insert(OperationLog entity) throws Exception;

    @Update("update operation_log set operator=#{operator},deviceName=#{deviceName},ip=#{ip},operationLogTypeId=#{operationLogTypeId},contents=#{contents},operationDate=#{operationDate} where id=#{id}")
    public int update(OperationLog entity) throws Exception;

    @Delete("delete from operation_log where id=#{id}")
    public int delete(String id) throws Exception;
}
