package com.narxoz.rpg.observer;

import com.narxoz.rpg.combatant.Hero;
import java.util.*;

public class BattleLogger implements GameObserver {
    public void onEvent(GameEvent event) {
        System.out.printf("[LOG] %s from %s: %d%n", event.getType(), event.getSourceName(), event.getValue());
    }
}


