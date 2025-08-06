package game.world.rooms;

public class TownCenterBack extends Room {
    public TownCenterBack() {
        super("town_center_back", "Behind the Town Center",
                "The back area of the town center, where reality seems less stable.");
        this.detailedDescription = "Behind the main square, you find an area that doesn't quite " +
                "match your memories. Buildings shift subtly when you're not looking, and doorways " +
                "lead to rooms that shouldn't exist.";
    }

    @Override
    protected void initializeRoom() {
        examineDescriptions.put("buildings", "The architecture shifts when you're not looking directly at it.");
        examineDescriptions.put("doorways", "Some doors lead to rooms that aren't on any map.");
        examineDescriptions.put("ground", "The cobblestones form patterns that hurt to look at directly.");
        examineDescriptions.put("entrance", "You notice a concealed entrance to underground tunnels.");
    }
}
