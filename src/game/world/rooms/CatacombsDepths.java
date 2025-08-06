package game.world.rooms;

public class CatacombsDepths extends Room {
    public CatacombsDepths() {
        super("catacombs_depths", "Catacombs Depths",
                "The deepest level of the catacombs, where reality begins to break down.");
        this.detailedDescription = "At the bottom of the catacombs, the very air seems to warp " +
                "and twist. Linna stands transfixed, staring at something only she can see. " +
                "The walls pulse with an alien rhythm, and you can hear whispers in languages " +
                "that predate human civilization.";
    }

    @Override
    protected void initializeRoom() {
        examineDescriptions.put("walls", "The stone walls pulse like living tissue.");
        examineDescriptions.put("whispers", "Voices speaking in tongues that human minds weren't meant to understand.");
        examineDescriptions.put("reality", "The very fabric of existence seems thin here.");
        examineDescriptions.put("chamber", "At the far end, you see the source of all the strangeness.");
    }
}
