package game.world.rooms;

public class ForestEntrance extends Room {
    public ForestEntrance() {
        super("forest_entrance", "Forest Entrance",
                "The edge of a dark, mysterious forest. Ancient trees loom overhead.");
    }

    @Override
    protected void initializeRoom() {
        examineDescriptions.put("trees", "Massive, ancient trees that seem to whisper in the wind.");
        examineDescriptions.put("path", "A winding path disappears into the forest depths.");
    }
}
