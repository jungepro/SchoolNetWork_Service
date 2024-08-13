package cn.edu.hhstu.pojo;


public class IpDns {

  private long dnsId;
  private long ipAddressId;

  //dns表域名
  private String domainName;

  public long getDnsId() {
    return dnsId;
  }

  public void setDnsId(long dnsId) {
    this.dnsId = dnsId;
  }

  public long getIpAddressId() {
    return ipAddressId;
  }

  public void setIpAddressId(long ipAddressId) {
    this.ipAddressId = ipAddressId;
  }

  public String getDomainName() {
    return domainName;
  }

  public void setDomainName(String domainName) {
    this.domainName = domainName;
  }
}
