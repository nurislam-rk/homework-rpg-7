package com.narxoz.rpg.observer;

import com.narxoz.rpg.combatant.Hero;
import java.util.*;

public class AchievementTracker implements GameObserver {
    private final Set<String> unlocked = new HashSet<>();
    public void onEvent(GameEvent event) {
        switch (event.getType()) {
            case BOSS_DEFEATED -> { if (unlocked.add("Victory!")) System.out.println("Achievement unlocked: Victory!"); }
            case HERO_DIED -> { if (unlocked.add("Sacrifice!")) System.out.println("Achievement unlocked: Sacrifice!"); }
            case ATTACK_LANDED -> { if (event.getValue() > 50 && unlocked.add("Power Hit!")) System.out.println("Achievement unlocked: Power Hit!"); }
        }
    }
}
