package org.PSIGroupE.PokemonGame;

import java.util.HashMap;
import java.util.Map;


public class PokemonFactory {
    private static final Map<String, Attack[]> attackInfo = new HashMap<>();

    static {
        attackInfo.put("Blastoise", new Attack[] {
            new Attack("Hydro Pump", 110, Type.WATER),
            new Attack("Surf", 90, Type.WATER),
            new Attack("Water Gun", 40, Type.WATER),
            new Attack("Ice Beam", 90, Type.ICE)
        });

        attackInfo.put("Flareon", new Attack[] {
            new Attack("Flare Blitz", 12, Type.FIRE),
            new Attack("Fire Fang", 65, Type.FIRE),
            new Attack("Lava Plume", 80, Type.FIRE),
            new Attack("Overheat", 130, Type.FIRE)
        });

        attackInfo.put("Garganacl", new Attack[] {
            new Attack("Stone Edge", 100, Type.ROCK),
            new Attack("Rock Slide", 75, Type.ROCK),
            new Attack("Earthquake", 100, Type.GROUND),
            new Attack("Heavy Slam", 75, Type.STEEL)
        });

        attackInfo.put("Gigalith", new Attack[] {
            new Attack("Stone Edge", 100, Type.ROCK),
            new Attack("Rock Blast", 75, Type.ROCK),
            new Attack("Earthquake", 100, Type.GROUND),
            new Attack("Iron Head", 80, Type.STEEL)
        });

        attackInfo.put("Hippowdon", new Attack[] {
            new Attack("Earthquake", 100, Type.GROUND),
            new Attack("Crunch", 80, Type.DARK),
            new Attack("Body Slam", 85, Type.NORMAL),
            new Attack("Rock Slide", 75, Type.ROCK)
        });

        attackInfo.put("Leafeon", new Attack[] {
            new Attack("Leaf Blade", 90, Type.GRASS),
            new Attack("Razor Leaf", 55, Type.GRASS),
            new Attack("Solar Blade", 125, Type.GRASS),
            new Attack("Quick Attack", 40, Type.NORMAL)
        });

        attackInfo.put("Sandaconda", new Attack[] {
            new Attack("Earthquake", 100, Type.GROUND),
            new Attack("Sand Tomb", 35, Type.GROUND),
            new Attack("Iron Head", 80, Type.STEEL),
            new Attack("Body Slam", 85, Type.NORMAL)
        });

        attackInfo.put("Sceptile", new Attack[] {
            new Attack("Leaf Blade", 90, Type.GRASS),
            new Attack("Energy Ball", 90, Type.GRASS),
            new Attack("Bullet Seed", 75, Type.GRASS),
            new Attack("Dragon Claw", 80, Type.DRAGON)
        });

        attackInfo.put("Typhlosion", new Attack[] {
            new Attack("Flamethrower", 90, Type.FIRE),
            new Attack("Overheat", 130, Type.FIRE),
            new Attack("Lava Plume", 80, Type.FIRE),
            new Attack("Eruption", 130, Type.FIRE)
        });

        attackInfo.put("Vaporeon", new Attack[] {
            new Attack("Hydro Pump", 110, Type.WATER),
            new Attack("Water Pulse", 60, Type.WATER),
            new Attack("Aurora Beam", 65, Type.ICE),
            new Attack("Ice Beam", 90, Type.ICE)
        });
    }

    public static Pokemon createPokemon(String name) {
        Attack[] attacks = attackInfo.getOrDefault(name, new Attack[] {});
        switch (name) {
            case "Blastoise" -> {
                return new Pokemon("Blastoise", Type.WATER, 79, 83, 100, 78, attacks);
            }
            case "Flareon" -> {
                return new Pokemon("Flareon", Type.FIRE, 65, 130, 60, 65, attacks);
            }
            case "Garganacl" -> {
                return new Pokemon("Garganacl", Type.ROCK, 100, 100, 130, 35, attacks);
            }
            case "Gigalith" -> {
                return new Pokemon("Gigalith", Type.ROCK, 85, 135, 130, 25, attacks);
            }
            case "Hippowdon" -> {
                return new Pokemon("Hippowdon", Type.GROUND, 108, 112, 118, 47, attacks);
            }
            case "Leafeon" -> {
                return new Pokemon("Leafeon", Type.GRASS, 65, 110, 130, 95, attacks);
            }
            case "Sandaconda" -> {
                return new Pokemon("Sandaconda", Type.GROUND, 72, 107, 125, 71, attacks);
            }
            case "Sceptile" -> {
                return new Pokemon("Sceptile", Type.GRASS, 70, 85, 65, 120, attacks);
            }
            case "Typhlosion" -> {
                return new Pokemon("Typhlosion", Type.FIRE, 78, 84, 78, 100, attacks);
            }
            case "Vaporeon" -> {
                return new Pokemon("Vaporeon", Type.WATER, 130, 65, 60, 65, attacks);
            }
            default -> throw new IllegalArgumentException("Unknown Pokémon: " + name);
        }
    }
}