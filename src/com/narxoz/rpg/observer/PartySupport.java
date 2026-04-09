package com.narxoz.rpg.observer;

import com.narxoz.rpg.combatant.Hero;
import java.util.*;

public class PartySupport implements GameObserver {
    private final List<Hero> heroes; private final Random random = new Random();
    public PartySupport(List<Hero> heroes) { this.heroes = heroes; }
    public void onEvent(GameEvent event) {
        if (event.getType() == GameEventType.HERO_LOW_HP) {
            List<Hero> alive = heroes.stream().filter(Hero::isAlive).toList();
            if (!alive.isEmpty()) {
                Hero ally = alive.get(random.nextInt(alive.size()));
                ally.heal(20);
                System.out.println("[PartySupport] Healed " + ally.getName() + " for 20 HP");
            }
        }
    }
}
