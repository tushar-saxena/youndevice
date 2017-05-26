package com.youndevice.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "device")
public class Device extends BaseEntity {
    @Id
    private String id;

    private Boolean enabled = Boolean.FALSE;

    private String deviceType;

    private String userFriendlyName;

    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "device", orphanRemoval = true)
    private Set<Appliance> appliances;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Appliance> getAppliances() {
        return appliances;
    }

    public void setAppliances(Set<Appliance> appliances) {
        this.appliances = appliances;
    }

    public void addToAppliances(Appliance appliance) {
        if (this.appliances == null) {
            this.appliances = new HashSet<>();
        }
        this.appliances.add(appliance);
        appliance.setDevice(this);
    }

    public void addUser(User user) {
        user.addToDevices(this);
    }
}
