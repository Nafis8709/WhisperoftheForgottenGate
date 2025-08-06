package game.inventory;

import java.util.ArrayList;
import java.util.List;
import game.items.Item;

public class Inventory {
    private List<Item> items;
    private int maxCapacity;

    public Inventory() {
        this.items = new ArrayList<>();
        this.maxCapacity = 20;
    }

    public boolean addItem(Item item) {
        if (items.size() < maxCapacity) {
            items.add(item);
            return true;
        }
        return false;
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    public Item getItem(String itemName) {
        return items.stream()
                .filter(item -> item.getName().toLowerCase().contains(itemName.toLowerCase()))
                .findFirst()
                .orElse(null);
    }

    public boolean hasItem(String itemName) {
        return getItem(itemName) != null;
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }
    public int getSize() {
        return items.size();
    }
    public int getMaxCapacity() {
        return maxCapacity;
    }
}
