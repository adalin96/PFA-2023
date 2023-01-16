package com.emsi.HallBooking.service;

import com.emsi.HallBooking.domaine.HallVo;
import com.emsi.HallBooking.service.model.Hall;

import java.util.List;

public interface IService {
    List<HallVo> getHalls();
    void save(HallVo hallVo);
    HallVo getHallById(Long id);
    void delete(Long id);
    List<HallVo> findByName(String name);
    List<HallVo> findBySize(String size);
    List<HallVo> findByTv(Boolean tv);
    List<HallVo> findByProjector(Boolean projector);
    List<HallVo> findBySpeakers(Boolean speakers);
    List<HallVo> findByMic(Boolean mic);
    //List<HallVo> findByPrice(Boolean price);

    //multiple variable search
    public List<HallVo> search(String name, String size, Boolean tv, Boolean projector, Boolean speakers, Boolean mic);

    //Pour la pagination
    List<HallVo> findAll(int pageId, int size);

    //pour le tri
    List<HallVo> sortBy(String fieldName);
}
