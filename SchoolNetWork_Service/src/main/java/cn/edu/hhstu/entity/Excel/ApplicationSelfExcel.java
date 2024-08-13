package cn.edu.hhstu.entity.Excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;


@Data
public class ApplicationSelfExcel {

    @ExcelProperty("应用名称")
    private String appName;

    @ExcelProperty("服务器类型或地点")
    private String serverSite;

    @ExcelProperty("上线日期")
    @DateTimeFormat("yyyy-MM-dd")
    private java.util.Date installTime;

    @ExcelProperty("厂商名称")
    private String company;

    @ExcelProperty("厂商电话")
    private String contact;

    @ExcelProperty("联系人")
    private String linkMan;

    @ExcelProperty("电话")
    private String phone;

    @ExcelProperty("链接地址")
    private String url;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("应用类别")
    private String appKindName;

    @ExcelProperty("部门")
    private String departmentName;

    public String getAppName() {
        return appName;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public String getAppKindName() {
        return appKindName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    @ExcelIgnore
    private long status;
    @ExcelProperty("状态")
    private String statusName;
    public String getStatusName(){
        String result;
        if(this.status==1)
            result = "正常";
        else if(this.status==0)
            result = "下线";
        else
            result = "未知";
        return result;
    }
}
