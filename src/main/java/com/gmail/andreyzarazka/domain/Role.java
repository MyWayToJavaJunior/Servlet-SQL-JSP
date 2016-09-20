package com.gmail.andreyzarazka.domain;

import java.util.List;

public class Role extends Model {
    private static final long serialVersionUID = -8265247215031702285L;

    private String roleName;
    private List<User> users;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        if (roleName == null || roleName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
