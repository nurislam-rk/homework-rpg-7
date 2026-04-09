package com.narxoz.rpg;

import com.narxoz.rpg.combatant.*;
import com.narxoz.rpg.engine.*;
import com.narxoz.rpg.observer.*;
import com.narxoz.rpg.strategy.*;

import java.util.Arrays;

public class Main {
    

    public static void main(String[] args) {

        Hero h1 = new Hero("Arthas", 120, 30, 10, new OffensiveStrategy());
        Hero h2 = new Hero("Jaina", 100, 25, 12, new DefensiveStrategy());
        Hero h3 = new Hero("Thrall", 110, 28, 11, new BalancedStrategy());

        DungeonBoss boss = new DungeonBoss("Mal'Ganis", 300, 20, 8);

        Publisher publisher = new Publisher();

        BattleLogger logger = new BattleLogger();
        AchievementTracker tracker = new AchievementTracker();
        PartySupport support = new PartySupport(Arrays.asList(h1, h2, h3));
        HeroStatusMonitor monitor = new HeroStatusMonitor(Arrays.asList(h1, h2, h3));
        LootDropper loot = new LootDropper();

        publisher.registerObserver(logger);
        publisher.registerObserver(tracker);
        publisher.registerObserver(support);
        publisher.registerObserver(monitor);
        publisher.registerObserver(loot);

        publisher.registerObserver(boss);

        DungeonEngine engine = new DungeonEngine(Arrays.asList(h1, h2, h3), boss, publisher, 50);
        EncounterResult result = engine.runEncounter();

        System.out.printf("Encounter finished in %d rounds. Heroes won: %s. Survivors: %d%n",
                result.getRoundsPlayed(),
                result.isHeroesWon(),
                result.getSurvivingHeroes());
    }
}