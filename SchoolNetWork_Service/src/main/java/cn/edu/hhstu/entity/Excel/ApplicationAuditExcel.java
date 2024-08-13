package cn.edu.hhstu.entity.Excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;


@Data
public class ApplicationAuditExcel {
    @ExcelProperty("应用名称")
    private String appName;

    @ExcelProperty("上架时间")
    @DateTimeFormat("yyyy-MM-dd")
    private java.util.Date installTime;

    @ExcelProperty("使用部门")
    private String departmentName;

    @ExcelProperty("备案人")
    private String auditor;
    @ExcelProperty("备案时间")
    private java.util.Date auditTime;

    @ExcelIgnore
    private long status;
    @ExcelProperty("备案状态")
    private String statusDesc;

    @ExcelProperty("备注")
    private String remark;

    public String getAppName() {
        return appName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getAuditor() {
        return auditor;
    }

    public String getStatusDesc() {
        String tmp = "未知";
        if(status==0){
            tmp = "未备案";
        }
        else if(status == 1){
            tmp = "备案使用";
        }
        else{
            tmp = "备案下架";
        }
        return tmp;
    }
}
