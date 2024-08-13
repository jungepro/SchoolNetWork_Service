package cn.edu.hhstu.areaApplication.mapper;

import cn.edu.hhstu.entity.ApplicationSelfEntity;
import cn.edu.hhstu.entity.Excel.ApplicationExcel;
import cn.edu.hhstu.entity.Excel.ApplicationSelfExcel;
import cn.edu.hhstu.pojo.ApplicationSelf;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


@Mapper
@Repository
public interface  IApplicationSelfMapper {

    public List<ApplicationSelfEntity> list(HashMap params) throws Exception;
    public List<ApplicationSelfExcel> listExcel(HashMap params) throws Exception;

    public ApplicationSelfEntity detail(String id) throws Exception;

    @Insert("insert into application_self (id,appName,appKind,serverSite,installTime,departmentId,company,contact,linkMan,phone,url,status,remark,creator,createTime) " +
            "values (#{id},#{appName},#{appKind},#{serverSite},#{installTime},#{departmentId},#{company},#{contact},#{linkMan},#{phone},#{url},#{status},#{remark},#{creator},#{createTime})")
    public int insert(ApplicationSelf entity) throws Exception;

    @Update("update application_self set appName=#{appName},appKind=#{appKind},serverSite=#{serverSite},installTime=#{installTime},departmentId=#{departmentId}" +
            ",company=#{company},contact=#{contact},linkMan=#{linkMan},phone=#{phone},url=#{url},status=#{status},remark=#{remark} where id=#{id}")
    public int update(ApplicationSelf entity) throws Exception;

    @Delete("delete from application_self where id=#{id}")
    public int delete(String id) throws Exception;
}
