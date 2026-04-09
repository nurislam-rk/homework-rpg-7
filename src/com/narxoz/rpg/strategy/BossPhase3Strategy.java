package com.narxoz.rpg.strategy;

public class BossPhase3Strategy implements CombatStrategy {
    public int calculateDamage(int basePower) { return basePower * 2; }
    public int calculateDefense(int baseDefense) { return 0; }
    public String getName() { return "Desperate"; }
}
