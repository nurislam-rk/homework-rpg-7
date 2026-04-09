package com.narxoz.rpg.engine;

import com.narxoz.rpg.combatant.*;
import com.narxoz.rpg.observer.*;

import java.util.List;

public class DungeonEngine {

    private final List<Hero> heroes;
    private final DungeonBoss boss;
    private final Publisher publisher;
    private final int maxRounds;

    public DungeonEngine(List<Hero> heroes, DungeonBoss boss, Publisher publisher, int maxRounds) {
        this.heroes = heroes;
        this.boss = boss;
        this.publisher = publisher;
        this.maxRounds = maxRounds;
    }

    public EncounterResult runEncounter() {
        int round = 0;

        while (round < maxRounds && boss.isAlive() && heroes.stream().anyMatch(Hero::isAlive)) {
            round++;
            System.out.println("\n=== Round " + round + " ===");
            for (Hero hero : heroes) {
                if (!hero.isAlive()) continue;
                int damage = Math.max(0, hero.getAttackPower() - boss.getDefense());
                boss.takeDamage(damage);
                publisher.notifyObservers(new GameEvent(GameEventType.ATTACK_LANDED, hero.getName(), damage));
                if (hero.getHp() < hero.getMaxHp() * 0.3) {
                    publisher.notifyObservers(new GameEvent(GameEventType.HERO_LOW_HP, hero.getName(), hero.getHp()));
                }
                if (!hero.isAlive()) {
                    publisher.notifyObservers(new GameEvent(GameEventType.HERO_DIED, hero.getName(), 0));
                }
            }
            for (Hero hero : heroes) {
                if (!hero.isAlive()) continue;

                int damage = boss.attack(hero);
                if (hero.getHp() < hero.getMaxHp() * 0.3) {
                    publisher.notifyObservers(new GameEvent(GameEventType.HERO_LOW_HP, hero.getName(), hero.getHp()));
                }
                if (!hero.isAlive()) {
                    publisher.notifyObservers(new GameEvent(GameEventType.HERO_DIED, hero.getName(), 0));
                }
            }
        }
        int survivors = (int) heroes.stream().filter(Hero::isAlive).count();

        return new EncounterResult(boss.isAlive() ? false : true, round, survivors);
    }
}

