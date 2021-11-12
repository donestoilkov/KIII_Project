package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Balloon;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BallonRepository {

    public List<Balloon> findAllBalloons(){
        return DataHolder.balloons;
    }

    public List<Balloon> findAllByNameOrDescription(String text){
        return DataHolder.balloons
                .stream()
                .filter(b -> b.getName().contains(text)|| b.getDescription().contains(text)).collect(Collectors.toList());
    }

    public void deleteById(Long id){
        DataHolder.balloons.removeIf(b->b.getId().equals(id));
    }

    public Balloon save(Balloon balloon){
        DataHolder.balloons.add(balloon);
        return balloon;
    }

    public Optional<Balloon> findById(Long id){
        return DataHolder.balloons
                .stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();
    }

}
