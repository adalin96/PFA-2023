package com.emsi.HallBooking.domaine;

import com.emsi.HallBooking.model.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleConverter {

    public static RoleVo toRoleVo(Role role) {
        if (role == null)
            return null;
        RoleVo roleVo = new RoleVo();
        roleVo.setId(role.getId());
        roleVo.setRole(role.getRole());
        return roleVo;
    }

    public static Role toRole(RoleVo roleVo) {
        if (roleVo == null)
            return null;
        Role role = new Role();
        role.setId(roleVo.getId());
        role.setRole(roleVo.getRole());
        return role;
    }

    public static List<RoleVo> toRoleVoList(List<Role> roleList) {
        if (roleList == null || roleList.isEmpty())
            return null;
        List<RoleVo> roleVoList = new ArrayList<>();
        for (Role role : roleList) {
            roleVoList.add(toRoleVo(role));
        }
        return roleVoList;
    }

    public static List<Role> toRoleList(List<RoleVo> roleVoList) {
        if (roleVoList == null || roleVoList.isEmpty())
            return null;
        List<Role> roleList = new ArrayList<>();
        for (RoleVo roleVo : roleVoList) {
            roleList.add(toRole(roleVo));
        }
        return roleList;
    }
}
