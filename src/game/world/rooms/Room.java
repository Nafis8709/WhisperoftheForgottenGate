package game.world.rooms;

import game.entities.NPC;
import game.items.Item;
import java.util.*;

public abstract class Room {

    protected String id;
    protected String name;
    protected String description;
    protected String detailedDescription;
    protected Map<String, Room> exits;
    protected List<Item> items;
    protected List<NPC> npcs;
    protected boolean visited;
    protected Map<String, String> examineDescriptions;

    public Room(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
        this.npcs = new ArrayList<>();
        this.visited = false;
        this.examineDescriptions = new HashMap<>();
        initializeRoom();
    }

    protected abstract void initializeRoom();

    public void addExit(String direction, Room room) {
        exits.put(direction.toLowerCase(), room);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void addNPC(NPC npc) {
        npcs.add(npc);
    }

    public void removeNPC(NPC npc) {
        npcs.remove(npc);
    }

    public String getFullDescription() {
        StringBuilder sb = new StringBuilder();

        if (!visited) {
            visited = true;
            sb.append(detailedDescription != null ? detailedDescription : description);
        } else {
            sb.append(description);
        }

        return sb.toString();
    }

    // Getters
    public String getId() {
        return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getDetailedDescription() { return detailedDescription; }
    public Map<String, Room> getExits() { return exits; }
    public List<Item> getItems() {
        return items;
    }
    public List<NPC> getNpcs() {
        return npcs;
    }
    public boolean isVisited() {
        return visited;
    }
    public Map<String, String> getExamineDescriptions() {
        return examineDescriptions;
    }

    // For backward compatibility
    public List<NPC> getNPCs() {
        return npcs;
    }

}
