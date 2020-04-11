package ru.levelup.web;


import ru.levelup.model.User;

public class MyRestResponse {
    private User someone;
    private String title;
    private long usersCount;

    public User getSomeone() {
        return someone;
    }

    public void setSomeone(User someone) {
        this.someone = someone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(long usersCount) {
        this.usersCount = usersCount;
    }
}
