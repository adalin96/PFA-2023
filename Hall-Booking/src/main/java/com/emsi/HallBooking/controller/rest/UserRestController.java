package com.emsi.HallBooking.controller.rest;


import com.emsi.HallBooking.domaine.RoleConverter;
import com.emsi.HallBooking.domaine.RoleVo;
import com.emsi.HallBooking.domaine.UserConverter;
import com.emsi.HallBooking.domaine.UserVo;
import com.emsi.HallBooking.service.IUserService;
import com.emsi.HallBooking.service.model.AppUser;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
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