package cn.edu.hhstu.areaIp.mapper;

import cn.edu.hhstu.pojo.IpIsp;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;


@Mapper
@Repository
public interface  IIpISPMapper {

    @Select("select * from ip_isp")
    public List<IpIsp> list() throws Exception;

    @Select("select * from ip_isp where id=#{id}")
    public IpIsp detail(int id) throws Exception;

    @Insert("insert into ip_isp (IspNo,IspName,contacts,phone,startTime,remark) " +
            "values (#{ispNo},#{ispName},#{contacts},#{phone},#{startTime},#{remark})")
    public int insert(IpIsp entity) throws Exception;

    @Update("update ip_isp set ispNo=#{ispNo},ispName=#{ispName},contacts=#{contacts}" +
            ",phone=#{phone},startTime=#{startTime},remark=#{remark} where id=#{id}")
    public int update(IpIsp entity) throws Exception;

    @Delete("delete from ip_isp where id=#{id}")
    public int delete(int id) throws Exception;
}
