package cn.edu.hhstu.entity.Excel;

import cn.edu.hhstu.pojo.Department;
import cn.edu.hhstu.pojo.Device;
import cn.edu.hhstu.pojo.DeviceIp;
import cn.edu.hhstu.pojo.Os;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.util.List;


@Data
public class VmServerExcel {
    @ExcelProperty("编码")
    private String serverNo;

    @ExcelProperty("虚机名称")
    private String serverName;

    @ExcelProperty("所在物理机")
    private String deviceName;

    @ExcelIgnore
    private String osName;
    @ExcelIgnore
    private String osVersion;
    @ExcelProperty("操作系统")
    private String osAll;

    @ExcelProperty("IP地址")
    private String ip;

    @ExcelProperty("责任部门")
    private String departmentName;

    @ExcelProperty("联系人")
    private String linkMan;

    @ExcelProperty("联系电话")
    private String phone;

    @ExcelProperty("重要定级")
    private long level;

    @ExcelProperty("创建日期")
    @DateTimeFormat("yyyy-MM-dd")
    private java.util.Date inTime;

    @ExcelProperty("备注")
    private String remark;

    @ExcelIgnore
    private List<DeviceIp> deviceIpList;

    public String getOsAll() {
        return (osName==null?"":osName) + " " + (osVersion==null?"":osVersion);
    }

    public void setOsAll(String osAll) {
        this.osAll = osAll;
    }

    public String getIp() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < deviceIpList.size(); i++) {
            sb.append(deviceIpList.get(i).getIpAddress().getIp());
            if (i < deviceIpList.size() - 1) {
                sb.append("\r\n");
            }
        }
        return sb.toString();
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
