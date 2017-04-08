package com.youndevice.bootstrap;

import com.youndevice.domain.Appliance;
import com.youndevice.domain.Device;
import com.youndevice.domain.User;
import com.youndevice.services.repoServices.UserRepoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ApplicationReadyEventHandlerService implements ApplicationListener<ApplicationReadyEvent> {

    Log log = LogFactory.getLog(ApplicationReadyEventHandlerService.class);

    @Value("${skip.bootstrap:false}")
    Boolean skipBootstrap;

    @Autowired
    UserRepoService userRepoService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (skipBootstrap) {
            log.info("Bootstrap skipped as config property 'skip.bootstrap' is set to true");
        } else {
            createUserTestData();
        }
    }

    private void createUserTestData() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User("firstName", "lastName", "email" + i + "gmail.com", "igdefault");
            user.setDevices(createTestDevices(i, user));
            userList.add(user);
        }
        userRepoService.save(userList);
    }

    private Set<Device> createTestDevices(Integer noOfDevices, User user) {
        Set<Device> devices = new HashSet<>(noOfDevices);
        for (Integer count = 0; count < noOfDevices; count++) {
            Device device = new Device();
            device.setDeviceType("Sample Type- " + count);
            device.setUserFriendlyName("User Friendly name for Device");
            device.setAppliances(createTestAppliances(count, device));
            device.setUser(user);
            devices.add(device);
        }
        return devices;
    }

    private Set<Appliance> createTestAppliances(Integer noOfAppliances, Device device) {
        Set<Appliance> appliances = new HashSet<>(noOfAppliances);
        for (Integer count = 0; count < noOfAppliances; count++) {
            Appliance appliance = new Appliance();
            appliance.setStatus("1");
            appliance.setUserFriendlyName("User Friendly name for Appliance- ");
            appliances.add(appliance);
            appliance.setDevice(device);
        }
        return appliances;
    }
}
