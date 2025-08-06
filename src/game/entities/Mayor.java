package game.entities;

public class Mayor extends NPC {
    public Mayor() {
        super("Mayor Thorne", "A well-dressed man with an air of authority and hidden knowledge.");
    }

    @Override
    protected void initializeDialogue() {
        dialogue.add("Welcome, stranger. You look... familiar somehow.");
        dialogue.add("I need you to pick up some supplies from Elias's shop.");
        dialogue.add("Take this coin. There's something beneath the shop you'll need.");
        dialogue.add("A scout named Linna went missing in the forest. Find her.");
    }

    @Override
    public String interact() {
        if (!hasBeenTalkedTo) {
            hasBeenTalkedTo = true;
            return dialogue.get(0) + " " + dialogue.get(1);
        }
        return "Have you completed your tasks?";
    }
}
