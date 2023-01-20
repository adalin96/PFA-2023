package com.emsi.HallBooking.domaine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingVo {

    private Long id;

    private Date date;

    private Long idHall;

    private Long idClient;
}
