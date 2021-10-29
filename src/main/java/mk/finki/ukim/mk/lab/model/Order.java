package mk.finki.ukim.mk.lab.model;

import lombok.Data;

@Data
public class Order {

    private String balloonColor;
    private String balloonSize;
    private String clientName;
    private String clientAddress;
    private Long orderId;

    public Order(String balloonColor, String balloonSize, String clientName, String clientAddress, Long orderId) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return String.format("Client Name:%s Client Address: %s Balloon Color:%s Balloon Size:%s",this.clientName,this.clientAddress, this.balloonColor, this.balloonSize);
    }
}
