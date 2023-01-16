package com.emsi.HallBooking.controller.rest;

import com.emsi.HallBooking.domaine.HallVo;
import com.emsi.HallBooking.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class HallRestController {
    @Autowired
    private IService service;

    @GetMapping(value = "/rest/emp", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public List<HallVo> getAll() {
        return service.getHalls();
    }

    @GetMapping(value = "/rest/hall/{id}")
    public ResponseEntity<Object> getHallById(@PathVariable(value = "id") Long hallVoId) {
        HallVo hallVoFound = service.getHallById(hallVoId);
        if (hallVoFound == null)
            return new ResponseEntity<>("hall doesn't exist", HttpStatus.OK);
        return new ResponseEntity<>(hallVoFound, HttpStatus.OK);
    }

    @PostMapping(value = "/rest/hall")
    public ResponseEntity<Object> createHall(@Valid @RequestBody HallVo hallVo) {
        service.save(hallVo);
        return new ResponseEntity<>("hall is created successfully", HttpStatus.CREATED);
    }


    @PutMapping(value = "/rest/emp/{id}")
    public ResponseEntity<Object> updateHall(@PathVariable(name = "id") Long hallVoId, @RequestBody HallVo hallVo) {
        HallVo hallVoFound = service.getHallById(hallVoId);
        if (hallVoFound == null)
            return new ResponseEntity<>("hall doesn't exist", HttpStatus.OK);
        hallVo.setId(hallVoId);
        service.save(hallVo);
        return new ResponseEntity<>("Hall is updated successsfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/rest/emp/{id}")
    public ResponseEntity<Object> deleteHall(@PathVariable(name = "id") Long hallVoId) {
        HallVo hallVoFound = service.getHallById(hallVoId);
        if (hallVoFound == null)
            return new ResponseEntity<>("hall doesn't exist", HttpStatus.OK);
        service.delete(hallVoId);
        return new ResponseEntity<>("Hall is deleted successsfully", HttpStatus.OK);
    }

    @GetMapping(value = "/rest/sort/{fieldName}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public List<HallVo> sortBy(@PathVariable String fieldName) {
        return service.sortBy(fieldName);
    }

    @GetMapping("/rest/pagination/{pageid}/{size}")
    public List<HallVo> pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
        return service.findAll(pageid, size);
    }
}
