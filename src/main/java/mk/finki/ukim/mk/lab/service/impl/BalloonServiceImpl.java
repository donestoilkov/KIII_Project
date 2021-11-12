package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.repository.BallonRepository;
import mk.finki.ukim.mk.lab.repository.ManufacturerRepository;
import mk.finki.ukim.mk.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {

    private final BallonRepository ballonRepository;
    private final ManufacturerRepository manufacturerRepository;

    public BalloonServiceImpl(BallonRepository ballonRepository, ManufacturerRepository manufacturerRepository) {
        this.ballonRepository = ballonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return ballonRepository.findAllBalloons();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return ballonRepository.findAllByNameOrDescription(text);
    }

    @Override
    public void deleteById(Long id) {
        ballonRepository.deleteById(id);
    }

    @Override
    public Balloon save(String name, String description, Long manufacturerId,Long balloonId) {
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId).orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        if(ballonRepository.findById(balloonId).isPresent()) {
            Balloon balloon = ballonRepository.findById(balloonId).get();
            balloon.setName(name);
            balloon.setDescription(description);
            balloon.setManufacturer(manufacturer);
            return balloon;
        }
        Balloon balloon = new Balloon(name, description, manufacturer);
        ballonRepository.save(balloon);
        return balloon;
    }

    @Override
    public Optional<Balloon> findById(Long id) {
        return ballonRepository.findById(id);
    }
}
