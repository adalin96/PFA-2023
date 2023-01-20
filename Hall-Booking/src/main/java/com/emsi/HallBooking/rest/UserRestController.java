package com.emsi.HallBooking.rest;

import com.emsi.HallBooking.domaine.RoleConverter;
import com.emsi.HallBooking.domaine.RoleVo;
import com.emsi.HallBooking.domaine.UserConverter;
import com.emsi.HallBooking.domaine.UserVo;
import com.emsi.HallBooking.service.IUserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserVo>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<UserVo> saveUser(@RequestBody UserVo userVo) {
        return ResponseEntity.ok().body(UserConverter.toUserVo(userService.saveUser(userVo)));
    }

    @PostMapping("/role/save")
    public ResponseEntity<RoleVo> saveRole(@RequestBody RoleVo roleVo) {

        return ResponseEntity.ok().body(RoleConverter.toRoleVo(userService.saveRole(roleVo)));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

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

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}
