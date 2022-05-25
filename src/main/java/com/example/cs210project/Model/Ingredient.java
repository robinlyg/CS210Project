package com.example.cs210project.Model;

import java.io.Serializable;

public class Ingredient extends Food implements Serializable {


    public Ingredient(String type, String name) {
        super(type, name);

    }


    @Override
    public String toString() {
        return "Ingredient{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
