package cn.edu.hhstu.pojo;


public class  ArticleClass {

  private String id;
  private String className;
  private long category;
  private long orderNo;
  private boolean shown;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }


  public long getCategory() {
    return category;
  }

  public void setCategory(long category) {
    this.category = category;
  }


  public long getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(long orderNo) {
    this.orderNo = orderNo;
  }


  public boolean getShown() {
    return shown;
  }

  public void setShown(boolean shown) {
    this.shown = shown;
  }

}
