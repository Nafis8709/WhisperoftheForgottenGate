package game.entities;

public class Elias extends NPC{
    private boolean truthRevealed = false;
    public Elias() {
        super("Elias", "A weathered shopkeeper with knowing eyes that seem to see through you.");
    }

    @Override
    protected void initializeDialogue() {
        dialogue.add("You feel it too, don't you? The hum. The pull.");
        dialogue.add("Take this letter back to the mayor. No questions asked.");
        dialogue.add("That coin... yes, I know what it opens. The basement holds what you seek.");
        dialogue.add("This town isn't what it seems. It was built around a rift. A gate.");
        dialogue.add("Long ago, we sealed it. But now... something's waking up.");
        dialogue.add("And you — you've opened it again.");
    }

    @Override
    public String interact() {
        if (!hasBeenTalkedTo) {
            hasBeenTalkedTo = true;
            return dialogue.get(0) + " " + dialogue.get(1);
        }
        if (!truthRevealed && hasFoundEvidence()) {
            truthRevealed = true;
            return "Elias's facade drops. His eyes grow distant and haunted.\n" +
                    "\"" + dialogue.get(3) + " " + dialogue.get(4) + " " + dialogue.get(5) + "\"";
        }

        if (truthRevealed) {
            return "\"The Gate has chosen you, just as it chose others before. " +
                    "The town... we're all just echoes now, playing our parts in its design.\"";
        }
        return "The basement awaits, if you have the coin.";
    }
    private boolean hasFoundEvidence() {
        return true;
    }
    public boolean isTruthRevealed() {
        return truthRevealed;
    }
}
