package ru.levelup.web;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

public class RegistrationForm {
    @Size(min = 4, max = 50, message = "Login should be at least 4 characters length.")
    @Pattern(regexp = "[a-zA-Z-_.0-9]*",
            message = "Only letters, digits, underscore, minus sign and " +
                    " dots are allowed in login.")
    private String login;

    @Size(min = 4, max = 50, message = "Password should be at least 4 characters length.")
    @Pattern(regexp = "[a-zA-Z-_.0-9]*",
            message = "Only letters, digits, underscore, minus sign and " +
                    " dots are allowed in password.")
    private String password;

    private String selectedGroupName;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSelectedGroupName() {
        return selectedGroupName;
    }

    public void setSelectedGroupName(String selectedGroup) {
        this.selectedGroupName = selectedGroup;
    }

    @Override
    public String toString() {
        return "RegistrationForm{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", selectedGroup=" + selectedGroupName +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationForm that = (RegistrationForm) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(selectedGroupName, that.selectedGroupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, selectedGroupName);
    }
}
