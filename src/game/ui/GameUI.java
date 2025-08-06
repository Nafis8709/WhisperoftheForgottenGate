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
        System.out.println("=".repeat(70));
        System.out.println("           WHISPERS OF THE FORGOTTEN GATE");
        System.out.println("                A Mystery Adventure");
        System.out.println("=".repeat(70));
        System.out.println("You awaken in a strange place with no memory of how you got there...");
        System.out.println("Your choices will determine the fate of an entire town.");
        System.out.println();
        System.out.println("This is a text-based adventure game. Type commands to interact with");
        System.out.println("the world around you. If you get stuck, type 'help' for assistance.");
        System.out.println("=".repeat(70));
        System.out.println();
    }

    public void displayStartingInstructions() {
        System.out.println("GETTING STARTED:");
        System.out.println("- Type commands like 'look', 'go north', 'take key', 'talk mayor'");
        System.out.println("- Use 'examine [object]' to investigate things more closely");
        System.out.println("- Check your 'inventory' to see what you're carrying");
        System.out.println("- Type 'help' anytime for a complete list of commands");
        System.out.println();
        System.out.println("TIP: Start by examining your surroundings carefully!");
        System.out.println("=".repeat(70));
        System.out.println();
    }

    public void displayHelp() {
        System.out.println();
        System.out.println("=".repeat(60));
        System.out.println("                    COMMAND HELP");
        System.out.println("=".repeat(60));
        System.out.println();
        System.out.println("MOVEMENT:");
        System.out.println("  go [direction]     - Move in a direction (north, south, door, etc.)");
        System.out.println("  move [direction]   - Same as 'go'");
        System.out.println();
        System.out.println("OBSERVATION:");
        System.out.println("  look               - Look around your current location again");
        System.out.println("  examine [object]   - Examine something specific");
        System.out.println("  look [object]      - Same as examine");
        System.out.println();
        System.out.println("ITEMS:");
        System.out.println("  take [item]        - Pick up an item");
        System.out.println("  use [item]         - Use an item from your inventory");
        System.out.println("  inventory          - Check what you're carrying");
        System.out.println("  inv                - Short for inventory");
        System.out.println();
        System.out.println("SOCIAL:");
        System.out.println("  talk [person]      - Talk to someone");
        System.out.println("  speak [person]     - Same as talk");
        System.out.println();
        System.out.println("GAME CONTROL:");
        System.out.println("  help               - Show this help menu");
        System.out.println("  save               - Save your progress (planned feature)");
        System.out.println("  load               - Load saved progress (planned feature)");
        System.out.println("  restart            - Restart the game (after ending)");
        System.out.println("  quit               - Exit the game");
        System.out.println();
        System.out.println("SPECIAL COMMANDS (Act V only):");
        System.out.println("  seal gate          - Attempt to seal the Original Gate");
        System.out.println("  enter gate         - Step through the Original Gate");
        System.out.println();
        System.out.println("TIPS:");
        System.out.println("- Commands are not case-sensitive");
        System.out.println("- You can use partial names (e.g., 'take key' for 'Ornate Key')");
        System.out.println("- If a command doesn't work, try typing it differently");
        System.out.println("- Pay attention to story hints and NPC dialogue");
        System.out.println("- Some areas require special items or conditions to access");
        System.out.println();
        System.out.println("=".repeat(60));
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
    public void displayWarning(String warning) {
        System.out.println("WARNING: " + warning);
    }

    public void displaySuccess(String success) {
        System.out.println("SUCCESS: " + success);
    }

    public void displayStoryUpdate(String update) {
        System.out.println();
        System.out.println("*** " + update.toUpperCase() + " ***");
        System.out.println();
    }
}
