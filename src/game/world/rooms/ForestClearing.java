package game.world.rooms;

public class ForestClearing extends Room {
    public ForestClearing() {
        super("forest_clearing", "Forest Clearing",
                "A small clearing bathed in eerie moonlight.");
    }

    @Override
    protected void initializeRoom() {
        examineDescriptions.put("ground", "The earth here seems disturbed, as if something was buried and dug up.");
        examineDescriptions.put("moonlight", "Pale light filters through the canopy above.");
        examineDescriptions.put("tracks", "Strange tracks lead toward a deeper part of the forest.");
    }
}
