package com.emsi.HallBooking.domaine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleVo {

    private Long id;

    private String role;

    public RoleVo(String admin) {
    }
}
