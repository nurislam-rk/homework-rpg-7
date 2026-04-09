package com.narxoz.rpg.combatant;

public class Hero {

    private final String name;
    private int hp;
    private final int maxHp;
    private int attackPower;
    private int defense;

    private com.narxoz.rpg.strategy.CombatStrategy strategy;

    public Hero(String name, int hp, int attackPower, int defense, com.narxoz.rpg.strategy.CombatStrategy strategy) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attackPower = attackPower;
        this.defense = defense;
        this.strategy = strategy;
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getAttackPower() { return strategy.calculateDamage(attackPower); }
    public int getDefense() { return strategy.calculateDefense(defense); }
    public boolean isAlive() { return hp > 0; }

    public void takeDamage(int amount) { hp = Math.max(0, hp - amount); }

    public void heal(int amount) { hp = Math.min(maxHp, hp + amount); }

    public void setStrategy(com.narxoz.rpg.strategy.CombatStrategy strategy) { this.strategy = strategy; }

    public String getStrategyName() { return strategy.getName(); }
}