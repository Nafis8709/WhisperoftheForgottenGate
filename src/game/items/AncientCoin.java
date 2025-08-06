package game.items;

public class AncientCoin extends Item{
    public AncientCoin() {
        super("Ancient Coin", "A tarnished coin with an ancient symbol etched into it.", true);
        this.examineDescription = "The symbol on the coin seems to pulse with a faint energy. " +
                "You feel it might be important.";
        this.isUnique = true;
    }

    @Override
    public String use() {
        return "The coin grows warm in your hand, resonating with hidden mechanisms.";
    }
}
