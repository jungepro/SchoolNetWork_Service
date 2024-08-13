package cn.edu.hhstu.areaSystem.mapper;

import cn.edu.hhstu.pojo.LogSys;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface  ISysLogMapper {

    @Insert("insert into log_sys (visitTime,userName,ip,url,requestMethod,executionTime,method)" +
            " values (#{visitTime},#{userName},#{ip},#{url},#{requestMethod},#{executionTime},#{method})")
    public int insert(LogSys model) throws Exception;
}
