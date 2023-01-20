package com.emsi.HallBooking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Hall")
public class Hall implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;
    @Column(nullable = false, length = 60)
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


    @Override
    public String toString() {
        return  "Hall [id=" + id + ", designation=" + name + ", capacity=" + size + ", tv=" + tv + ", projector=" + projector + ", speakers=" + speakers + ", mic=" + mic + ", price=" + price+"]";
    }
}
