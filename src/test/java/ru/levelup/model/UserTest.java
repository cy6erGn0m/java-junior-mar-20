package ru.levelup.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
    @Test
    public void getX() {
        new User().getX();
    }

    @Test
    public void getX2() {
        int actual = new User().getX();
        assertEquals(
                "getX should return 999",
                999,
                actual
        );
    }

    @Test
    public void getX3() {
    }
}