package cn.edu.hhstu.areaHome.controller;

import cn.edu.hhstu.security.entity.UserDetailsEntity;
import cn.edu.hhstu.utils.CaptchaUtil;
import cn.edu.hhstu.utils.JsonMsg;
import cn.edu.hhstu.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONML;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Api(tags = "登陆控制器")
@Controller
public class  SignController {


    @ApiOperation("验证码图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name="request",value="请求",required=false),
            @ApiImplicitParam(name="response",value="响应",required=false),
            @ApiImplicitParam(name="session",value="session",required=false)
    })
    @GetMapping("/code")
    public void checkCode(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        int width = NumberUtils.toInt(request.getParameter("width"), 80);
        int height = NumberUtils.toInt(request.getParameter("height"), 30);
        int codeCount = NumberUtils.toInt(request.getParameter("codeCount"), 4);
        int lineCount = NumberUtils.toInt(request.getParameter("lineCount"), 10);
        if (width > 800) {
            width = 80;
        }
        if (height > 300) {
            height = 30;
        }
        if (codeCount > 10) {
            codeCount = 4;
        }
        if (lineCount > 100) {
            lineCount = 10;
        }
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 自定义参数
        CaptchaUtil code = new CaptchaUtil(width, height, codeCount, lineCount);
        System.out.println("code-------------- "+code.getCode());
        session.setAttribute("checkcode",code.getCode());
        code.write(response.getOutputStream());
    }

    @ApiOperation("登陆视图")
    @GetMapping("/login")
    public String Login() {

        return "login";
    }

    @GetMapping("/sign")
    public void Sign(){

    }
}
