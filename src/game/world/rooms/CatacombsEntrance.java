package game.world.rooms;

public class CatacombsEntrance extends Room {
    public CatacombsEntrance() {
        super("catacombs_entrance", "Catacombs Entrance",
                "The entrance to ancient burial chambers that pulse with otherworldly energy.");
        this.detailedDescription = "You descend into catacombs far older than the town above. " +
                "The walls are lined with empty alcoves, and the air thrums with an energy that " +
                "makes your skin crawl. Linna seems drawn to something deeper below.";
    }

    @Override
    protected void initializeRoom() {
        examineDescriptions.put("alcoves", "Empty burial niches that once held the town's original inhabitants.");
        examineDescriptions.put("walls", "Ancient stone that predates known civilization in this area.");
        examineDescriptions.put("energy", "The pulsing sensation grows stronger as you go deeper.");
        examineDescriptions.put("stairs", "Stone steps leading down into the heart of the earth.");
    }
}
