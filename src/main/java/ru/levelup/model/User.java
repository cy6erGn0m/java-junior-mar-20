package ru.levelup.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.levelup.db.ColorConverter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column(length = 50, nullable = false, unique = true)
    @Size(min = 4, max = 50, message = "Login should be at least 4 characters length.")
    @Pattern(regexp = "[a-zA-Z-_.0-9]*",
            message = "Only letters, digits, underscore, minus sign and " +
                    " dots are allowed in login.")
    private String login;

    @Column(length = 70, nullable = false)
    @JsonIgnore
    private String encodedPassword;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(nullable = false)
    @Convert(converter = ColorConverter.class)
    private Color color;

    @Column
    @Max(1000000)
    @PositiveOrZero
    private int bonusScore;

    @Transient
    private String notStored;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @ManyToMany(mappedBy = "users")
//    private List<Group> group;
    private Group group;

    @Temporal(TemporalType.TIMESTAMP)
    @PastOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
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

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String password) {
        this.encodedPassword = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
