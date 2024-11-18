package org.PSIGroupE.PokemonGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private final int id;
    private final Pokemon[] team;
    private int activePokemonIndex;
    private final Random random;
    private final List<String> consciousPokemon;

    public Player (int id, Pokemon[] team) {
        random = new Random();
        this.id = id;
        this.team = team;
        activePokemonIndex = random.nextInt(4); // Select a random first Pokemon
        consciousPokemon = new ArrayList<>();
        consciousPokemon.add("0");
        consciousPokemon.add("1");
        consciousPokemon.add("2");
        consciousPokemon.add("3");
    }

    public boolean canPlay() {
        return !consciousPokemon.isEmpty();
    }

    public void faint() {
        consciousPokemon.remove(String.valueOf(activePokemonIndex));
    }

    public Pokemon getActivePokemon() {
        return team[activePokemonIndex];
    }

    public List<String> getConsciousPokemon() {
        return consciousPokemon;
    }

    public void setActivePokemon(int i) {
        activePokemonIndex = i;
    }

    public int getId() {
        return id;
    }

    public Pokemon[] getTeam() {
        return team;
    }
}