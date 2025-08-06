package game.ui;

import java.util.Scanner;
import game.core.GameEngine;

public class GameUI {
    private GameEngine gameEngine;
    private Scanner scanner;

    public GameUI(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.scanner = new Scanner(System.in);
    }

    public void displayWelcome() {
        System.out.println("=".repeat(50));
        System.out.println("    WHISPERS OF THE FORGOTTEN GATE");
        System.out.println("=".repeat(50));
        System.out.println("A mysterious adventure awaits...");
        System.out.println();
    }

    public void displayHelp() {
        System.out.println("\nAvailable Commands:");
        System.out.println("- go/move [direction] - Move in a direction");
        System.out.println("- look/examine [object] - Examine something");
        System.out.println("- take/get [item] - Take an item");
        System.out.println("- use [item] - Use an item");
        System.out.println("- talk [person] - Talk to someone");
        System.out.println("- inventory/inv - Check your inventory");
        System.out.println("- help - Show this help");
        System.out.println("- quit - Exit the game");
        System.out.println("- restart - Restart the game (after ending)");
        System.out.println("\nSpecial Commands (Act V):");
        System.out.println("- seal gate - Attempt to seal the Original Gate");
        System.out.println("- enter gate - Step through the Original Gate");
        System.out.println();
    }

    public String getInput() {
        System.out.print("> ");
        return scanner.nextLine();
    }

    public void display(String message) {
        System.out.println(message);
    }

    public void displayError(String error) {
        System.out.println("Error: " + error);
    }
}
