package com.gmail.andreyzarazka.domain;

import java.util.List;

public class MusicType extends Model {
    private static final long serialVersionUID = 1260666002442541186L;

    private String musicTypeName;
    private List<User> users;

    public String getMusicTypeName() {
        return musicTypeName;
    }

    public void setMusicTypeName(String musicTypeName) {
        if (musicTypeName == null || musicTypeName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.musicTypeName = musicTypeName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
