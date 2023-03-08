package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Balloon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
    
    private String color;
    
    private Integer capacity;

    @ManyToOne
    private Manufacturer manufacturer;

    public Balloon(String name, String description, Manufacturer manufacturer, String color, Integer capacity) {
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    public Balloon() {

    }
}
