package mk.finki.ukim.mk.lab.bootstrap;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Balloon> balloons = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();

    @PostConstruct
    public void init(){

        Manufacturer Balloons1 = new Manufacturer("Balloons And More", "USA", "One Bowerman Drive");
        Manufacturer Balloons2 = new Manufacturer("Qualatex", "USA", "5000 E. 29th St North");
        Manufacturer Balloons3 = new Manufacturer("MSR Balloons", "USA", "1020 Hull Street");
        Manufacturer Balloons4 = new Manufacturer("Balloon Fast", "USA", "115-A Industrial Loop");
        Manufacturer Balloons5 = new Manufacturer("Balloons Everywhere", "USA", "5733 E Shields Ave");

        balloons.add(new Balloon("Red Balloon","Red Balloon",Balloons1));
        balloons.add(new Balloon("Green Balloon","Green Balloon",Balloons2));
        balloons.add(new Balloon("Yellow Balloon","Yellow Balloon",Balloons3));
        balloons.add(new Balloon("Orange Balloon","Orange Balloon",Balloons4));
        balloons.add(new Balloon("Purple Balloon","Purple Balloon",Balloons5));
        balloons.add(new Balloon("Blue Balloon","Blue Balloon",Balloons1));
        balloons.add(new Balloon("Classic Balloon","Regular White Balloon",Balloons2));
        balloons.add(new Balloon("Black Balloon","Black Balloon",Balloons3));
        balloons.add(new Balloon("Multi Color Balloon","Red Green and White Balloon",Balloons4));
        balloons.add(new Balloon("Confetti Balloon","Balloon with confetti effect",Balloons5));



        manufacturers.add(Balloons1);
        manufacturers.add(Balloons2);
        manufacturers.add(Balloons3);
        manufacturers.add(Balloons4);
        manufacturers.add(Balloons5);
    }

}
