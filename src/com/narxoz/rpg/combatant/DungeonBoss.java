package com.narxoz.rpg.combatant;

import com.narxoz.rpg.strategy.*;
import com.narxoz.rpg.observer.*;

public class DungeonBoss extends Publisher implements GameObserver {
    private final String name;
    private int hp;
    private final int maxHp;
    private final int attackPower;
    private final int defense;
    private int phase = 1;
    private CombatStrategy strategy;
    private final CombatStrategy phase1 = new BossPhase1Strategy();
    private final CombatStrategy phase2 = new BossPhase2Strategy();
    private final CombatStrategy phase3 = new BossPhase3Strategy();

    public DungeonBoss(String name, int hp, int attackPower, int defense) {
        this.name = name; this.hp = hp; this.maxHp = hp;
        this.attackPower = attackPower; this.defense = defense;
        this.strategy = phase1;
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public boolean isAlive() { return hp > 0; }

    public void takeDamage(int amount) {
        hp = Math.max(0, hp - amount);
        int newPhase = getPhaseByHp();
        if (newPhase != phase) {
            phase = newPhase;
            notifyObservers(new GameEvent(GameEventType.BOSS_PHASE_CHANGED, name, phase));
        }
        if (hp == 0) notifyObservers(new GameEvent(GameEventType.BOSS_DEFEATED, name, 0));
    }

    private int getPhaseByHp() {
        double percent = (hp * 100.0) / maxHp;
        if (percent > 60) return 1;
        else if (percent > 30) return 2;
        else return 3;
    }

    public int getDefense() {
    return strategy.calculateDefense(defense);
}

    public int attack(Hero hero) {
        int damage = Math.max(0, strategy.calculateDamage(attackPower) - hero.getDefense());
        hero.takeDamage(damage);
        notifyObservers(new GameEvent(GameEventType.ATTACK_LANDED, name, damage));
        return damage;
    }

    public void onEvent(GameEvent event) {
        if (event.getType() == GameEventType.BOSS_PHASE_CHANGED && event.getSourceName().equals(name)) {
            switch (event.getValue()) {
                case 1 -> strategy = phase1;
                case 2 -> strategy = phase2;
                case 3 -> strategy = phase3;
            }
        }
    }

    public String getStrategyName() { return strategy.getName(); }
}