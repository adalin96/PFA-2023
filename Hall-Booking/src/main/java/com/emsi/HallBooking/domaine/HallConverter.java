package com.emsi.HallBooking.domaine;

import com.emsi.HallBooking.model.Hall;

import java.util.ArrayList;
import java.util.List;

public class HallConverter {

    //fonction qui fait la convertion du Hall en HallVo
    public static HallVo toHallVo(Hall hallBo) {
        if (hallBo == null) {
            return null;
        }
        HallVo hallVo = new HallVo();

        hallVo.setId(hallBo.getId());
        hallVo.setName(hallBo.getName());
        hallVo.setSize(hallBo.getSize());
        hallVo.setTv(hallBo.getTv());
        hallVo.setProjector(hallBo.getProjector());
        hallVo.setSpeakers(hallBo.getSpeakers());
        hallVo.setMic(hallBo.getMic());
        hallVo.setPrice(hallBo.getPrice());

        return hallVo;
    }

    //fonction qui fait la convertion du HallVo en Hall
    public static Hall toHallBo(HallVo hallVo) {
        if (hallVo == null) {
            return null;
        }
        Hall hallBo = new Hall();

        hallBo.setId(hallVo.getId());
        hallBo.setName(hallVo.getName());
        hallBo.setSize(hallVo.getSize());
        hallBo.setTv(hallVo.getTv());
        hallBo.setProjector(hallVo.getProjector());
        hallBo.setSpeakers(hallVo.getSpeakers());
        hallBo.setMic(hallVo.getMic());
        hallBo.setPrice(hallVo.getPrice());

        return hallBo;
    }

    //fonction convertie une list de Hall en une list de HallVo
    public static List<HallVo> toListHallVo(List<Hall> listHall) {
        List<HallVo> listHallVo = new ArrayList<>();
        for (Hall hall : listHall) {
            listHallVo.add(toHallVo(hall));
        }
        return listHallVo;
    }
}
