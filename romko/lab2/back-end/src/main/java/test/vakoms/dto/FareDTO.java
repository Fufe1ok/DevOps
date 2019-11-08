package test.vakoms.dto;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import test.vakoms.domain.Fare;
import test.vakoms.domain.User;

import java.util.Date;

public class FareDTO extends ResourceSupport {
    private Fare fare;

    public FareDTO(Fare fare, Link selfLink) {
        this.fare = fare;
        add(selfLink);
    }

    public Integer getFareId() {
        return fare.getId();
    }

    public String getFareUuid() {
        return fare.getUuid();
    }

    public String getFareStatus() {
        return fare.getStatus();
    }

    public User getFareUser() {
        return fare.getUser();
    }

    public Date getCreatedAt() {
        return fare.getCreatedAt();
    }

    public Date getUpdatedAt() {
        return fare.getUpdatedAt();
    }
}
