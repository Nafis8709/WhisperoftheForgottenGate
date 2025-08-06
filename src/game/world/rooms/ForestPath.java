package game.world.rooms;

public class ForestPath extends Room {
    public ForestPath() {
        super("forest_path", "Forest Path",
                "A winding path through dense forest. Something feels wrong here.");
    }

    @Override
    protected void initializeRoom() {
        examineDescriptions.put("tracks", "Footprints lead deeper into the forest, then suddenly stop.");
        examineDescriptions.put("bushes", "Dense undergrowth that could hide many secrets.");
    }
}
