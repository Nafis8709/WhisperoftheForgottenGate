package game.items;

public class ShrineKey extends Item{
    public ShrineKey(){
        super("Shrine Key", "A crystalline key that resonates with otherworldly energy.", true);
        this.examineDescription = "This key seems to be made of solidified light. " +
                "It hums in harmony with the forest shrine's runes.";
        this.isUnique = true;
    }
    @Override
    public String use() {
        return "The shrine key pulses with power, ready to unlock deeper mysteries.";
    }

}
