package game.world.rooms;

import game.entities.Linna;

public class RuinedGlade extends Room {
    public RuinedGlade() {
        super("ruined_glade", "Ruined Glade",
                "A desolate glade filled with broken stone monuments and an oppressive atmosphere.");
        this.detailedDescription = "You emerge into a circular glade surrounded by crumbling stone monuments. " +
                "The air here feels thick and wrong. In the center, you see a figure sitting motionless " +
                "among the ruins - it's Linna, but something about her seems... changed.";
    }

    @Override
    protected void initializeRoom() {
        // Add Linna if she hasn't been rescued yet
        addNPC(new Linna());

        examineDescriptions.put("monuments", "Broken stone pillars covered in the same runes as the shrine.");
        examineDescriptions.put("ruins", "Ancient stonework that predates the town by centuries.");
        examineDescriptions.put("center", "The focal point of the glade, where reality seems thinner.");
        examineDescriptions.put("air", "The atmosphere here feels heavy, like breathing through water.");
    }
}
