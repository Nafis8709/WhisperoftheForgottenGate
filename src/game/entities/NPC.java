package game.entities;

import java.util.ArrayList;
import java.util.List;

public abstract class NPC {
    protected String name;
    protected String description;
    protected List<String> dialogue;
    protected boolean hasBeenTalkedTo;
    protected String currentQuest;

    public NPC(String name, String description) {
        this.name = name;
        this.description = description;
        this.dialogue = new ArrayList<>();
        this.hasBeenTalkedTo = false;
        initializeDialogue();
    }
    protected abstract void initializeDialogue();
    public abstract String interact();

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public boolean hasBeenTalkedTo() {
        return hasBeenTalkedTo;
    }
    public void setHasBeenTalkedTo(boolean talked) {
        this.hasBeenTalkedTo = talked;
    }
}
