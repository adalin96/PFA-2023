package com.emsi.HallBooking.rest;

import com.emsi.HallBooking.domaine.RoleConverter;
import com.emsi.HallBooking.domaine.RoleVo;
import com.emsi.HallBooking.domaine.UserConverter;
import com.emsi.HallBooking.domaine.UserVo;
import com.emsi.HallBooking.service.IUserService;
<<<<<<< HEAD:Hall-Booking/src/main/java/com/emsi/HallBooking/controller/rest/UserRestController.java
=======
import com.emsi.HallBooking.model.AppUser;
>>>>>>> 59b072f40c77914ea0e41b76c19db376ceea4cf2:Hall-Booking/src/main/java/com/emsi/HallBooking/rest/UserRestController.java
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

}

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}
