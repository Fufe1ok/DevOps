package test.vakoms.dto;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import test.vakoms.domain.Car;
import test.vakoms.domain.Fare;
import test.vakoms.domain.Role;
import test.vakoms.domain.User;
import test.vakoms.presentation.FareController;
import test.vakoms.presentation.CarController;

import java.util.Date;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class UserDTO extends ResourceSupport {
    private User user;

    public UserDTO(User user, Link selfLink) {
        this.user = user;
        add(linkTo(methodOn(CarController.class).getCarsByUserId(user.getId())).withRel("cars"));
        add(linkTo(methodOn(FareController.class).getFaresByUserId(user.getId())).withRel("fares"));
        add(selfLink);
    }

    public Integer getUserId() {
        return user.getId();
    }

    public String getUserFirstName() {
        return user.getFirstName();
    }

    public String getUserLastName() {
        return user.getLastName();
    }

    public String getUserEmail() {
        return user.getEmail();
    }

    public String getUserPassword() {
        return user.getPassword();
    }

    public Role getUserRole() {
        return user.getRole();
    }

    public Set<Car> getUserCars() {
        return user.getCars();
    }

    public Set<Fare> getUserFares() {
        return user.getFares();
    }

    public Date getCreatedAt() {
        return user.getCreatedAt();
    }

    public Date getUpdatedAt() {
        return user.getUpdatedAt();
    }
}
