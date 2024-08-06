package de.example;

import java.util.Objects;

public class User {

    private String username;
    private int score = 0;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getScore() == user.getScore() && Objects.equals(getUsername(), user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getScore());
    }

    public void incrementScore(int score){
        this.score += score;
    }
}
