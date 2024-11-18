package org.PSIGroupE.PokemonGame;

public class Attack {
    final private String name;
    final private int power;
    final private Type type;

    public Attack(String name, int power, Type type) {
        this.name = name;
        this.power = power;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public Type getType() {
        return type;
    }
}