package mk.finki.ukim.mk.lab.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ManufacturerNotFoundException extends RuntimeException {
    public ManufacturerNotFoundException(Long id) {
        super("Manufacturer with ID: " + id + " not found.");
    }
}
