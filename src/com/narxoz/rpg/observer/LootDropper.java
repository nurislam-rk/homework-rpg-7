package com.narxoz.rpg.observer;

import com.narxoz.rpg.combatant.Hero;
import java.util.*;

public class LootDropper implements GameObserver {
    private final Random random = new Random();
    public void onEvent(GameEvent event) {
        if (event.getType() == GameEventType.BOSS_PHASE_CHANGED || event.getType() == GameEventType.BOSS_DEFEATED) {
            String loot = switch (random.nextInt(3)) {
                case 0 -> "Sword"; case 1 -> "Shield"; default -> "Potion";
            };
            System.out.println("[LootDropper] Dropped loot: " + loot);
        }
    }
}
