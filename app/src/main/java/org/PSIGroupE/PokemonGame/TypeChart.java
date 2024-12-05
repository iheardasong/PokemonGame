package org.PSIGroupE.PokemonGame;


import java.util.HashMap;
import java.util.Map;

public class TypeChart {
    private final Map<Type, Map<Type, Double>> typeChart;

    public TypeChart() {
        typeChart = new HashMap<>();
        for (Type type : Type.values()) {
            typeChart.put(type, new HashMap<>());
        }

        typeChart.get(Type.NORMAL).put(Type.ROCK, 0.5);

        typeChart.get(Type.FIRE).put(Type.FIRE, 0.5);
        typeChart.get(Type.FIRE).put(Type.WATER, 0.5);
        typeChart.get(Type.FIRE).put(Type.GRASS, 2.0);
        typeChart.get(Type.FIRE).put(Type.ROCK, 0.5);

        typeChart.get(Type.WATER).put(Type.FIRE, 2.0);
        typeChart.get(Type.WATER).put(Type.WATER, 0.5);
        typeChart.get(Type.WATER).put(Type.GRASS, 0.5);
        typeChart.get(Type.WATER).put(Type.GROUND, 2.0);
        typeChart.get(Type.WATER).put(Type.ROCK, 2.0);

        typeChart.get(Type.GRASS).put(Type.FIRE, 0.5);
        typeChart.get(Type.GRASS).put(Type.WATER, 2.0);
        typeChart.get(Type.GRASS).put(Type.GRASS, 0.5);
        typeChart.get(Type.GRASS).put(Type.GROUND, 2.0);
        typeChart.get(Type.GRASS).put(Type.ROCK, 2.0);

        typeChart.get(Type.ICE).put(Type.FIRE, 0.5);
        typeChart.get(Type.ICE).put(Type.WATER, 0.5);
        typeChart.get(Type.ICE).put(Type.GRASS, 2.0);
        typeChart.get(Type.ICE).put(Type.GROUND, 2.0);

        typeChart.get(Type.GROUND).put(Type.FIRE, 2.0);
        typeChart.get(Type.GROUND).put(Type.GRASS, 0.5);
        typeChart.get(Type.GROUND).put(Type.ROCK, 2.0);

        typeChart.get(Type.ROCK).put(Type.FIRE, 2.0);
        typeChart.get(Type.ROCK).put(Type.GROUND, 0.5);

        typeChart.get(Type.STEEL).put(Type.FIRE, 0.5);
        typeChart.get(Type.STEEL).put(Type.WATER, 0.5);
        typeChart.get(Type.STEEL).put(Type.ROCK, 2.0);
    }

    public double getEffectiveness(Type attackType, Type defenseType) {
        return typeChart.getOrDefault(attackType, new HashMap<>())
                        .getOrDefault(defenseType, 1.0);
    }
}