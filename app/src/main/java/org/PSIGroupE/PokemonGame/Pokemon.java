package org.PSIGroupE.PokemonGame;

public class Pokemon {
    private final String name;
    private final Type type;
    private int hp;
    private final int ap;
    private final int dp;
    private final int sp;
    private final Attack[] attacks;

    public Pokemon(String name, Type type, int hp, int ap, int dp, int sp, Attack[] attacks) {
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.ap = ap;
        this.dp = dp;
        this.sp = sp;
        this.attacks = attacks;
    }

    public void decreaseHp(int amount) {
        if (amount <= hp) {
            hp -= amount;
        } else {
            hp = 0;
        }
    }

    public boolean isConscious() {
        return (hp > 0);
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getHp() {
        return hp;
    }

    public int getAp() {
        return ap;
    }

    public int getDp() {
        return dp;
    }

    public int getSp() {
        return sp;
    }

    public Attack[] getAttacks() {
        return attacks;
    }
}