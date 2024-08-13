package cn.edu.hhstu.pojo;


import org.springframework.format.annotation.DateTimeFormat;

public class DeviceLog {

    private int id;
    private int logType;
    private String logTypeDesc;
    private String deviceId;
    private String deviceName;
    private String outMan;
    @DateTimeFormat(pattern = "yyyy-MM-dd") //前台传数据到后台
    private java.util.Date outTime;
    private String outPhone;
    private String outCreator;
    private String remark;
    private String creator;
    private java.sql.Timestamp createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLogType() {
        return logType;
    }

    public void setLogType(int logType) {
        this.logType = logType;
    }

    public String getLogTypeDesc() {
        return logTypeDesc;
    }

    public void setLogTypeDesc(String logTypeDesc) {
        this.logTypeDesc = logTypeDesc;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getOutMan() {
        return outMan;
    }

    public void setOutMan(String outMan) {
        this.outMan = outMan;
    }


    public java.util.Date getOutTime() {
        return outTime;
    }

    public void setOutTime(java.util.Date outTime) {
        this.outTime = outTime;
    }


    public String getOutPhone() {
        return outPhone;
    }

    public void setOutPhone(String outPhone) {
        this.outPhone = outPhone;
    }


    public String getOutCreator() {
        return outCreator;
    }

    public void setOutCreator(String outCreator) {
        this.outCreator = outCreator;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }


    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }

}
