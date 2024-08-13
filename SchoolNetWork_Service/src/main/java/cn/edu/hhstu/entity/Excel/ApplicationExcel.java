package cn.edu.hhstu.entity.Excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;


@Data
public class ApplicationExcel {
    @ExcelProperty("应用名称")
    private String appName;

    @ExcelProperty("分类")
    private String applicationTypeName;

    @ExcelProperty("版本")
    private String version;

    @ExcelProperty("上架时间")
    @DateTimeFormat("yyyy-MM-dd")
    private java.util.Date installTime;

    @ExcelProperty("公司")
    private String company;

    @ExcelProperty("厂商联系方式")
    private String contact;

    @ExcelProperty("使用部门")
    private String departmentName;

    @ExcelProperty("联系人")
    private String linkMan;
    @ExcelProperty("联系电话")
    private String phone;
    @ExcelProperty("电子邮件")
    private String email;

    @ExcelIgnore
    private boolean record;
    @ExcelProperty("是否备案")
    private String recordName;

    public String getRecordName() {
        if(record){
            return "是";
        }
        else{
            return "否";
        }
    }
}
