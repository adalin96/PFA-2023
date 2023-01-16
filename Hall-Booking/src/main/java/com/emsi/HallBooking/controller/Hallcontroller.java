package com.emsi.HallBooking.controller;

import com.emsi.HallBooking.domaine.HallVo;
import com.emsi.HallBooking.service.IService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class Hallcontroller {
    private IService service;

    @RequestMapping("/")
    public String showWelcomeFile(Model model) {
        return "index";
    }

    @RequestMapping("/hallform")
    public String showform(Model model) {
        model.addAttribute("empVo", new HallVo());
        return "hallform";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("hallVo") HallVo emp) {
        service.save(emp);
        return "redirect:/viewemp";// will redirect to viewhall request mapping
    }

    @RequestMapping("/viewhall")
    public String viewhall(Model model) {
        List<HallVo> list = service.getHalls();
        model.addAttribute("list", list);
        return "viewhall";
    }

    @RequestMapping(value = "/edithall/{id}")
    public String edit(@PathVariable Long id, Model m) {
        HallVo emp = service.getHallById(id);
        m.addAttribute("hallVo", emp);
        return "halleditform";
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String editsave(@ModelAttribute("hallVo") HallVo hall) {
        service.save(hall);
        return "redirect:/viewhall";
    }

    @RequestMapping(value = "/deletehall/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/viewhall";
    }

    @RequestMapping("/name/{name}")
    public String getBySalary(@PathVariable String name, Model m) {
        List<HallVo> list = service.findByName(name);
        m.addAttribute("list", list);
        return "viewhall";
    }

    @RequestMapping("/size/{size}")
    public String getBySize(@PathVariable String size, Model m) {
        List<HallVo> list = service.findBySize(size);
        m.addAttribute("list", list);
        return "viewhall";
    }

    @RequestMapping("/tv/{tv}")
    public String getByTv(@PathVariable Boolean tv, Model m) {
        List<HallVo> list = service.findByTv(tv);
        m.addAttribute("list", list);
        return "viewhall";
    }

    @RequestMapping("/projector/{projector}")
    public String getByProjector(@PathVariable Boolean projector, Model m) {
        List<HallVo> list = service.findByProjector(projector);
        m.addAttribute("list", list);
        return "viewhall";
    }

    @RequestMapping("/speakers/{speakers}")
    public String getBySpeakers(@PathVariable Boolean speakers, Model m) {
        List<HallVo> list = service.findBySpeakers(speakers);
        m.addAttribute("list", list);
        return "viewhall";
    }

    @RequestMapping("/mic/{mic}")
    public String getByMic(@PathVariable Boolean mic, Model m) {
        List<HallVo> list = service.findByMic(mic);
        m.addAttribute("list", list);
        return "viewhall";
    }

    @RequestMapping("/multiple_search/{multiple_search}")
    public String getSearch(@PathVariable String name, String size, Boolean tv, Boolean projector, Boolean speakers, Boolean mic, Double price, Model m) {
        List<HallVo> list = service.search(name,  size, tv,  projector, speakers, mic);
        m.addAttribute("list", list);
        return "viewhall";
    }

    @RequestMapping("/pagination/{pageid}/{size}")
    public String pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
        List<HallVo> list = service.findAll(pageid, size);
        m.addAttribute("list", list);
        return "viewemp";
    }

    @RequestMapping("/sort/{fieldName}")
    public String sortBy(@PathVariable String fieldName, Model m) {
        List<HallVo> list = service.sortBy(fieldName);
        m.addAttribute("list", list);
        return "viewemp";
    }
}
