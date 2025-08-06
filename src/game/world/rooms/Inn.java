package game.world.rooms;

import game.entities.Innkeeper;

public class Inn extends Room{
    public Inn() {
        super("inn", "The Wanderer's Rest Inn",
                "A cozy inn with flickering candles and an atmosphere of forced normalcy.");
        this.detailedDescription = "The inn feels oddly familiar, yet wrong somehow. The innkeeper " +
                "greets you with a smile that doesn't reach his eyes, and you notice some of the " +
                "patrons staring at you with hollow expressions.";
    }

    @Override
    protected void initializeRoom() {
        addNPC(new Innkeeper());

        examineDescriptions.put("patrons", "The guests avoid eye contact, but you feel them watching when you look away.");
        examineDescriptions.put("candles", "The flames flicker without any wind, casting dancing shadows.");
        examineDescriptions.put("stairs", "Wooden stairs leading to guest rooms that seem to shift when unobserved.");
        examineDescriptions.put("floor", "You notice a hidden trapdoor behind the bar.");
    }
}
