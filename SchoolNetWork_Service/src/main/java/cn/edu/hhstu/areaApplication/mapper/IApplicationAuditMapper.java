package cn.edu.hhstu.areaApplication.mapper;

import cn.edu.hhstu.entity.Excel.ApplicationAuditExcel;
import cn.edu.hhstu.pojo.ApplicationAudit;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface  IApplicationAuditMapper {
    //查询列表
    public List<ApplicationAudit> list(HashMap params) throws Exception;
    public List<ApplicationAuditExcel> listExcel(HashMap params) throws Exception;

    @Select("select * from application_audit where id=#{id}")
    public ApplicationAudit detail(String id) throws Exception;

    @Update("update application_audit set appName=#{appName},installTime=#{installTime},status=#{status},departmentId=#{departmentId}," +
            "departmentName=#{departmentName},url=#{url},remark=#{remark} where id=#{id}")
    public int update(ApplicationAudit entity) throws Exception;

    @Delete("delete from application_audit where id=#{id}")
    public int delete(String id) throws Exception;

    public int born(HashMap params) throws Exception;

    @Update("update application_audit set status=#{status},remark=#{remark},auditor=#{auditor},auditTime=#{auditTime} where id=#{id}")
    public int updateAudit(ApplicationAudit entity) throws Exception;
}
