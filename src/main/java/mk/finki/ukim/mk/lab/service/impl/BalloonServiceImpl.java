package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.repository.BallonRepository;
import mk.finki.ukim.mk.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalloonServiceImpl implements BalloonService {

    private final BallonRepository ballonRepository;

    public BalloonServiceImpl(BallonRepository ballonRepository) {
        this.ballonRepository = ballonRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return ballonRepository.findAllBalloons();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return ballonRepository.findAllByNameOrDescription(text);
    }
}
