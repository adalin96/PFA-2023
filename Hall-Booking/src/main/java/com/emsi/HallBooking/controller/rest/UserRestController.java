package com.emsi.HallBooking.controller.rest;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginRestController {

    @RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("userLogIn", auth.getName());
        modelAndView.setViewName("welcome");
        return modelAndView;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView methodForAdmin() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("userName", "Welcome " + auth.getName());
        modelAndView.addObject("adminMessage", "Content Available Only for Admins with ADMIN Role");
        modelAndView.setViewName("/admin/admin");
        return modelAndView;
    }

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public ModelAndView methodForClient() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("userName", "Welcome " + auth.getName());
        modelAndView.addObject("clientMessage", "Content Available Only for Clients with CLIENT Role");
        modelAndView.setViewName("client/client");
        return modelAndView;
    }

    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public ModelAndView accessdenied() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("access-denied");
        return modelAndView;
    }
}

