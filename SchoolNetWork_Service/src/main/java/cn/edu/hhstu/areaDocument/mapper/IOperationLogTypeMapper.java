package cn.edu.hhstu.areaDocument.mapper;

import cn.edu.hhstu.pojo.OperationLogType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface  IOperationLogTypeMapper {

    @Select("select * from operation_log_type")
    public List<OperationLogType> list() throws Exception;
}
