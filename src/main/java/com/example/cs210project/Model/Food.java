package com.example.cs210project.Model;

import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.Objects;

public abstract class Food implements Serializable {
    protected String type;
    protected String name;

    protected Food(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public String getName(){return name;}
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

    public boolean contains(ObservableList<Food> foodList){
        for (Food f: foodList) {
            if(this.equals(f))
                return true;

        }
        return false;
    }


}
