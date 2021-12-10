package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String balloonColor;

    private String balloonSize;

    private String username;

    public Order(String balloonColor, String balloonSize, String username) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.username = username;
    }

    public Order() {

    }
}


