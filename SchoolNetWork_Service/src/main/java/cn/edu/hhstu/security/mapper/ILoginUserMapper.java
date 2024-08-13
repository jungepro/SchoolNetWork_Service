package cn.edu.hhstu.security.mapper;

import cn.edu.hhstu.security.entity.UserDetailsEntity;
import cn.edu.hhstu.security.pojo.LoginUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface ILoginUserMapper {
//    @Select("select * from login_user")
    public List<LoginUser> list(HashMap params) throws Exception;

    //详细信息
//    @Select("select * from login_user where userid=#{id}")
    public LoginUser detail(String id) throws Exception;

    //新增保存
    @Insert("insert into login_user (userId,account,pwd,realName,phone,email,departmentId,disabled,ssoLogin) " +
            "values (#{userId},#{account},#{pwd},#{realName},#{phone},#{email},#{departmentId},#{disabled},#{ssoLogin})")
    public int insert(LoginUser entity) throws Exception;

    //更新保存
    @Update("update login_user set realName=#{realName},phone=#{phone},email=#{email},departmentId=#{departmentId},disabled=#{disabled},ssoLogin=#{ssoLogin} where userId=#{userId}")
    public int update(LoginUser entity) throws Exception;

    //删除单条记录
    @Delete("delete from login_user where userid=#{id}")
    public int delete(String id) throws Exception;

    //修改用户密码
    @Update("update login_user set pwd=#{pwd} where userId=#{id}")
    public int savePwd(String id,String pwd) throws Exception;

    //新增角色
    @Insert("insert into role_user (userId,roleId) values (#{userId},#{roleId})")
    public int insertRole(String userId,Integer roleId) throws Exception;

    //删除角色
    @Delete("delete from role_user where userId=#{userId} and roleId=#{roleId}")
    public int deleteRole(String userId,Integer roleId) throws Exception;

    /***********************************/

    //通过账号获取用户
    public UserDetailsEntity getUserByAccount(String account) throws Exception;

    //登录成功，更新用户数据
    @Update("update login_user set lastLoginIp=#{lastLoginIp},lastLoginTime=#{lastLoginTime},loginTimes=loginTimes+1 where account=#{account}")
    public void loginSuccess(LoginUser entity);
}
