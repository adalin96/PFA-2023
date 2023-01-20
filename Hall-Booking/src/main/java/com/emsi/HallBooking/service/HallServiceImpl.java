package com.emsi.HallBooking.service;

import com.emsi.HallBooking.dao.HallRepository;
import com.emsi.HallBooking.domaine.HallConverter;
import com.emsi.HallBooking.domaine.HallVo;
import com.emsi.HallBooking.service.model.Hall;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallServiceImpl implements IHallService {

    @Autowired
    private HallRepository hallRepository;

    @Override
    public List<HallVo> getHalls() {
        List<Hall> list = hallRepository.findAll();
        return HallConverter.toListHallVo(list);
    }

    @Override
    public void save(HallVo hallVo) {
        hallRepository.save(HallConverter.toHallBo(hallVo));
    }

    @Override
    public HallVo getHallById(Long id) {
        boolean found = hallRepository.existsById(id);
        if (!found)
            return null;
        return HallConverter.toHallVo(hallRepository.getOne(id));
    }

    @Override
    public void delete(Long id) {
        hallRepository.deleteById(id);
    }

    @Override
    public List<HallVo> findByName(String name) {
        List<Hall> list = hallRepository.findByName(name);
        return HallConverter.toListHallVo(list);
    }

    @Override
    public List<HallVo> findBySize(String size) {
        List<Hall> list = hallRepository.findBySize(size);
        return HallConverter.toListHallVo(list);
    }

    @Override
    public List<HallVo> findByTv(Boolean tv) {
        List<Hall> list = hallRepository.findByTv(tv);
        return HallConverter.toListHallVo(list);
    }

    @Override
    public List<HallVo> findByProjector(Boolean projector) {
        List<Hall> list = hallRepository.findByProjector(projector);
        return HallConverter.toListHallVo(list);
    }

    @Override
    public List<HallVo> findBySpeakers(Boolean speakers) {
        List<Hall> list = hallRepository.findBySpeakers(speakers);
        return HallConverter.toListHallVo(list);
    }

    @Override
    public List<HallVo> findByMic(Boolean mic) {
        List<Hall> list = hallRepository.findByMic(mic);
        return HallConverter.toListHallVo(list);
    }

//    @Override
//    public List<HallVo> findByPrice(Boolean price) {
//        List<Hall> list = hallRepository.findByPrice(price);
//        return HallConverter.toListHallVo(list);
//    }

    @Override
    public List<HallVo> search(String name, String size, Boolean tv, Boolean projector, Boolean speakers, Boolean mic) {
        // Only look for non-null attributes using Query By Example that has only non-null attributes
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues()
            // Name is substring with ignored case
            .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Hall hall = new Hall();

        if (!name.equals("")) {
            hall.setName(name);
        }
        if (!size.equals("")) {
            hall.setSize(size);
        }
        // If user selects one of these only then will they be in the Example
        if (tv.equals(true)) {
            hall.setTv(true);
        }
        if (projector.equals(true)) {
            hall.setProjector(true);
        }
        if (speakers.equals(true)) {
            hall.setSpeakers(true);
        }
        if (mic.equals(true)) {
            hall.setMic(true);
        }

        Example<Hall> query = Example.of(hall, matcher);
        return HallConverter.toListHallVo(hallRepository.findAll(query, Sort.by("id")));
    }

    @Override
    public List<HallVo> findAll(int pageId, int size) {
        Page<Hall> result = hallRepository.findAll(PageRequest.of(pageId, size, Sort.Direction.ASC, "name"));
        return HallConverter.toListHallVo(result.getContent());
    }

    @Override
    public List<HallVo> sortBy(String fieldName) {
        return HallConverter.toListHallVo(hallRepository.findAll(Sort.by(fieldName)));
    }

//    @Override
//    public void run(String... args) throws Exception {
//        hallRepository.deleteAll();
//        hallRepository.saveUser(new Hall("amphi1", "L", false,false,false,false,100.0));
//        hallRepository.saveUser(new Hall("amphi2", "L",false,false,false,false,100.0));
//        hallRepository.saveUser(new Hall("amphi3", "L", false,false,false,false,100.0));
//        hallRepository.saveUser(new Hall("cc4", "S", false,false,false,false,100.0));
//        hallRepository.saveUser(new Hall("cc5", "S", false,false,false,false,100.0));
//        hallRepository.saveUser(new Hall("cc6", "S", false,false,false,false,100.0));
//        hallRepository.saveUser(new Hall("a7", "M", false,false,false,false,100.0));
//        hallRepository.saveUser(new Hall("a8", "M", false,false,false,false,100.0));
//    }
}
