package cn.edu.hhstu.areaIp.mapper;

import cn.edu.hhstu.pojo.IpPool;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface  IIpPoolMapper {

    public List<IpPool> list(IpPool entity) throws Exception;

    //详细信息
    @Select("select * from ip_pool where id=#{id}")
    public IpPool detail(int id) throws Exception;

    //新增保存
    @Insert("insert into ip_pool (id,domainId,ipSegment,mask,gateway,place,ipNumber,remark,creator,createTime) values (#{id},#{domainId},#{ipSegment},#{mask},#{gateway},#{place},#{ipNumber},#{remark},#{creator},#{createTime})")
    public void insert(IpPool entity) throws Exception;

    //更新保存
    @Update("update ip_pool set domainId=#{domainId},ipSegment=#{ipSegment},mask=#{mask},gateway=#{gateway},place=#{place},ipNumber=#{ipNumber},remark=#{remark} where id=#{id}")
    public void update(IpPool entity) throws Exception;

    //删除单条记录
    @Delete("delete from ip_pool where id=#{id}")
    public void delete(int id) throws Exception;

    //删除多条记录
    public void deleteByIds(int[] ids) throws Exception;
}
