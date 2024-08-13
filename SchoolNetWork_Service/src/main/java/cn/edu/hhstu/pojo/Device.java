package cn.edu.hhstu.pojo;


import java.util.List;

public class  Device {

  private String id;
  private String deviceBatchId;
  private String serverNo;
  private String serverName;
  private String seriesNumber;
  private boolean isTrust;
  private String departmentId;
  private String linkMan;
  private String phone;
  private Long level;
  private long status;
  private String roomId;
  private String position;
  private String creator;
  private java.sql.Timestamp createTime;
  private String remark;
  private boolean isVm;
  private Long osId;
  private String osVersion;
  private Double availableStorage;
  private String switchType;

  //多ip用“，”拼接
  private String ip;

  private DeviceBatch deviceBatch;
  private Department department;
  private Room room;
  private Os os;
  private List<DeviceIp> deviceIpList;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getDeviceBatchId() {
    return deviceBatchId;
  }

  public void setDeviceBatchId(String deviceBatchId) {
    this.deviceBatchId = deviceBatchId;
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


  public String getSeriesNumber() {
    return seriesNumber;
  }

  public void setSeriesNumber(String seriesNumber) {
    this.seriesNumber = seriesNumber;
  }


  public boolean getIsTrust() {
    return isTrust;
  }

  public void setIsTrust(boolean isTrust) {
    this.isTrust = isTrust;
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


  public Long getLevel() {
    return level;
  }

  public void setLevel(Long level) {
    this.level = level;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public String getRoomId() {
    return roomId;
  }

  public void setRoomId(String roomId) {
    this.roomId = roomId;
  }


  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
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


  public boolean getIsVm() {
    return isVm;
  }

  public void setIsVm(boolean isVm) {
    this.isVm = isVm;
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


  public Double getAvailableStorage() {
    return availableStorage;
  }

  public void setAvailableStorage(Double availableStorage) {
    this.availableStorage = availableStorage;
  }


  public String getSwitchType() {
    return switchType;
  }

  public void setSwitchType(String switchType) {
    this.switchType = switchType;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public DeviceBatch getDeviceBatch() {
    return deviceBatch;
  }

  public void setDeviceBatch(DeviceBatch deviceBatch) {
    this.deviceBatch = deviceBatch;
  }


  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }


  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }


  public Os getOs() {
    return os;
  }

  public void setOs(Os os) {
    this.os = os;
  }


  public List<DeviceIp> getDeviceIpList() {
    return deviceIpList;
  }

  public void setDeviceIpList(List<DeviceIp> deviceIpList) {
    this.deviceIpList = deviceIpList;
  }
}
