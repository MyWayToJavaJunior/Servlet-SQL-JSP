package com.gmail.andreyzarazka.domain;

import java.io.Serializable;

public abstract class Model implements Serializable {
    private static final long serialVersionUID = -1919486886179876612L;

    private int id;

    public Model() {
    }

    public Model(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
