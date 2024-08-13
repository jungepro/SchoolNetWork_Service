package cn.edu.hhstu.pojo;


public class IpPort {

  private long id;
  private long ipAddressId;
  private String protocal;
  private long port;
  private long openType;
  private String openRange;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getIpAddressId() {
    return ipAddressId;
  }

  public void setIpAddressId(long ipAddressId) {
    this.ipAddressId = ipAddressId;
  }


  public String getProtocal() {
    return protocal;
  }

  public void setProtocal(String protocal) {
    this.protocal = protocal;
  }


  public long getPort() {
    return port;
  }

  public void setPort(long port) {
    this.port = port;
  }


  public long getOpenType() {
    return openType;
  }

  public void setOpenType(long openType) {
    this.openType = openType;
  }


  public String getOpenRange() {
    return openRange;
  }

  public void setOpenRange(String openRange) {
    this.openRange = openRange;
  }

}
