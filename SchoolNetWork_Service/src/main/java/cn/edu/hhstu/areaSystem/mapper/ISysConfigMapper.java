package cn.edu.hhstu.areaSystem.mapper;

import cn.edu.hhstu.entity.AppAuditConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface  ISysConfigMapper {

    @Select("select AppAuditYear,AppAuditStart,AppAuditEnd,AppAuditOpen from sys_config where id=1")
    public AppAuditConfigEntity getAppAuditConfig() throws Exception;

    @Update("update sys_config set AppAuditYear=#{appAuditYear},AppAuditStart=#{appAuditStart},AppAuditEnd=#{appAuditEnd},AppAuditOpen=#{appAuditOpen} where id=1")
    public int setAppAuditConfig(AppAuditConfigEntity entity) throws Exception;

}
