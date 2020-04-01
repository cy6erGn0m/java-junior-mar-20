package ru.levelup.web;

import ru.levelup.model.Group;

import java.util.List;
import java.util.Objects;

public class RegistrationForm {
    private String login;
    private String password;
    private List<Group> groups;
    private Group selectedGroup;

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

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Group getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(Group selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    @Override
    public String toString() {
        return "RegistrationForm{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", groups=" + groups +
                ", selectedGroup=" + selectedGroup +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationForm that = (RegistrationForm) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(groups, that.groups) &&
                Objects.equals(selectedGroup, that.selectedGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, groups, selectedGroup);
    }
}
