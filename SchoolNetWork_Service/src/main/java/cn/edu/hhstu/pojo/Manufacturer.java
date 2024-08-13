package cn.edu.hhstu.pojo;


public class Manufacturer {

  private long id;
  private String manufacturerName;
  private String briefName;
  private String phone;
  private String remark;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getManufacturerName() {
    return manufacturerName;
  }

  public void setManufacturerName(String manufacturerName) {
    this.manufacturerName = manufacturerName;
  }


  public String getBriefName() {
    return briefName;
  }

  public void setBriefName(String briefName) {
    this.briefName = briefName;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
