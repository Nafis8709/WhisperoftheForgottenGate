package game.world.rooms;

import game.items.EnergyRevolver;

public class ShopBasement extends Room {
    public ShopBasement() {
        super("shop_basement", "Hidden Basement",
                "A secret basement filled with ancient symbols and mysterious artifacts.");
        this.detailedDescription = "Behind rusted locks and cryptic sigils, you find yourself " +
                "in a hidden chamber. The air hums with strange energy.";
    }

    @Override
    protected void initializeRoom() {
        addItem(new EnergyRevolver());

        examineDescriptions.put("sigils", "Ancient symbols carved into the stone walls pulse faintly.");
        examineDescriptions.put("locks", "Rusted mechanisms that once sealed this place.");
        examineDescriptions.put("walls", "Stone walls covered in mysterious markings.");
    }
}
