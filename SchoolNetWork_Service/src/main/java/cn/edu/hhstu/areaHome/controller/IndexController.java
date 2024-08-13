package cn.edu.hhstu.areaHome.controller;

import cn.edu.hhstu.areaHome.service.IIndexService;
import cn.edu.hhstu.security.entity.SysMenuEntity;
import cn.edu.hhstu.security.entity.UserDetailsEntity;
import cn.edu.hhstu.security.pojo.LoginRole;
import cn.edu.hhstu.security.pojo.LoginUser;
import cn.edu.hhstu.security.service.ILoginUserService;
import cn.edu.hhstu.utils.JsonMsg;
import cn.edu.hhstu.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.util.ServerInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

@Api(tags = "公共视图控制器")
@Controller
public class  IndexController {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Autowired
    private IIndexService indexService;
    @Autowired
    private ILoginUserService loginUserService;

    @ApiOperation("首页视图")
    @GetMapping("/index")
    public String Index(Model model) {
        //获取登录用户信息
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserDetailsEntity loginUser = (UserDetailsEntity) principal;
//        System.out.println(loginUser);
        return "index";
    }

    @ApiOperation("用户信息面板视图")
    @GetMapping("/dashboard")
    public String Dashboard(HttpServletRequest request,Model model) throws Exception {
        UserDetailsEntity loginUser = UserUtil.getLoginUser();
        LoginUser detail = loginUserService.detail(loginUser.getUserId());
        model.addAttribute("loginUser",detail);

        if(loginUser!=null){
//            List<LoginRole> roles = loginUser.getLoginRoles().stream().filter(f -> f.getRoleId().equals(3)).collect(Collectors.toList());
            //系统管理员
            if(loginUser.getUsername().toLowerCase().equals("system")
                    || loginUser.getLoginRoles().stream().filter(f -> f.getRoleId().equals(1)).collect(Collectors.toList()).size()>0
                    || loginUser.getLoginRoles().stream().filter(f -> f.getRoleId().equals(2)).collect(Collectors.toList()).size()>0){
                HashMap<String,Object> map = new HashMap<String,Object>();
                Properties props = System.getProperties();
                map.put("os.name",props.getProperty("os.name"));            //操作系统名称
                map.put("os.arch",props.getProperty("os.arch"));            //操作系统架构
                map.put("os.version",props.getProperty("os.version"));      //操作系统版本
                map.put("java.version",props.getProperty("java.version"));  //java版本
                map.put("java.vendor",props.getProperty("java.vendor"));    //java提供商
                map.put("server.built",ServerInfo.getServerBuilt());
                map.put("server.info",ServerInfo.getServerInfo());
                map.put("server.number",ServerInfo.getServerNumber());
                map.put("local.addr",request.getLocalAddr());               //服务器ip
                map.put("user.home",props.getProperty("user.home"));        //用户主目录
                map.put("user.dir",props.getProperty("user.dir"));          //用户当前工作目录
                map.put("driver.class.name",driverClassName);               //数据库驱动名称
                model.addAttribute("sysinfo",map);

                model.addAttribute("serverCount",indexService.serverCount(1));
                model.addAttribute("vmServerCount",indexService.vmServerCount());
                model.addAttribute("applicationCount",indexService.applicationCount());
                model.addAttribute("operationLogCount",indexService.operationLogCount());
                return "dashboard1";
            }
            //网络管理员
            else{
                model.addAttribute("applicationDepartCount",indexService.applicationCount(detail.getDepartmentId()));
                model.addAttribute("serverTrustCount",indexService.serverTrustCount(detail.getDepartmentId()));
                return "dashboard3";
            }
        }
        else{
            return "dashboard";
        }
    }

    @ApiOperation("用户修改密码视图")
    @GetMapping("/alterPassword")
    public String alterPassword(){
        return "alterPassword";
    }

    @ApiOperation("用户修改密码保存接口")
    @PostMapping("/passwordSave")
    @ResponseBody
    public JsonMsg passwordSave(String oldP,String newP,String confirmP){
        try {
            if (StringUtils.isBlank(oldP) || StringUtils.isBlank(newP)){
                return JsonMsg.resonpse(201,"密码不能为空");
            }
            else if(!newP.equals(confirmP)){
                return JsonMsg.resonpse(201,"两次输入的密码不相同");
            }

            LoginUser loginUser = indexService.userDetail(UserUtil.getLoginUser().getUserId());
            if(!loginUser.getPwd().equals(oldP)){
                return JsonMsg.resonpse(201,"原密码不正确");
            }
            else{
                indexService.passwordSave(loginUser.getUserId(),newP);
                return JsonMsg.success();
            }
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("获取用户权限菜单")
    @GetMapping("/getMenus")
    @ResponseBody
    public JsonMsg getMenus() {
        try {
            UserDetailsEntity loginUser = UserUtil.getLoginUser();
            if(loginUser != null) {
                List<SysMenuEntity> permissions = loginUser.getPermissions();

                final List<SysMenuEntity> menus = permissions.stream().filter(f -> f.getType() == 1 && !f.isHidden() && !f.isDisabled()).collect(Collectors.toList());
                List<SysMenuEntity> firstLevel = menus.stream().filter(f -> f.getParentId() == 0).collect(Collectors.toList());
                firstLevel.parallelStream().forEach(e->{
                    setChild(e,menus);
                });

                return JsonMsg.success().add("menus",firstLevel);
            }
            else {
                return JsonMsg.fail();
            }
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //设置菜单子元素
    private void setChild(SysMenuEntity menu, List<SysMenuEntity> menus) {
        List<SysMenuEntity> child = menus.parallelStream().filter((f -> f.getParentId() == menu.getId())).collect(Collectors.toList());
        menu.setChildren(child);
        if(!CollectionUtils.isEmpty(child)) {
            child.parallelStream().forEach(e->{
                setChild(e,menus);
            });
        }
    }
}
