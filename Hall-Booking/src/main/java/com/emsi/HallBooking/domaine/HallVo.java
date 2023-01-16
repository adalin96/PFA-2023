package com.emsi.HallBooking.domaine;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HallVo {
    private Long id;
    private String name;
    private String size;
    private Boolean tv;
    private Boolean projector;
    private Boolean speakers;
    private Boolean mic;
    private Double price;
}
