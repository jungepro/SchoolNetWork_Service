package cn.edu.hhstu.areaApplication.mapper;

import cn.edu.hhstu.pojo.ApplicationUrl;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface  IApplicationUrlMapper {
    //查询列表
    @Select("select * from application_url where applicationId=#{applicationId}")
    public List<ApplicationUrl> list(String applicationId) throws Exception;

    //详细信息
    @Select("select * from application_url where id=#{id}")
    public ApplicationUrl detail(int id) throws Exception;

    //新增保存
    @Insert("insert into application_url (applicationId,description,url,creator,createTime) " +
            "values (#{applicationId},#{description},#{url},#{creator},#{createTime})")
    public int insert(ApplicationUrl entity) throws Exception;

    //更新保存
    @Update("update application_url set applicationId=#{applicationId},description=#{description},url=#{url} where id=#{id}")
    public int update(ApplicationUrl entity) throws Exception;

    //删除单条记录
    @Delete("delete from application_url where id=#{id}")
    public int delete(int id) throws Exception;

}
