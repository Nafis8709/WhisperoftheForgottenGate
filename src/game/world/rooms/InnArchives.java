package game.world.rooms;

import game.items.AncientDocuments;
import game.items.TownMap;

public class InnArchives extends Room {
    public InnArchives() {
        super("inn_archives", "Hidden Archives",
                "A dusty underground chamber filled with old records and forgotten documents.");
        this.detailedDescription = "Beneath the inn lies a repository of the town's true history. " +
                "Shelves line the walls, packed with documents, maps, and records that tell a different " +
                "story than what the townspeople remember.";
    }

    @Override
    protected void initializeRoom() {
        addItem(new AncientDocuments());
        addItem(new TownMap());

        examineDescriptions.put("shelves", "Packed with documents spanning decades, many with pages mysteriously torn out.");
        examineDescriptions.put("documents", "Records of strange disappearances, reality distortions, and cover-ups.");
        examineDescriptions.put("maps", "Old town maps showing buildings and streets that no longer exist.");
        examineDescriptions.put("tunnel", "A hidden passage leading deeper underground.");
    }
}
