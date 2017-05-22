package com.youndevice.rest.dto;


public class ApplianceDTO {

    private Long id;

    private String webStatus;

    private String actualDeviceStatus;

    private String userFriendlyName;

    private DeviceDTO device;

    public DeviceDTO getDevice() {
        return device;
    }

    public void setDevice(DeviceDTO device) {
        this.device = device;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWebStatus() {
        return webStatus;
    }

    public void setWebStatus(String webStatus) {
        this.webStatus = webStatus;
    }

    public String getActualDeviceStatus() {
        return actualDeviceStatus;
    }

    public void setActualDeviceStatus(String actualDeviceStatus) {
        this.actualDeviceStatus = actualDeviceStatus;
    }

    public String getUserFriendlyName() {
        return userFriendlyName;
    }

    public void setUserFriendlyName(String userFriendlyName) {
        this.userFriendlyName = userFriendlyName;
    }

    public static ApplianceDTO getApplianceDTO(Long id, String webStatus, String actualDeviceStatus, String userFriendlyName, DeviceDTO device) {
        ApplianceDTO applianceDTO = new ApplianceDTO();
        applianceDTO.id = id;
        applianceDTO.webStatus = webStatus;
        applianceDTO.actualDeviceStatus = actualDeviceStatus;
        applianceDTO.userFriendlyName = userFriendlyName;
        applianceDTO.device = device;
        return applianceDTO;
    }
}
