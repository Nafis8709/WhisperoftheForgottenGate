package game.items;

public abstract class Item {
    protected String name;
    protected String description;
    protected String examineDescription;
    protected boolean canTake;
    protected boolean isUnique;

    public Item(String name, String description, boolean canTake) {
        this.name = name;
        this.description = description;
        this.canTake = canTake;
        this.isUnique = false;
    }

    public abstract String use();
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getExamineDescription() {
        return examineDescription != null ? examineDescription : description;
    }
    public boolean canTake() {
        return canTake;
    }
    public boolean isUnique() {
        return isUnique;
    }
}
