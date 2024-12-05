package org.PSIGroupE.PokemonGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PokemonGame {

    private GameState gameState;
    private Player player1;
    private Player player2;
    private List<String> pokemonList;
    private final Random random;
    private Player winner;

    public PokemonGame() {
        random = new Random();
        createPokemonList();
        gameState = GameState.S0AwaitingTeamSelection;
    }

    public GameState startGame(String[] selectedPokemon) {
        Pokemon[] team1 = createTeam(selectedPokemon);
        Pokemon[] team2 = createRandomTeam();

        player1 = new Player(1, team1);
        player2 = new Player(2, team2);

        gameState = GameState.S1AwaitingAction;
        return gameState;
    }

    private Pokemon[] createTeam(String[] selectedPokemon) {
        if (selectedPokemon.length != 4) {
            throw new IllegalArgumentException("There must be 4 selected Pokemon!");
        }

        Pokemon[] team = new Pokemon[4];
        
        for (int i = 0; i < 4; i++) {
            String pokemonName = selectedPokemon[0];
            if (pokemonName == null) {
                throw new IllegalArgumentException("The selected Pokemon cannot be null!");
            }
            team[i] = PokemonFactory.createPokemon(pokemonName);
        }

        return team;
    }

    private Pokemon[] createRandomTeam() {
        Pokemon[] randomTeam = new Pokemon[4];
        List<String> pool = new ArrayList<>(pokemonList);
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(pool.size());
            String selectedName = pokemonList.get(index);
            randomTeam[i] = PokemonFactory.createPokemon(selectedName);
            pool.remove(index);
        }
        return randomTeam;
    }

    private void createPokemonList() {
        pokemonList = new ArrayList<>();
        pokemonList.add("Blastoise");
        pokemonList.add("Flareon");
        pokemonList.add("Garganacl");
        pokemonList.add("Gigalith");
        pokemonList.add("Hippowdon");
        pokemonList.add("Leafeon");
        pokemonList.add("Sandaconda");
        pokemonList.add("Sceptile");
        pokemonList.add("Typhlosion");
        pokemonList.add("Vaporeon");
    }

    public GameState playAction(int action) {
        if (action > 4) {
            if (action / 10 != 5 || action % 10 > 3) {
                throw new IllegalArgumentException("Invalid action");
            }
        } else if (action < 1) {
            throw new IllegalArgumentException("Invalid action");
        }

        playMoves(action, selectRandomMove());
        return gameState;
    }

    private int selectRandomMove() {
        int randomMove = random.nextInt(5) + 1;
        if (randomMove == 5) {
            int randomOtherPokemon = Integer.parseInt(player2.getConsciousPokemon().get(random.nextInt(player2.getConsciousPokemon().size())));
            randomMove = randomMove * 10 + randomOtherPokemon;
        }
        return randomMove;
    }

    private void playMoves(int move1, int move2) {
        Player fasterPlayer;
        Player slowerPlayer;
        int fasterMove;
        int slowerMove;
        if (player1.getActivePokemon().getSp() > player2.getActivePokemon().getSp()) {
            fasterPlayer = player1;
            fasterMove = move1;
            slowerPlayer = player2;
            slowerMove = move2;
        } else {
            fasterPlayer = player2;
            fasterMove = move2;
            slowerPlayer = player1;
            slowerMove = move1;
        }

        if (fasterMove > 4) {
            fasterPlayer.setActivePokemon(fasterMove % 10);
            // Call a GUI function to inform about Pokemon switch of faster player
        }

        if (slowerMove > 4) {
            slowerPlayer.setActivePokemon(slowerMove % 10);
            // Call a GUI function to inform about Pokemon switch of slower player
        }

        if (fasterMove <= 4) {
            int damage = computeDamage(fasterPlayer.getActivePokemon().getAttacks()[fasterMove - 1], slowerPlayer.getActivePokemon().getType()); // Very simple
            slowerPlayer.getActivePokemon().decreaseHp(damage);
            // Call GUI function to inform about attack used and damage
        }

        if (!slowerPlayer.getActivePokemon().isConscious()) {
            slowerPlayer.faint();
            // Call GUI function to inform about faint

            if (slowerPlayer.canPlay()) {
                if (slowerPlayer == player2) {
                    //Select random next Pokemon if it is the bot
                    slowerPlayer.setActivePokemon(Integer.parseInt(slowerPlayer.getConsciousPokemon().get(random.nextInt(slowerPlayer.getConsciousPokemon().size()))));
                    // Call GUI method to inform about new Pokemon
                } else {
                    gameState = GameState.S2AwaitingNextPokemon;
                }
                
            } else {
                winner = fasterPlayer;
                gameState = GameState.S3GameOver;
            }

        } else if (slowerMove <= 4) {
            int damage = slowerPlayer.getActivePokemon().getAttacks()[slowerMove - 1].getPower() / 4; // Very simple
            fasterPlayer.getActivePokemon().decreaseHp(damage);
            // Call GUI function to inform about attack used and damage
        }

        if (!fasterPlayer.getActivePokemon().isConscious()) {
            fasterPlayer.faint();
            // Call GUI function to inform about faint

            if (fasterPlayer.canPlay()) {
                if (fasterPlayer == player2) {
                    fasterPlayer.setActivePokemon(Integer.parseInt(fasterPlayer.getConsciousPokemon().get(random.nextInt(fasterPlayer.getConsciousPokemon().size()))));
                    // Call GUI method to inform about new Pokemon
                } else {
                    gameState = GameState.S2AwaitingNextPokemon;
                }
            } else {
                winner = slowerPlayer;
                gameState = GameState.S3GameOver;
            }
        }
    }

    private int computeDamage(Attack attack, Type pType) {
        // not implemented yet
    }

    public GameState selectNextPokemon(int next) {
        if (next < 0 || next > 3) {
            throw new IllegalArgumentException("Invalid next Pokemon index");
        }

        if(!player1.getConsciousPokemon().contains(String.valueOf(next))) {
            throw new IllegalArgumentException("Chosen next pokemon is unconscious");
        }

        player1.setActivePokemon(next);
        gameState = GameState.S1AwaitingAction;
        return gameState;
    }

    public List<String> getPokemonList() {
        return pokemonList;
    }

    public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

    public Player getWinner() {
        return winner;
    }
}