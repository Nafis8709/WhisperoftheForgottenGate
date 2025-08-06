package game.items;

public class TownMap extends Item {
    public TownMap() {
        super("Old Town Map", "A map of the town showing streets and buildings that don't match reality.", true);
        this.examineDescription = "This map shows the town as it was decades ago. Many buildings " +
                "and streets marked here don't exist anymore... or do they?";
        this.isUnique = true;
    }

    @Override
    public String use() {
        return "Comparing the map to your memories reveals disturbing inconsistencies. " +
                "The town's layout has been changing, perhaps for years.";

    }
}
