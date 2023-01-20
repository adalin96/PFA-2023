package com.emsi.HallBooking.service;

import com.emsi.HallBooking.domaine.RoleVo;
import com.emsi.HallBooking.domaine.UserVo;
import com.emsi.HallBooking.model.AppUser;
import com.emsi.HallBooking.model.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    AppUser saveUser(UserVo user);
    Role saveRole(RoleVo role);

    void addRoleToUser(String username, String roleName);
    UserVo getUser(String username);
    List<UserVo> getAllUsers();
    // List<RoleVo> getAllRoles();
    // RoleVo getRoleByName(String nameRole);
    void cleanDataBase();
}
