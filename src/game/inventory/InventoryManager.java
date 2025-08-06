package game.inventory;

import game.entities.Player;
import game.items.Item;

public class InventoryManager {
    public String displayInventory(Player player) {
        if (player.getInventory().getItems().isEmpty()) {
            return "Your inventory is empty.";
        }

        StringBuilder sb = new StringBuilder("Inventory:\n");
        for (Item item : player.getInventory().getItems()) {
            sb.append("- ").append(item.getName()).append(": ").append(item.getDescription()).append("\n");
        }
        return sb.toString();
    }

    public String useItem(Player player, String itemName) {
        Item item = player.getInventory().getItem(itemName);
        if (item != null) {
            return item.use();
        }
        return "You don't have that item.";
    }
}
