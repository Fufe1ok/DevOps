package test.vakoms.dto;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import test.vakoms.domain.Car;
import test.vakoms.domain.Location;
import test.vakoms.presentation.CarController;

import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class LocationDTO extends ResourceSupport {
    private Location location;

    public LocationDTO(Location location, Link selfLink) {
        this.location = location;
        add(selfLink);
        add(linkTo(methodOn(CarController.class).getCarsByLocationId(location.getId())).withRel("cars"));
    }

    public Integer getLocationId() {
        return location.getId();
    }

    public String getLocationName() {
        return location.getName();
    }

    public String getLocationDescription() {
        return location.getDescription();
    }

    public Set<Car> getLocationCars() {
        return location.getCars();
    }
}
