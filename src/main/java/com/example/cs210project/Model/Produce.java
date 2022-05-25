package com.example.cs210project.Model;

import java.io.Serializable;
import java.util.Objects;

public class Produce extends Food implements Serializable {



    public Produce(String type, String name) {
        super(type, name);


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produce)) return false;
        Produce produce = (Produce) o;
        return Objects.equals(type, produce.type);
    }

    @Override
    public String toString() {
        return "Produce{" +
                "type='" + type + '\'' + "Name= " + name +
                '}';
    }
}
