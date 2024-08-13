package cn.edu.hhstu.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

@ApiModel(value = "JsonMsg",description = "json响应数据实体类")
public class  JsonMsg {
    //状态码
    @ApiModelProperty("响应代码")
    private int code;
    //提示信息
    @ApiModelProperty("响应消息")
    private String msg;
    //返回的用户信息
    @ApiModelProperty("响应内容")
    private Map<String,Object> extend = new HashMap<String,Object>();

    public static cn.edu.hhstu.utils.JsonMsg success() {
        cn.edu.hhstu.utils.JsonMsg result = new cn.edu.hhstu.utils.JsonMsg();
        result.setCode(200);
        result.setMsg("ok");
        return result;
    }
    public static cn.edu.hhstu.utils.JsonMsg fail() {
        cn.edu.hhstu.utils.JsonMsg result = new cn.edu.hhstu.utils.JsonMsg();
        result.setCode(400);
        result.setMsg("fail");
        return result;
    }

    public static cn.edu.hhstu.utils.JsonMsg resonpse(int code, String msg) {
        cn.edu.hhstu.utils.JsonMsg result = new cn.edu.hhstu.utils.JsonMsg();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public cn.edu.hhstu.utils.JsonMsg add(String key, Object value) {
        this.getExtend().put(key, value);
        return this;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Map<String, Object> getExtend() {
        return extend;
    }
    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
