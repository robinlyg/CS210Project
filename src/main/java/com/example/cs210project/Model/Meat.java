package com.example.cs210project.Model;

import java.io.Serializable;

public class Meat extends Food implements Serializable {


    public Meat(String type) {
        super(type, type);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meat)) return false;
        Meat meat = (Meat) o;
        return type.equals(meat.type);
    }

    @Override
    public String toString() {
        return "Meat{" +
                "type='" + type + '\'' +
                '}';
    }
}