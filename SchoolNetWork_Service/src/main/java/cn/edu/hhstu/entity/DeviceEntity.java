package cn.edu.hhstu.entity;

public class  DeviceEntity {
    private String id;
    private Integer deviceTypeId;
    private String deviceTypeName;
    private String serverName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(Integer deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
