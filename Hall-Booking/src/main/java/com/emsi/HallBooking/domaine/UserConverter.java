package com.emsi.HallBooking.domaine;

import com.emsi.HallBooking.model.AppUser;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {

    public static UserVo toUserVo(AppUser appUser) {
        if (appUser == null)
            return null;
        UserVo userVo = new UserVo();
        userVo.setId(appUser.getId());
        userVo.setUsername(appUser.getUsername());
        userVo.setPassword(appUser.getPassword());
        userVo.setRoles(RoleConverter.toRoleVoList(appUser.getRoles()));
        return userVo;
    }


    public static AppUser toUser(UserVo userVo) {
        if (userVo == null)
            return null;
        AppUser appUser = new AppUser();
        if (userVo.getId() != null)
            appUser.setId(userVo.getId());
        appUser.setUsername(userVo.getUsername());
        appUser.setPassword(userVo.getPassword());
        appUser.setRoles(RoleConverter.toRoleList(userVo.getRoles()));
        return appUser;
    }

    public static List<UserVo> toUserVoList(List<AppUser> appUserList) {
        if (appUserList == null || appUserList.isEmpty())
            return null;
        List<UserVo> userVoList = new ArrayList<>();
        for (AppUser appUser : appUserList) {
            userVoList.add(toUserVo(appUser));
        }
        return userVoList;
    }

    public static List<AppUser> toUserList(List<UserVo> userVoList) {
        if (userVoList == null || userVoList.isEmpty())
            return null;
        List<AppUser> appUserList = new ArrayList<>();
        for (UserVo userVo : userVoList) {
            appUserList.add(toUser(userVo));
        }
        return appUserList;
    }
}
