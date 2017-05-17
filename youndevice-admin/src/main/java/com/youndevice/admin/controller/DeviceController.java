package com.youndevice.admin.controller;

import com.youndevice.admin.model.User;
import com.youndevice.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class DeviceController {

    @Autowired
    private UserService userService;

    @RequestMapping(value={"/device/list"}, method = RequestMethod.GET)
    public ModelAndView listDevices(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("deviceListing");
        return modelAndView;
    }

}