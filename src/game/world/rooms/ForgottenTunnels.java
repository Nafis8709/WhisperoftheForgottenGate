package game.world.rooms;

import game.items.PersonalLetters;

public class ForgottenTunnels extends Room {
    public ForgottenTunnels() {
        super("forgotten_tunnels", "Forgotten Tunnels",
                "Ancient stone tunnels that predate the town above.");
        this.detailedDescription = "These tunnels stretch beneath the entire town, carved from " +
                "living rock. The walls bear the same strange runes you've seen elsewhere, and " +
                "you can feel a faint pulse emanating from deeper underground.";
    }

    @Override
    protected void initializeRoom() {
        addItem(new PersonalLetters());

        examineDescriptions.put("walls", "Carved stone covered in the same runes as the forest shrine.");
        examineDescriptions.put("runes", "Ancient symbols that seem to pulse with the same rhythm as your heartbeat.");
        examineDescriptions.put("passages", "Multiple tunnels branch off in different directions.");
        examineDescriptions.put("deeper", "One passage slopes downward into absolute darkness.");
    }
}
