package test.vakoms.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name = "";

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @JsonManagedReference(value="role-user")
    private Set<User> users;

    public Role() {
    }

    public Role(String name, User... users) {
        setName(name);
        this.users = Stream.of(users).collect(Collectors.toSet());
        this.users.forEach(x -> x.setRole(this));
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getUsersDetails() {
        StringBuilder allInformation = new StringBuilder();
        for (User user : getUsers()) {
            allInformation.append(user.toString());
        }
        return allInformation.toString();
    }
}
