package com.youndevice.domain;

import javax.persistence.*;

@Entity(name = "appliance")
public class Appliance extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String webStatus;

    private String actualDeviceStatus;


    private String userFriendlyName;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

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

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
