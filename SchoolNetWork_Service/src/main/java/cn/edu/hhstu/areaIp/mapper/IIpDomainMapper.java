package cn.edu.hhstu.areaIp.mapper;

import cn.edu.hhstu.pojo.IpDomain;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

//ip地址范畴mapper
@Mapper
@Repository
public interface  IIpDomainMapper {
    @Select("select * from ip_domain")
    public List<IpDomain> list() throws Exception;

    //详细信息
    @Select("select * from ip_domain where id=#{id}")
    public IpDomain detail(int id) throws Exception;

    //新增保存
    @Insert("insert into ip_domain (id,domainName,remark,creator,createTime) values (#{id},#{domainName},#{remark},#{creator},#{createTime})")
    public void insert(IpDomain entity) throws Exception;

    //更新保存
    @Update("update ip_domain set domainName=#{domainName},remark=#{remark} where id=#{id}")
    public void update(IpDomain entity) throws Exception;

    //删除单条记录
    @Delete("delete from ip_domain where id=#{id}")
    public void delete(int id) throws Exception;
}
