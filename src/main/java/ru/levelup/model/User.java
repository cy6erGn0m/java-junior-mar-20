package ru.levelup.model;

import ru.levelup.db.ColorConverter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column(length = 50, nullable = false, unique = true)
    private String login;

    @Column(length = 50, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(nullable = false)
    @Convert(converter = ColorConverter.class)
    private Color color;

    @Transient
    private String notStored;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @ManyToMany(mappedBy = "users")
//    private List<Group> group;
    private Group group;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate = new Date();

    public int getX() {
        return 999;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
