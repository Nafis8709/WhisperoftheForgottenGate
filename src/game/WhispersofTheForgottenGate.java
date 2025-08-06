package game;

import game.core.GameEngine;
import game.ui.GameUI;

public class WhispersofTheForgottenGate {
    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        GameUI ui = new GameUI(engine);
        engine.setUI(ui);
        engine.start();
    }
}
