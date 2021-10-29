package mk.finki.ukim.mk.lab.bootstrap;

import mk.finki.ukim.mk.lab.model.Balloon;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Balloon> balloons = new ArrayList<>();

    @PostConstruct
    public void init(){
        balloons.add(new Balloon("Red Balloon","Red Balloon"));
        balloons.add(new Balloon("Green Balloon","Green Balloon"));
        balloons.add(new Balloon("Yellow Balloon","Yellow Balloon"));
        balloons.add(new Balloon("Orange Balloon","Orange Balloon"));
        balloons.add(new Balloon("Purple Balloon","Purple Balloon"));
        balloons.add(new Balloon("Blue Balloon","Blue Balloon"));
        balloons.add(new Balloon("Classic Balloon","Regular White Balloon"));
        balloons.add(new Balloon("Black Balloon","Black Balloon"));
        balloons.add(new Balloon("Multi Color Balloon","Red Green and White Balloon"));
        balloons.add(new Balloon("Confetti Balloon","Balloon with confetti effect"));
    }

}
