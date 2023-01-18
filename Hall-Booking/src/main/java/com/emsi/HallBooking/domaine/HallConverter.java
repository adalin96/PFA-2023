package com.emsi.HallBooking.domaine;

import com.emsi.HallBooking.service.model.Hall;

import java.util.ArrayList;
import java.util.List;

public class HallConverter {

    //fonction qui fait la convertion du Hall en HallVo
    public static HallVo toHallVo(Hall hall) {
        if (hall == null || hall.getId() == null) {
            return null;
        }

        HallVo hallVo = new HallVo();
        hallVo.setId(hall.getId());
        hallVo.setMic(hall.getMic());
        hallVo.setName(hall.getName());
        hallVo.setPrice(hall.getPrice());
        hallVo.setProjector(hall.getProjector());
        hallVo.setSize(hall.getSize());
        hallVo.setSpeakers(hall.getSpeakers());
        hallVo.setName(hall.getName());

        return hallVo;
    }

    //fonction qui fait la convertion du HallVo en Hall
    public static Hall toHall(HallVo hallVo) {
        if (hallVo == null || hallVo.getId() == null) {
            return null;
        }

        Hall hall = new Hall();

        hall.setId(hallVo.getId());
        hall.setMic(hallVo.getMic());
        hall.setName(hallVo.getName());
        hall.setPrice(hallVo.getPrice());
        hall.setProjector(hallVo.getProjector());
        hall.setSize(hallVo.getSize());
        hall.setSpeakers(hallVo.getSpeakers());
        hall.setName(hallVo.getName());

        return hall;
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
