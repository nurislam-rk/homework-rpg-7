package com.narxoz.rpg.strategy;

public class OffensiveStrategy implements CombatStrategy {
    public int calculateDamage(int basePower) { return (int)(basePower * 1.5); }
    public int calculateDefense(int baseDefense) { return (int)(baseDefense * 0.5); }
    public String getName() { return "Offensive"; }
}



