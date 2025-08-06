package game.items;

public class EnergyRevolver extends Item{
    public EnergyRevolver() {
        super("Energy Revolver", "A perfectly clean revolver that hums faintly with energy.", true);
        this.examineDescription = "Despite the basement's age and dust, this weapon is pristine. " +
                "It pulses with an otherworldly energy that makes your skin tingle.";
        this.isUnique = true;
    }

    @Override
    public String use() {
        return "The revolver feels warm and alive in your hands. You sense it's meant for more than ordinary threats.";
    }
}
