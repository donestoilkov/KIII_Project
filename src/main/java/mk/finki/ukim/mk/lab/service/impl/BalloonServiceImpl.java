package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.repository.jpa.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ManufacturerRepository;
import mk.finki.ukim.mk.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {

    private final BalloonRepository ballonRepository;
    private final mk.finki.ukim.mk.lab.repository.jpa.ManufacturerRepository manufacturerRepository;

    public BalloonServiceImpl(BalloonRepository ballonRepository, ManufacturerRepository manufacturerRepository) {
        this.ballonRepository = ballonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return ballonRepository.findAll();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return ballonRepository.findAllByDescription(text);
    }

    @Override
    public void deleteById(Long id) {
        ballonRepository.deleteById(id);
    }

    @Override
    public Balloon save(String name, String description, Long manufacturerId, Long balloonId) {
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId).orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        if (balloonId!=null&&ballonRepository.findById(balloonId).isPresent()) {
            ballonRepository.getById(balloonId).setName(name);
            ballonRepository.getById(balloonId).setDescription(description);
            ballonRepository.getById(balloonId).setManufacturer(manufacturerRepository.getById(manufacturerId));
            return ballonRepository.save(ballonRepository.getById(balloonId));
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
