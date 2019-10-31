package test.vakoms.dto;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import test.vakoms.domain.Car;
import test.vakoms.domain.Location;
import test.vakoms.domain.User;

import java.util.Date;

public class CarDTO extends ResourceSupport {
    private Car car;

    public CarDTO(Car car, Link selfLink) {
        this.car = car;
        add(selfLink);
    }

    public Integer getCarId() {
        return car.getId();
    }

    public String getCarName() {
        return car.getName();
    }

    public String getCarType() {
        return car.getType();
    }

    public String getCarRegistrationNumber() {
        return car.getRegistrationNumber();
    }

    public String getCarColour() {
        return car.getColour();
    }

    public User getCarUser() {
        return car.getUser();
    }

    public Location getCarLocation() {
        return car.getLocation();
    }

    public Date getCreatedAt() {
        return car.getCreatedAt();
    }

    public Date getUpdatedAt() {
        return car.getUpdatedAt();
    }
}
