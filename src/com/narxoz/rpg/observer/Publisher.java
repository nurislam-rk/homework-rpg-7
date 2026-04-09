package com.narxoz.rpg.observer;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private final List<GameObserver> observers = new ArrayList<>();
    public void registerObserver(GameObserver observer) { observers.add(observer); }
    public void notifyObservers(GameEvent event) { observers.forEach(o -> o.onEvent(event)); }
}