package game.world.rooms;

import game.items.OrnateKey;

public class DarkAlley extends Room {
    public DarkAlley() {
        super("dark_alley", "Dark Alley",
                "A narrow, shadowy alley filled with the smell of rust and garbage.");
        this.detailedDescription = "You awaken in a dark alley, your memory clouded. " +
                "The smell of rust and garbage surrounds you. Trash heaps line the walls, " +
                "and at the end of the alley, something seems different...";

    }
    @Override
    protected void initializeRoom() {
        addItem(new OrnateKey());

        examineDescriptions.put("trash", "Searching through the garbage, you find an ornate key!");
        examineDescriptions.put("garbage", "Searching through the garbage, you find an ornate key!");
        examineDescriptions.put("heap", "Searching through the garbage, you find an ornate key!");
        examineDescriptions.put("wall", "The brick walls are old and weathered, covered in grime.");
        examineDescriptions.put("walls", "The brick walls are old and weathered, covered in grime.");
        examineDescriptions.put("end", "At the end of the alley, a mysterious door has appeared!");
        examineDescriptions.put("door", "A strange door that wasn't there before. It seems to be locked.");
        examineDescriptions.put("mysterious_door", "A strange door that wasn't there before. It seems to be locked.");

    }

}
