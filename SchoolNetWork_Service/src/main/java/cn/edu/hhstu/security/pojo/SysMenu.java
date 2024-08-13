package cn.edu.hhstu.security.pojo;


public class SysMenu {

  private long id;
  private long parentId;
  private String name;
  private String css;
  private String href;
  private long type;
  private String permission;
  private long sort;
  private boolean hidden;
  private boolean disabled;
  private java.sql.Timestamp createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getParentId() {
    return parentId;
  }

  public void setParentId(long parentId) {
    this.parentId = parentId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getCss() {
    return css;
  }

  public void setCss(String css) {
    this.css = css;
  }


  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }


  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }


  public String getPermission() {
    return permission;
  }

  public void setPermission(String permission) {
    this.permission = permission;
  }


  public long getSort() {
    return sort;
  }

  public void setSort(long sort) {
    this.sort = sort;
  }


  public boolean isHidden() {
    return hidden;
  }

  public void setHidden(boolean hidden) {
    this.hidden = hidden;
  }

  public boolean isDisabled() {
    return disabled;
  }

  public void setDisabled(boolean disabled) {
    this.disabled = disabled;
  }

  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
