package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Balloon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalloonRepository extends JpaRepository<Balloon, Long> {
    List<Balloon> findAllByDescription(String text);

    List<Balloon> findAllByManufacturer_Country(String country);
    List<Balloon> findAllByManufacturer_Name(String name);

}
