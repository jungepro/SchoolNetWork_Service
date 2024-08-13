package cn.edu.hhstu.pojo;


public class  DeviceIp {

  private long ipAddressId;
  private String deviceId;
  private long deviceTypeId;
  private String creator;
  private java.sql.Timestamp createTime;

  private IpAddress ipAddress;
  private Device device;


  public long getIpAddressId() {
    return ipAddressId;
  }

  public void setIpAddressId(long ipAddressId) {
    this.ipAddressId = ipAddressId;
  }


  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }


  public long getDeviceTypeId() {
    return deviceTypeId;
  }

  public void setDeviceTypeId(long deviceTypeId) {
    this.deviceTypeId = deviceTypeId;
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

  public IpAddress getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(IpAddress ipAddress) {
    this.ipAddress = ipAddress;
  }

  public Device getDevice() {
    return device;
  }

  public void setDevice(Device device) {
    this.device = device;
  }
}
