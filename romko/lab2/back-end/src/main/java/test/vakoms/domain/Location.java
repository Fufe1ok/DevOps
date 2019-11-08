package test.vakoms.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name = "";
    @Column(nullable = false)
    private String description = "";

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    @JsonManagedReference(value="car-location")
    private Set<Car> cars = new HashSet<>();

    public Location() {
    }

    public Location(String name, String description, Set<Car> cars) {
        setName(name);
        setDescription(description);
        setCars(cars);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public String getCarsDetails() {
        StringBuilder allInformation = new StringBuilder();
        for (Car car : getCars()) {
            allInformation.append(car.toString());
        }
        return allInformation.toString();
    }
}
