package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "shop_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String name;

    private String surname;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user")
    private List<ShoppingCart> carts;

    public User() {
    }

    public User(String username, String name, String surname, LocalDate dateOfBirth, List<ShoppingCart> carts) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.carts = carts;
    }
}
