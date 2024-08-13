package cn.edu.hhstu.areaHome.mapper;

import cn.edu.hhstu.security.pojo.LoginUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface  IIndexMapper {

    //设备总量
    @Select("select count(0) from device d join device_batch db on d.DeviceBatchId=db.id where deviceTypeId=#{deviceTypeId} and status=1")
    public int serverCount(int deviceTypeId) throws Exception;
    //托管设备总量
    @Select("select count(0) from device where IsTrust and DepartmentId=#{departmentId}")
    public int serverTrustCount(String departmentId) throws Exception;

    //虚机总量
    @Select("select count(0) from vm_server where status=1")
    public int vmServerCount() throws Exception;

    //应用系统总量
    @Select("select count(0) from application where status=1")
    public int applicationCount() throws Exception;

    @Select("select count(0) from application where status=1 and departmentId=#{departmentId}")
    public int applicationDepartmentCount(String departmentId) throws Exception;

    //本年操作日志总量
    @Select("select count(0) from operation_log where YEAR(OperationDate)=YEAR(NOW())")
    public int operationLogCount() throws Exception;

    //获取用户信息
    @Select("select * from login_user where userId=#{id}")
    public LoginUser userDetail(String id) throws Exception;
    //用户修改密码保存
    @Update("update login_user set pwd=#{pwd} where userId=#{userId}")
    public int passwordSave(String userId,String pwd) throws Exception;
}
