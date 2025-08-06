package game.entities;

import game.inventory.Inventory;

public class Player {
    private String name;
    private Inventory inventory;
    private int health;
    private int maxHealth;
    private boolean hasKey;
    private boolean hasCoin;
    private boolean hasRevolver;

    public Player() {
        this.name = "Unknown";
        this.inventory = new Inventory();
        this.health = 100;
        this.maxHealth = 100;
        this.hasKey = false;
        this.hasCoin = false;
        this.hasRevolver = false;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Inventory getInventory() {
        return inventory;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = Math.min(health, maxHealth);
    }
    public boolean hasKey() {
        return hasKey;
    }
    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }
    public boolean hasCoin() {
        return hasCoin;
    }
    public void setHasCoin(boolean hasCoin) {
        this.hasCoin = hasCoin;
    }
    public boolean hasRevolver() {
        return hasRevolver;
    }
    public void setHasRevolver(boolean hasRevolver) {
        this.hasRevolver = hasRevolver;
    }
}

