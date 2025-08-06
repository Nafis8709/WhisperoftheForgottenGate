package game.items;

public class AncientDocuments extends Item{
    public AncientDocuments() {
        super("Ancient Documents", "Yellowed papers containing the town's hidden history.", true);
        this.examineDescription = "These documents speak of a great disaster, reality rifts, " +
                "and a cover-up that spans generations. Many pages have been deliberately torn out.";
        this.isUnique = true;
    }

    @Override
    public String use() {
        return "Reading the documents reveals disturbing truths about the town's foundation. " +
                "It was built specifically to contain something that should never have been released.";
    }
}
