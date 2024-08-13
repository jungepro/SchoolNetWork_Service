package cn.edu.hhstu.entity;

import org.springframework.format.annotation.DateTimeFormat;


public class ApplicationSelfEntity {
    private String id;
    private String appName;
    private long appKind;
    private String serverSite;
    @DateTimeFormat(pattern = "yyyy-MM-dd") //前台传数据到后台
    private java.util.Date installTime;
    private String departmentId;
    private String company;
    private String contact;
    private String linkMan;
    private String phone;
    private String url;
    private long status;
    private String remark;
    private String creator;
    private java.sql.Timestamp createTime;

    private Integer annual;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }


    public long getAppKind() {
        return appKind;
    }

    public void setAppKind(long appKind) {
        this.appKind = appKind;
    }


    public String getServerSite() {
        return serverSite;
    }

    public void setServerSite(String serverSite) {
        this.serverSite = serverSite;
    }


    public java.util.Date getInstallTime() {
        return installTime;
    }

    public void setInstallTime(java.util.Date installTime) {
        this.installTime = installTime;
    }


    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
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



    private String appKindName;
    public String getAppKindName() {
        return appKindName;
    }
    public void setAppKindName(String appKindName) {
        this.appKindName = appKindName;
    }


    private String departmentName;
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getStatusName(){
        String result;
        if(this.status==1)
            result = "正常";
        else if(this.status==0)
            result = "下线";
        else
            result = "未知";
        return result;
    }

    public Integer getAnnual() {
        return annual;
    }

    public void setAnnual(Integer annual) {
        this.annual = annual;
    }
}
