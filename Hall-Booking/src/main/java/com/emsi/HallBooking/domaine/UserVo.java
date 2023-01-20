package com.emsi.HallBooking.domaine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

    private Long id;

    private String username;

    private String password;

    private List<RoleVo> roles = new ArrayList<>();

    public <T> UserVo(String admin1, String admin11, List<T> asList) {

    }
}
