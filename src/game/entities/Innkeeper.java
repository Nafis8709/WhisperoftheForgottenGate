package game.entities;

public class Innkeeper extends NPC{
    public Innkeeper() {
        super("Innkeeper", "A friendly but hollow-eyed innkeeper who seems to be going through the motions.");
    }

    @Override
    protected void initializeDialogue() {
        dialogue.add("Welcome to the Wanderer's Rest! We have... comfortable rooms.");
        dialogue.add("Been running this place for... how long has it been? Time moves strangely here.");
        dialogue.add("The guests upstairs never seem to leave. But that's... that's normal, right?");
        dialogue.add("Sometimes I forget who I am. Do you ever feel that way?");
        dialogue.add("The basement has old records. Town history. But some things... better left forgotten.");
    }

    @Override
    public String interact() {
        if (!hasBeenTalkedTo) {
            hasBeenTalkedTo = true;
            return dialogue.get(0);
        }

        int index = (int)(Math.random() * dialogue.size());
        return dialogue.get(index);
    }
}
