package cn.edu.hhstu.entity.Excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;


@Data
public class DeviceBatchExcel {
    @ExcelProperty("批号")
    private String batchNo;
    @ExcelProperty("设备类型")
    private String deviceTypeName;
    @ExcelProperty("厂商")
    private String manufacturerName;
    @ExcelProperty("型号")
    private String model;
    @ExcelProperty("数量")
    private int amount;
    @ExcelProperty("入库数量")
    private int deviceCount;
    @ExcelProperty("入库日期")
    @DateTimeFormat("yyyy-MM-dd")
    private java.util.Date inTime;
    @ExcelProperty("接收人")
    private String inMan;
    @ExcelProperty("来源")
    private String comeFrom;
    @ExcelProperty("备注")
    private String remark;
}
