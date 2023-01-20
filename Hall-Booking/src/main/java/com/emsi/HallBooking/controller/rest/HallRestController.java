package com.emsi.HallBooking.controller.rest;

import com.emsi.HallBooking.domaine.HallVo;
import com.emsi.HallBooking.service.IHallService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/hall")
public class HallRestController {

    @Autowired
    private final IHallService hallService;

    public HallRestController(IHallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllHalls() {
        List<HallVo> hallVos = hallService.getHalls();
        if (hallVos == null)
            return new ResponseEntity<>("the list of halls is empty", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(hallVos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getHallById(@PathVariable(value = "id") Long hallVoId) {
        HallVo hallVoFound = hallService.getHallById(hallVoId);
        if (hallVoFound == null)
            return new ResponseEntity<>("hall doesn't exist", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(hallVoFound, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchHall(@RequestParam("name") String name, @RequestParam("size") String size,
                                             @RequestParam("tv") Boolean tv, @RequestParam("projector") Boolean projector,
                                             @RequestParam("speakers") Boolean speakers, @RequestParam("mic") Boolean mic) {
        List<HallVo> hallsVo = hallService.search(name, size, tv, projector, speakers, mic);
        if (hallsVo == null)
            return new ResponseEntity<>("hall does not exit", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(hallsVo, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createHall(@Valid @RequestBody HallVo hallVo) {
        hallService.save(hallVo);
        return new ResponseEntity<>("hall is created successfully", HttpStatus.CREATED);
    }


    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Object> updateHall(@PathVariable(name = "id") Long hallVoId, @RequestBody HallVo hallVo) {
        HallVo hallVoFound = hallService.getHallById(hallVoId);
        if (hallVoFound == null)
            return new ResponseEntity<>("hall doesn't exist", HttpStatus.NOT_FOUND);
        hallVo.setId(hallVoId);
        hallService.save(hallVo);
        return new ResponseEntity<>("Hall is updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deleteHall(@PathVariable(name = "id") Long hallVoId) {
        HallVo hallVoFound = hallService.getHallById(hallVoId);
        if (hallVoFound == null)
            return new ResponseEntity<>("hall doesn't exist", HttpStatus.NOT_FOUND);
        hallService.delete(hallVoId);
        return new ResponseEntity<>("Hall is deleted successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/sort/{fieldName}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<HallVo> sortBy(@PathVariable String fieldName) {
        return hallService.sortBy(fieldName);
    }

    @GetMapping("/page/{pageId}/{size}")
    public List<HallVo> pagination(@PathVariable int pageid, @PathVariable int size) {
        return hallService.findAll(pageid, size);
    }
}
