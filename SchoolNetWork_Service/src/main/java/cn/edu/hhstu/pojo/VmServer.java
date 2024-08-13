package cn.edu.hhstu.pojo;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

public class VmServer {

  private String id;
  private String deviceId;
  private String serverNo;
  private String serverName;
  private Long osId;
  private String osVersion;
  private String departmentId;
  private String linkMan;
  private String phone;
  private long level;
  private long status;
  @DateTimeFormat(pattern = "yyyy-MM-dd") //前台传数据到后台
  private java.util.Date inTime;
  private String creator;
  private java.sql.Timestamp createTime;
  private String remark;

  //多ip用“，”拼接
  private String ip;

  private Device device;
  private Os os;
  private Department department;
  private List<DeviceIp> deviceIpList;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }


  public String getServerNo() {
    return serverNo;
  }

  public void setServerNo(String serverNo) {
    this.serverNo = serverNo;
  }


  public String getServerName() {
    return serverName;
  }

  public void setServerName(String serverName) {
    this.serverName = serverName;
  }


  public Long getOsId() {
    return osId;
  }

  public void setOsId(Long osId) {
    this.osId = osId;
  }


  public String getOsVersion() {
    return osVersion;
  }

  public void setOsVersion(String osVersion) {
    this.osVersion = osVersion;
  }


  public String getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(String departmentId) {
    this.departmentId = departmentId;
  }


  public String getLinkMan() {
    return linkMan;
  }

  public void setLinkMan(String linkMan) {
    this.linkMan = linkMan;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public long getLevel() {
    return level;
  }

  public void setLevel(long level) {
    this.level = level;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public java.util.Date getInTime() {
    return inTime;
  }

  public void setInTime(java.util.Date inTime) {
    this.inTime = inTime;
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


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public Device getDevice() {
    return device;
  }

  public void setDevice(Device device) {
    this.device = device;
  }

  public Os getOs() {
    return os;
  }

  public void setOs(Os os) {
    this.os = os;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public List<DeviceIp> getDeviceIpList() {
    return deviceIpList;
  }

  public void setDeviceIpList(List<DeviceIp> deviceIpList) {
    this.deviceIpList = deviceIpList;
  }
}
