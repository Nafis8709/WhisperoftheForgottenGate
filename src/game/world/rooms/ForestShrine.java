package game.world.rooms;

import game.items.ShrineKey;

public class ForestShrine extends Room {
    private boolean puzzleSolved = false;
    public ForestShrine() {
        super("forest_shrine", "Forest Shrine",
                "An ancient stone shrine covered in glowing runes. Strange creatures lurk in the shadows.");
        this.detailedDescription = "You enter a circular clearing dominated by an ancient shrine. " +
                "The stone structure pulses with an otherworldly light, and you can hear faint whispers " +
                "emanating from within. Dark shapes move at the edge of your vision.";
    }

    @Override
    protected void initializeRoom() {
        if (!puzzleSolved) {
            addItem(new ShrineKey());
        }

        examineDescriptions.put("runes", "Ancient symbols that seem to shift and writhe when you're not looking directly at them.");
        examineDescriptions.put("shrine", "A towering stone structure with intricate carvings. There appears to be a puzzle mechanism.");
        examineDescriptions.put("shadows", "Dark shapes that seem to watch you from the forest edge.");
        examineDescriptions.put("creatures", "Strange beings that fade away when you try to focus on them.");
    }

    public boolean isPuzzleSolved() {
        return puzzleSolved;
    }
    public void setPuzzleSolved(boolean solved) {
        this.puzzleSolved = solved;
    }
}
