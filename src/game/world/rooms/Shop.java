package game.world.rooms;

import game.entities.Elias;

public class Shop extends Room {
    public Shop() {
        super("shop", "General Store",
                "A well-stocked general store with shelves full of various supplies.");
    }

    @Override
    protected void initializeRoom() {
        addNPC(new Elias());

        examineDescriptions.put("shelves", "The shelves are packed with tools, food, and other supplies.");
        examineDescriptions.put("counter", "A wooden counter where Elias conducts business.");
        examineDescriptions.put("floor", "The wooden floorboards creak slightly under your feet.");
    }
}
