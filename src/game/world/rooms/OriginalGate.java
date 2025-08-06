package game.world.rooms;

public class OriginalGate extends Room {
    public OriginalGate() {
        super("original_gate", "The Original Gate",
                "The source of all the town's strangeness - a massive otherworldly portal.");
        this.detailedDescription = "Before you stands the Original Gate, a massive portal of " +
                "swirling energy and impossible geometries. This is what the town was built to " +
                "contain, what your key was meant to open. Linna stands before it, her condition " +
                "worsening with each pulse of its alien light.";
    }

    @Override
    protected void initializeRoom() {
        examineDescriptions.put("gate", "A portal to realms beyond human comprehension.");
        examineDescriptions.put("portal", "Swirling energies that hurt to look at directly.");
        examineDescriptions.put("geometries", "Shapes that shouldn't exist in three-dimensional space.");
        examineDescriptions.put("light", "An illumination that seems to come from everywhere and nowhere.");
        examineDescriptions.put("seal", "The ancient barriers that once contained this horror are failing.");
    }
}
