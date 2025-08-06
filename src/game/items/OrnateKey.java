package game.items;

public class OrnateKey extends Item {
    public OrnateKey() {
        super("Ornate Key", "A strange, ornate key that glows faintly with inner light.", true);
        this.examineDescription = "The key seems out of place in this grimy alley. " +
                "Its surface is carved with intricate patterns that seem to shift in the light.";
        this.isUnique = true;
    }

    @Override
    public String use() {
        return "The key hums with energy, ready to unlock something mysterious.";
    }
}
