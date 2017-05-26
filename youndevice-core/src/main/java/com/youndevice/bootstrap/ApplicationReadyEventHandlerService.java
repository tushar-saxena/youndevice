package com.youndevice.bootstrap;

import com.youndevice.domain.Appliance;
import com.youndevice.domain.Device;
import com.youndevice.domain.Role;
import com.youndevice.domain.User;
import com.youndevice.enums.RoleType;
import com.youndevice.services.repoServices.RoleRepoService;
import com.youndevice.services.repoServices.UserRepoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

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

    @Autowired
    RoleRepoService roleRepoService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (skipBootstrap) {
            log.info("Bootstrap skipped as config property 'skip.bootstrap' is set to true");
        } else {
            createTestRole();
            createDefaultUser();
            createUserTestData();
        }
    }

    private void createUserTestData() {
        List<User> userList = new ArrayList<>();
        String testEmailId = null;
        for (int i = 1; i <= 5; i++) {
            testEmailId = "email" + i + "@gmail.com";
            if (userRepoService.countUserByEmailId(testEmailId) < 1) {
                User user = new User("firstName", "lastName", testEmailId, new BCryptPasswordEncoder().encode("igdefault"));
                user.setId("user" + i);
                user.setDevices(createTestDevices(i, user));
                HashSet<Role> roleSet = new HashSet<Role>();
                roleSet.add(roleRepoService.findRoleByAuthority(RoleType.ROLE_USER.name()));
                user.setRoles(roleSet);
                userList.add(user);
            }
        }
        userRepoService.save(userList);
    }

    private void createDefaultUser() {
        List<User> userList = new ArrayList<>();
        User user1 = userRepoService.findUserByEmailId("ajayishan.06@gmail.com");
        if (ObjectUtils.isEmpty(user1)) {
            user1 = new User("Ajay", "Kumar", "ajayishan.06@gmail.com", new BCryptPasswordEncoder().encode("igdefault"));
            user1.setId("ajay");
            user1.setDevices(createTestDevices(1, user1));
            HashSet<Role> roleSet1 = new HashSet<Role>();
            roleSet1.add(roleRepoService.findRoleByAuthority(RoleType.ROLE_USER.name()));
            user1.setRoles(roleSet1);
            userList.add(user1);
        }

        User user2 = userRepoService.findUserByEmailId("anujgargcse@gmail.com");
        if (ObjectUtils.isEmpty(user2)) {
            user2 = new User("Anuj", "Garg", "anujgargcse@gmail.com", new BCryptPasswordEncoder().encode("igdefault"));
            user2.setId("9KvXRzjuYKchu7zUfoiYQD1P6iT2");
            user2.setDevices(createTestDevices(1, user2));
            HashSet<Role> roleSet2 = new HashSet<Role>();
            roleSet2.add(roleRepoService.findRoleByAuthority(RoleType.ROLE_USER.name()));
            user2.setRoles(roleSet2);
            userList.add(user2);
        }
        if (!ObjectUtils.isEmpty(userList)) {
            userRepoService.save(userList);
        }
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
            appliance.setWebStatus("1");
            appliance.setUserFriendlyName("User Friendly name for Appliance- ");
            appliances.add(appliance);
            appliance.setDevice(device);
        }
        return appliances;
    }

    private void createTestRole() {
        if (roleRepoService.countRoleByAuthority(RoleType.ROLE_ADMIN.name()) < 1) {
            Role role1 = new Role();
            role1.setAuthority(RoleType.ROLE_ADMIN.name());
            roleRepoService.save(role1);
        }
        if (roleRepoService.countRoleByAuthority(RoleType.ROLE_USER.name()) < 1) {
            Role role2 = new Role();
            role2.setAuthority(RoleType.ROLE_USER.name());
            roleRepoService.save(role2);
        }
    }
}
