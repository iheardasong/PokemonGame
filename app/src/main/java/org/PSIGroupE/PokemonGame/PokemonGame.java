/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.PSIGroupE.PokemonGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


/**
 *
 * @author Robert Sonnenburg
 */
public class PokemonGame {

    private Player player1;
    private Player player2;
    private List<String> pokemonList;
    private final Scanner scanner;
    private final Random random;
    private boolean gameOver;
    private Player winner;
    
    private PokemonGame() {
        scanner = new Scanner(System.in);
        random = new Random();
        createPokemonList();
        gameOver = false;
    }

    private void start() {
        Pokemon[] team1 = selectTeam();
        Pokemon[] team2 = selectRandomTeam();
        
        player1 = new Player(1, team1);
        player2 = new Player(2, team2);

        while(!gameOver) {
            playRound();
        }

        if (winner == player1) {
            System.out.println("You won!");
        } else {
            System.out.println("You lost!");
        }
    }

    private Pokemon[] selectTeam() {
        Pokemon[] team = new Pokemon[4];
        boolean selectionValid = false;
        while (selectionValid == false) {
            System.out.println("Please select 4 Pokemon (e.g. 1,2,4,7):");
            for (int i = 0; i < pokemonList.size(); i++) {
                System.out.println( (i+1) + ". " + pokemonList.get(i));
            }

            String selection = scanner.next();
            String[] selectedPokemonStr = selection.split(",");
            if (selectedPokemonStr.length != 4) {
                System.out.println("Please enter exactly 4 integers separated by commas.");
                continue;
            }
            
            int[] selectedPokemonInt = new int[4];

            try {
                for (int i = 0 ; i < 4; i++) {
                    selectedPokemonInt[i] = Integer.parseInt(selectedPokemonStr[i]);
                    if (selectedPokemonInt[i] < 1 || selectedPokemonInt[i] > 10) {
                        throw new IllegalArgumentException("Selection not in range");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please ensure all entries are integers.");
                continue;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Selection not in range.");
                continue;
            }

            selectionValid = true;

            for (int i = 0; i < 4; i++) {
                String selectedName = pokemonList.get(selectedPokemonInt[i] - 1);
                team[i] = PokemonFactory.createPokemon(selectedName);
            }
        }
        return team;
    }

    private Pokemon[] selectRandomTeam() {
        Pokemon[] randomTeam = new Pokemon[4];
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(10);
            String selectedName = pokemonList.get(index);
            randomTeam[i] = PokemonFactory.createPokemon(selectedName);
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

    private void playRound() {
        int move1 = selectMove();
        int move2 = selectRandomMove();
        playMoves(move1, move2);
        if (!player1.canPlay()) {
            gameOver = true;
            winner = player2;
        } else if (!player2.canPlay()) {
            gameOver = true;
            winner = player1;
        }
    }

    private int selectMove() {
        boolean inputValid = false;
        int selection = 0;
        while (!inputValid) {
            System.out.println();
            System.out.println("Opponent Pokemon: " + player2.getActivePokemon().getName());
            System.out.println("HP: " + player2.getActivePokemon().getHp());
            System.out.println("Your Pokemon: " + player1.getActivePokemon().getName());
            System.out.println("HP: " + player1.getActivePokemon().getHp());
            System.out.println();
            System.out.println("What do you want to do?");
            Attack[] attacks1 = player1.getActivePokemon().getAttacks();
            System.out.println("1. " + attacks1[0].getName());
            System.out.println("2. " + attacks1[1].getName());
            System.out.println("3. " + attacks1[2].getName());
            System.out.println("4. " + attacks1[3].getName());
            System.out.println("5. Switch Pokemon");
            try {
                selection = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter an integer");
                continue;
            }
            if (selection < 1 || selection > 5) {
                System.out.println("Invalid Selection");
                continue;
            }
            inputValid = true;
        }

        if (selection == 5) {
            int pokemonToSwitchTo = selectSwitch();
            selection = selection * 10 + pokemonToSwitchTo;
        }

        return selection;
    }

    private int selectSwitch() {
        int selection = 0;
        boolean inputValid = false;
        while (!inputValid) {
            System.out.println("Select Pokemon to switch to:");
            System.out.println("1. " + player1.getTeam()[0].getName()); // TODO: show only conscious Pokemon
            System.out.println("2. " + player1.getTeam()[1].getName());
            System.out.println("3. " + player1.getTeam()[2].getName());
            System.out.println("4. " + player1.getTeam()[3].getName());
            try {
                selection = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter an integer");
                continue;
            }
            if (selection < 1 || selection > 4) {
                System.out.println("Invalid Selection");
                continue;
            }
            inputValid = true;
        }
        return selection - 1;
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
            System.out.println("Player " + fasterPlayer.getId() + " switched to " + fasterPlayer.getActivePokemon().getName());
        }

        if (slowerMove > 4) {
            slowerPlayer.setActivePokemon(slowerMove % 10);
            System.out.println("Player " + slowerPlayer.getId() + " switched to " + slowerPlayer.getActivePokemon().getName());
        }

        if (fasterMove <= 4) {
            int damage = fasterPlayer.getActivePokemon().getAttacks()[fasterMove - 1].getPower() / 4; // Very simple
            slowerPlayer.getActivePokemon().decreaseHp(damage);
            System.out.println(fasterPlayer.getActivePokemon().getName() + " used "
                        + fasterPlayer.getActivePokemon().getAttacks()[fasterMove - 1].getName());
            System.out.println(slowerPlayer.getActivePokemon().getName() + " lost " + damage + " HP.");
        }

        if (!slowerPlayer.getActivePokemon().isConscious()) {
            slowerPlayer.faint();
            System.out.println(slowerPlayer.getActivePokemon().getName() + " fainted.");
            if (slowerPlayer.canPlay()) {
                //Sorry  for the following abomination haha
                // Basically, it selects a random conscious Pokemon as the new active Pokemon
                slowerPlayer.setActivePokemon(Integer.parseInt(slowerPlayer.getConsciousPokemon().get(random.nextInt(slowerPlayer.getConsciousPokemon().size()))));
                System.out.println("Player " + slowerPlayer.getId() + " put " + slowerPlayer.getActivePokemon().getName() + " into battle.");
            } else {
                gameOver = true;
                winner = fasterPlayer;
                return;
            }

        } else if (slowerMove <= 4) {
            int damage = slowerPlayer.getActivePokemon().getAttacks()[slowerMove - 1].getPower() / 4; // Very simple
            fasterPlayer.getActivePokemon().decreaseHp(damage);
            System.out.println(slowerPlayer.getActivePokemon().getName() + " used "
                        + slowerPlayer.getActivePokemon().getAttacks()[slowerMove - 1].getName());
            System.out.println(fasterPlayer.getActivePokemon().getName() + " lost " + damage + " HP.");
        }

        if (!fasterPlayer.getActivePokemon().isConscious()) {
            fasterPlayer.faint();
            System.out.println(fasterPlayer.getActivePokemon().getName() + " fainted.");
            if (fasterPlayer.canPlay()) {
                fasterPlayer.setActivePokemon(Integer.parseInt(fasterPlayer.getConsciousPokemon().get(random.nextInt(fasterPlayer.getConsciousPokemon().size()))));
                System.out.println("Player " + fasterPlayer.getId() + " put " + fasterPlayer.getActivePokemon().getName() + " into battle.");
            } else {
                gameOver = true;
                winner = slowerPlayer;
            }
        }
    }

    public static void main(String[] args) {
        new PokemonGame().start();
    }
}
