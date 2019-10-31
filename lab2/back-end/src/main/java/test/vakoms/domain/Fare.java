package test.vakoms.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "fares")
public class Fare extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String uuid = "";
    @Column(nullable = false)
    private String status = "";

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "fare-user")
    private User user;

    public Fare() {
    }

    public Fare(String uuid, String status, User user) {
        setUuid(uuid);
        setStatus(status);
        setUser(user);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Fare #" + getId() + " {" +
                "uuid='" + getUuid() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", user='" + getUser().getFirstName() + getUser().getLastName() + '\'' +
                "};\n";
    }
}
