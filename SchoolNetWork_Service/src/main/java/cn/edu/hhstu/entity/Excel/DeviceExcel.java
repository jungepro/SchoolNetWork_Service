package cn.edu.hhstu.entity.Excel;

import cn.edu.hhstu.pojo.*;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DeviceExcel {
    @ExcelProperty("编码")
    private String serverNo;

    @ExcelProperty("设备名称")
    private String serverName;

    @ExcelProperty("序列号")
    private String seriesNumber;

    @ExcelIgnore
    private String osName;
    @ExcelIgnore
    private String osVersion;
    @ExcelProperty("操作系统")
    private String osAll;

    @ExcelProperty("IP")
    private String ip;

    @ExcelProperty("厂商")
    private String manufacturerName;

    @ExcelProperty("入库日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date inTime;

    @ExcelProperty("托管")
    private String trust;
    @ExcelIgnore
    private boolean isTrust;

    @ExcelProperty("托管单位")
    private String departmentName;

    @ExcelProperty("联系人")
    private String linkMan;

    @ExcelProperty("联系电话")
    private String phone;

    @ExcelProperty("定级")
//    private String levelName;
//    @ExcelIgnore
    private Long level;

    @ExcelProperty("使用地点")
    private String roomName;

    @ExcelProperty("位置")
    private String position;

    @ExcelProperty("虚拟化")
    private  String vm;
    @ExcelIgnore
    private boolean isVm;

    @ExcelProperty("有效存储")
    private Double availableStorage;

    @ExcelProperty("交换机类型")
    private String switchType;

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

    public String getTrust() {
        String tmp = "否";
        if(isTrust){
            tmp = "是";
        }
        return tmp;
    }

    public void setTrust(String trust) {
        this.trust = trust;
    }


    public String getVm() {
        String tmp = "否";
        if(isVm){
            tmp = "是";
        }
        return tmp;
    }

    public void setVm(String vm) {
        this.vm = vm;
    }

}
