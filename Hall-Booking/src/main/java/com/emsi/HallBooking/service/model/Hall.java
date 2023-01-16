package com.emsi.HallBooking.service.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Hall implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String size;
    @Column(nullable = false)
    private Boolean tv;
    @Column(nullable = false)
    private Boolean projector;
    @Column(nullable = false)
    private Boolean speakers;
    @Column(nullable = false)
    private Boolean mic;
    @Column(nullable = false)
    private Double price;

    public Hall() {
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean getTv() {
        return tv;
    }

    public void setTv(Boolean tv) {
        this.tv = tv;
    }

    public Boolean getProjector() {
        return projector;
    }

    public void setProjector(Boolean projector) {
        this.projector = projector;
    }

    public Boolean getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Boolean speakers) {
        this.speakers = speakers;
    }

    public Boolean getMic() {
        return mic;
    }

    public void setMic(Boolean mic) {
        this.mic = mic;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hall(String name, String size, Boolean tv, Boolean projector, Boolean speakers, Boolean mic, Double price) {
        this.name = name;
        this.size = size;
        this.tv = tv;
        this.projector = projector;
        this.speakers = speakers;
        this.mic = mic;
        this.price = price;
    }



    @Override
    public String toString() {
        return  "Hall [id=" + id + ", designation=" + name + ", capacity=" + size + ", tv=" + tv + ", projector=" + projector + ", speakers=" + speakers + ", mic=" + mic + ", price=" + price+"]";
    }
}
