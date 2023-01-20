package com.emsi.HallBooking.domaine;

import com.emsi.HallBooking.service.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

    private Long id;

    private String username;

    private String password;

    private List<RoleVo> roles = new ArrayList<>();
}
