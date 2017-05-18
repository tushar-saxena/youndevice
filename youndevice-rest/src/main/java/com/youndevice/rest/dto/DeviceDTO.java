package com.youndevice.rest.dto;


public class DeviceDTO {

    private Long id;

    private Boolean enabled = Boolean.FALSE;

    private String deviceType;

    private String userFriendlyName;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getUserFriendlyName() {
        return userFriendlyName;
    }

    public void setUserFriendlyName(String userFriendlyName) {
        this.userFriendlyName = userFriendlyName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DeviceDTO(Long id, Boolean enabled, String deviceType, String userFriendlyName, String status) {

        this.id = id;
        this.enabled = enabled;
        this.deviceType = deviceType;
        this.userFriendlyName = userFriendlyName;
        this.status = status;
    }
}
