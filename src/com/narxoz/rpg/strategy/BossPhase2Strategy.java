package com.narxoz.rpg.strategy;

public class BossPhase2Strategy implements CombatStrategy {
    public int calculateDamage(int basePower) { return (int)(basePower * 1.5); }
    public int calculateDefense(int baseDefense) { return (int)(baseDefense * 0.8); }
    public String getName() { return "Aggressive"; }
}