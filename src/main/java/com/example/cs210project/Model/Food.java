package com.example.cs210project.Model;

import java.io.Serializable;
import java.util.Objects;

public abstract class Food implements Serializable {
    protected String type;

    protected Food(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Food)) return false;
        Food food = (Food) o;
        return Objects.equals(type, food.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
