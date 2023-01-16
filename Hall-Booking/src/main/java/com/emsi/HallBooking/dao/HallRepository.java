package com.emsi.HallBooking.dao;

import com.emsi.HallBooking.service.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HallRepository extends JpaRepository<Hall /*la class model*/, Long /*le type de la cle primaire*/> {
    List<Hall> findByName(String name);
    List<Hall> findBySize(String size);
    List<Hall> findByTv(Boolean tv);
    List<Hall> findByProjector(Boolean projector);
    List<Hall> findBySpeakers(Boolean speakers);
    List<Hall> findByMic(Boolean mic);
    List<Hall> findByPrice(Boolean price);

}
