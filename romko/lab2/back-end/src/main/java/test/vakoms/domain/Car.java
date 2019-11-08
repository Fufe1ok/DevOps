package test.vakoms.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name = "";
    @Column(nullable = false)
    private String type = "";
    @Column(nullable = false)
    private String registrationNumber = "";
    @Column(nullable = false)
    private String colour = "";

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value="car-user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonBackReference(value="car-location")
    private Location location;

    public Car() {
    }

    public Car(String name, String type, String registrationNumber, String colour, User user, Location location) {
        setName(name);
        setType(type);
        setRegistrationNumber(registrationNumber);
        setColour(colour);
        setUser(user);
        setLocation(location);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Car #" + getId() + " {" +
                "name='" + getName() + '\'' +
                ", type='" + getType() + '\'' +
                ", registrationNumber='" + getRegistrationNumber() + '\'' +
                ", colour='" + getColour() + '\'' +
                "};\n";
    }
}
