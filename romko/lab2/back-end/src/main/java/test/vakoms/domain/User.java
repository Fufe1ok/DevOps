package test.vakoms.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName = "";
    @Column(nullable = false)
    private String lastName = "";
    @Email
    @Column(nullable = false, unique = true)
    private String email = "";
    @Column(nullable = false)
    private String password = "";

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonBackReference(value="role-user")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference(value="car-user")
    private Set<Car> cars;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference(value="fare-user")
    private Set<Fare> fares = new HashSet<>();

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, Role role) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        setRole(role);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public String getCarsDetails() {
        StringBuilder allInformation = new StringBuilder();
        for (Car car : getCars()) {
            allInformation.append(car.toString());
        }
        return allInformation.toString();
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Set<Fare> getFares() {
        return fares;
    }

    public void setFares(Set<Fare> fares) {
        this.fares = fares;
    }

    public String getFaresDetails() {
        StringBuilder allInformation = new StringBuilder();
        for (Fare fare : getFares()) {
            allInformation.append(fare.toString());
        }
        return allInformation.toString();
    }

    @Override
    public String toString() {
        return "User#" + getId() + " {" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                "};\n";
    }
}
