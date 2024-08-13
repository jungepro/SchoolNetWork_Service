package cn.edu.hhstu.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

public class  DeviceBatch {

  private String id;
  private String batchNo;
  private long deviceTypeId;
  private long manufacturerId;
  private String model;
  private int amount;
  @DateTimeFormat(pattern = "yyyy-MM-dd") //前台传数据到后台
  private java.util.Date inTime;
  private String inMan;
  private String comeFrom;
  private String creator;
  private java.sql.Timestamp createTime;
  private String remark;

  //该批次录入设备数量，关联统计数据
  private int deviceCount;

  private DeviceType deviceType;
  private Manufacturer manufacturer;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getBatchNo() {
    return batchNo;
  }

  public void setBatchNo(String batchNo) {
    this.batchNo = batchNo;
  }


  public long getDeviceTypeId() {
    return deviceTypeId;
  }

  public void setDeviceTypeId(long deviceTypeId) {
    this.deviceTypeId = deviceTypeId;
  }


  public long getManufacturerId() {
    return manufacturerId;
  }

  public void setManufacturerId(long manufacturerId) {
    this.manufacturerId = manufacturerId;
  }


  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public java.util.Date getInTime() {
    return inTime;
  }

  public void setInTime(java.util.Date inTime) {
    this.inTime = inTime;
  }


  public String getInMan() {
    return inMan;
  }

  public void setInMan(String inMan) {
    this.inMan = inMan;
  }


  public String getComeFrom() {
    return comeFrom;
  }

  public void setComeFrom(String comeFrom) {
    this.comeFrom = comeFrom;
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

  public int getDeviceCount() {
    return deviceCount;
  }

  public void setDeviceCount(int deviceCount) {
    this.deviceCount = deviceCount;
  }

  public DeviceType getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(DeviceType deviceType) {
    this.deviceType = deviceType;
  }

  public Manufacturer getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(Manufacturer manufacturer) {
    this.manufacturer = manufacturer;
  }
}
