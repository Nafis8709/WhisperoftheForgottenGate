package game.core;

import game.entities.NPC;
import game.items.Item;
import game.parser.Command;
import game.ui.GameUI;
import game.world.WorldManager;
import game.entities.Player;
import game.parser.CommandParser;
import game.story.StoryManager;
import game.inventory.InventoryManager;
import game.world.rooms.Room;

public class GameEngine {
    private WorldManager worldManager;
    private Player player;
    private CommandParser commandParser;
    private StoryManager storyManager;
    private InventoryManager inventoryManager;
    private GameState gameState;
    private GameUI ui;
    private boolean running;

    public GameEngine() {
        this.worldManager = new WorldManager();
        this.player = new Player();
        this.commandParser = new CommandParser();
        this.storyManager = new StoryManager();
        this.inventoryManager = new InventoryManager();
        this.gameState = GameState.MAIN_MENU;
        this.running = false;
    }
    public void setUI(GameUI ui) {
        this.ui = ui;
    }

    public void start() {
        if(ui==null){
            System.err.println("UI not set.");
            return;
        }
        running = true;
        ui.displayWelcome();
        ui.displayStartingInstructions();

        storyManager.initializeStory();
        worldManager.loadWorld();
       /* ui.display(worldManager.getCurrentRoom().getFullDescription());
        ui.display("\nYou can type 'help' for available commands, or try:");
        ui.display("- 'look around' to examine your surroundings");
        ui.display("- 'examine trash' to search through the garbage");
        ui.display("- 'take [item]' to pick up items");
        ui.display("");*/
        displayCurrentRoomInfo();
        gameLoop();


    }

    private void gameLoop() {
        while (running) {
            String input = ui.getInput();
            if (input.trim().isEmpty()) {
                ui.display("Please enter a command. Type 'help' if you need assistance.");
                continue;
            }
            processCommand(input);
        }
    }
    private void processCommand(String input) {
        Command command = commandParser.parseCommand(input);

        switch (command.getType()) {
            case MOVE:
                handleMove(command.getObject());
                break;
            case EXAMINE:
                handleExamine(command.getObject());
                break;
            case TAKE:
                handleTake(command.getObject());
                break;
            case USE:
                handleUse(command.getObject());
                break;
            case TALK:
                handleTalk(command.getObject());
                break;
            case INVENTORY:
                handleInventory();
                break;
            case HELP:
                ui.displayHelp();
                break;
            case LOOK:
                displayCurrentRoomInfo();
                break;
            case SAVE:
                handleSave();
                break;
            case LOAD:
                handleLoad();
                break;
            case QUIT:
                handleQuit();
                break;
            case RESTART:
                handleRestart();
                break;
            case UNKNOWN:
                ui.display("I don't understand that command. Here are some suggestions:");
                ui.display("- Type 'help' for available commands");
                ui.display("- Try 'look' to see your surroundings again");
                ui.display("- Use simple commands like 'go north', 'take key', or 'examine door'");
                break;
        }
    }

    private void displayCurrentRoomInfo() {
        Room currentRoom = worldManager.getCurrentRoom();
        ui.display("\n" + "=".repeat(50));
        ui.display("LOCATION: " + currentRoom.getName());
        ui.display("=".repeat(50));
        ui.display(currentRoom.getFullDescription());


        /*if (!currentRoom.getExits().isEmpty()) {
            ui.display("\nEXITS:");
            for (String direction : currentRoom.getExits().keySet()) {
                ui.display("- " + direction + " (" + currentRoom.getExits().get(direction).getName() + ")");
            }
        }


        if (!currentRoom.getItems().isEmpty()) {
            ui.display("\nITEMS HERE:");
            for (Item item : currentRoom.getItems()) {
                ui.display("- " + item.getName() + " - " + item.getDescription());
            }
        }


        if (!currentRoom.getNPCs().isEmpty()) {
            ui.display("\nPEOPLE HERE:");
            for (NPC npc : currentRoom.getNPCs()) {
                ui.display("- " + npc.getName() + " - " + npc.getDescription());
            }
        }*/
        ui.display("");
    }


    private void handleMove(String direction) {
        if (direction.isEmpty()) {
            ui.display("Go where? Available exits are:");
            Room currentRoom = worldManager.getCurrentRoom();
            for (String exit : currentRoom.getExits().keySet()) {
                ui.display("- " + exit);
            }
            return;
        }

        Room currentRoom = worldManager.getCurrentRoom();


        if (currentRoom.getId().equals("dark_alley") &&
                (direction.toLowerCase().equals("door") || direction.toLowerCase().equals("mysterious_door") ||
                        direction.toLowerCase().equals("end"))) {

            if (player.getInventory().hasItem("Ornate Key")) {
                ui.display("You use the ornate key on the mysterious door. It glows brightly and opens to reveal a swirling void!");
                ui.display("You step through...");
                worldManager.setCurrentRoom("town_square");
                storyManager.setFlag("used_door", true);
                displayCurrentRoomInfo();
                return;
            } else {
                ui.display("There's a strange door here, but it seems to be locked. You need a key.");
                ui.display("Try examining the trash to find something useful.");
                return;
            }
        }


        if (currentRoom.getId().equals("shop") &&
                (direction.toLowerCase().equals("basement") || direction.toLowerCase().equals("down"))) {

            if (player.getInventory().hasItem("Ancient Coin")) {
                ui.display("You use the ancient coin. A hidden hatch opens in the floor!");
                Room nextRoom = currentRoom.getExits().get(direction.toLowerCase());
                worldManager.setCurrentRoom(nextRoom.getId());
                displayCurrentRoomInfo();
                return;
            } else {
                ui.display("You notice something odd about the floor, but you need something to activate it.");
                ui.display("Perhaps the mayor has something that could help.");
                return;
            }
        }


        if (currentRoom.getId().equals("forest_shrine") &&
                direction.toLowerCase().equals("glade")) {

            if (!storyManager.getFlag("solved_shrine_puzzle")) {
                ui.display("The path to the glade is blocked by an ancient barrier. You need to solve the shrine's puzzle first.");
                ui.display("Try 'use shrine key' if you have one, or examine the runes more carefully.");
                return;
            }
        }


        if (currentRoom.getId().equals("forgotten_tunnels") &&
                direction.toLowerCase().equals("catacombs")) {

            if (!storyManager.getFlag("linna_in_town")) {
                ui.display("The passage to the catacombs is sealed. Something tells you that you need Linna's presence to proceed.");
                ui.display("Have you rescued her from the forest yet?");
                return;
            }
        }

        Room nextRoom = currentRoom.getExits().get(direction.toLowerCase());

        if (nextRoom != null) {
            worldManager.setCurrentRoom(nextRoom.getId());
            displayCurrentRoomInfo();


            if (nextRoom.getId().equals("forest_entrance")) {
                storyManager.setFlag("entered_forest", true);
            } else if (nextRoom.getId().equals("catacombs_entrance")) {
                storyManager.setFlag("entered_catacombs", true);
                ui.display("\n[Linna follows you, her condition worsening with each step deeper.]");
            } else if (nextRoom.getId().equals("original_gate")) {
                storyManager.setFlag("reached_gate", true);
                ui.display("\n[The air itself seems to scream as you approach the Gate. This is it - the moment of final choice.]");
            }
            if (storyManager.getActNumber() >= 4 && Math.random() < 0.3) {
                displayRealityDistortion();
            }
        } else {
            ui.display("You can't go that way.");
            ui.display("Available exits are:");
            for (String exit : currentRoom.getExits().keySet()) {
                ui.display("- " + exit + " (to " + currentRoom.getExits().get(exit).getName() + ")");
            }
        }
    }




    private void displayRealityDistortion() {
        String[] distortions = {
                "\n[The walls seem to shift slightly when you're not looking directly at them.]",
                "\n[You hear whispers in a language you don't recognize.]",
                "\n[For a moment, the room looks completely different, then snaps back to normal.]",
                "\n[The shadows move independently of their sources.]",
                "\n[You smell something that reminds you of a place you've never been.]"
        };
        ui.display(distortions[(int)(Math.random() * distortions.length)]);
    }




    private void handleExamine(String object) {
        if (object.isEmpty()) {
            ui.display("Examine what? You can examine:");
            Room currentRoom = worldManager.getCurrentRoom();


            for (String key : currentRoom.getExamineDescriptions().keySet()) {
                ui.display("- " + key);
            }

            for (Item item : currentRoom.getItems()) {
                ui.display("- " + item.getName());
            }

            for (NPC npc : currentRoom.getNPCs()) {
                ui.display("- " + npc.getName());
            }
            return;
        }

        Room currentRoom = worldManager.getCurrentRoom();

        String examineDesc = currentRoom.getExamineDescriptions().get(object.toLowerCase());
        if (examineDesc != null) {
            ui.display(examineDesc);
            return;
        }

        for (Item item : currentRoom.getItems()) {
            if (item.getName().toLowerCase().contains(object.toLowerCase())) {
                ui.display(item.getExamineDescription());
                return;
            }
        }


        for (NPC npc : currentRoom.getNPCs()) {
            if (npc.getName().toLowerCase().contains(object.toLowerCase())) {
                ui.display(npc.getDescription());
                return;
            }
        }

        Item inventoryItem = player.getInventory().getItem(object);
        if (inventoryItem != null) {
            ui.display("From your inventory: " + inventoryItem.getExamineDescription());
            return;
        }

        ui.display("You don't see anything special about that here.");
        ui.display("Try 'examine' by itself to see what you can examine.");
    }



    private void handleTake(String itemName) {
        if (itemName.isEmpty()) {
            ui.display("Take what? Available items:");
            Room currentRoom = worldManager.getCurrentRoom();
            if (currentRoom.getItems().isEmpty()) {
                ui.display("There's nothing here to take.");
            } else {
                for (Item item : currentRoom.getItems()) {
                    if (item.canTake()) {
                        ui.display("- " + item.getName());
                    }
                }
            }
            return;
        }

        Room currentRoom = worldManager.getCurrentRoom();
        Item itemToTake = null;

        for (Item item : currentRoom.getItems()) {
            if (item.getName().toLowerCase().contains(itemName.toLowerCase())) {
                itemToTake = item;
                break;
            }
        }

        if (itemToTake == null) {
            ui.display("You don't see that here. Available items:");
            for (Item item : currentRoom.getItems()) {
                if (item.canTake()) {
                    ui.display("- " + item.getName());
                }
            }
            return;
        }

        if (!itemToTake.canTake()) {
            ui.display("You can't take that.");
            return;
        }

        if (player.getInventory().addItem(itemToTake)) {
            currentRoom.removeItem(itemToTake);
            ui.display("You take the " + itemToTake.getName() + ".");


            if (itemToTake.getName().equals("Ornate Key")) {
                storyManager.setFlag("found_key", true);
                ui.display("The key pulses with a strange energy...");
                ui.display("Perhaps you can use this on that mysterious door in the alley.");
            } else if (itemToTake.getName().equals("Energy Revolver")) {
                storyManager.setFlag("found_revolver", true);
                player.setHasRevolver(true);
                ui.display("The weapon hums with otherworldly power as you pick it up.");
            }
        } else {
            ui.display("Your inventory is full. Drop something first with 'drop [item]'.");
        }
    }

   private void handleUse(String itemName) {
       if (itemName.isEmpty()) {
           ui.display("Use what? Items in your inventory:");
           if (player.getInventory().getItems().isEmpty()) {
               ui.display("Your inventory is empty.");
           } else {
               for (Item item : player.getInventory().getItems()) {
                   ui.display("- " + item.getName());
               }
           }
           return;
       }

       Room currentRoom = worldManager.getCurrentRoom();

       if (itemName.toLowerCase().contains("shrine") && itemName.toLowerCase().contains("key") &&
               currentRoom.getId().equals("forest_shrine")) {

           if (player.getInventory().hasItem("Shrine Key")) {
               ui.display("You insert the shrine key into the ancient mechanism. The runes flare with blinding light!");
               ui.display("The barrier to the ruined glade dissolves. You hear a distant scream echoing from deeper in the forest.");
               storyManager.setFlag("solved_shrine_puzzle", true);
               ui.display("The path to the glade is now open!");
               return;
           } else {
               ui.display("You don't have a shrine key.");
               return;
           }
       }


       if (itemName.toLowerCase().contains("revolver") || itemName.toLowerCase().contains("gun")) {
           if (player.getInventory().hasItem("Energy Revolver")) {
               if (currentRoom.getId().equals("original_gate")) {
                   ui.display("You raise the energy revolver toward the Gate. It pulses with responding power.");
                   ui.display("You realize this weapon was made specifically for this moment. What will you do?");
                   ui.display("Type 'seal gate' to attempt to seal it, or 'enter gate' to step through.");
                   return;
               } else {
                   ui.display("The revolver hums with power, but there's nothing here that requires its use.");
                   return;
               }
           } else {
               ui.display("You don't have an energy revolver.");
               return;
           }
       }

       if (currentRoom.getId().equals("original_gate")) {
           if (itemName.toLowerCase().contains("seal") || itemName.toLowerCase().equals("seal gate")) {
               handleFinalChoice("seal");
               return;
           } else if (itemName.toLowerCase().contains("enter") || itemName.toLowerCase().equals("enter gate")) {
               handleFinalChoice("enter");
               return;
           }
       }

       String result = inventoryManager.useItem(player, itemName);
       ui.display(result);

       if (itemName.toLowerCase().contains("document")) {
           storyManager.setFlag("found_documents", true);
       } else if (itemName.toLowerCase().contains("letter")) {
           storyManager.setFlag("found_letters", true);
       } else if (itemName.toLowerCase().contains("map")) {
           storyManager.setFlag("found_map", true);
       }
   }


    private void handleFinalChoice(String choice) {
        if (storyManager.getFlag("final_choice_made")) {
            ui.display("You've already made your choice. The consequences are irreversible.");
            return;
        }

        storyManager.setFlag("final_choice_made", true);

        if (choice.equals("seal")) {
            ui.display("\n=== SEALING THE GATE ===");
            ui.display("You turn the energy revolver on yourself and pull the trigger.");
            ui.display("Your life force flows into the Gate's containment systems.");
            ui.display("Linna screams as the Gate's influence over her breaks.");
            ui.display("The portal begins to collapse, reality stabilizing around it.");
            ui.display("Your sacrifice has saved the town... but at what cost?");
            ui.display("\nThe last thing you see is Linna's tears as the world fades to black.");
        } else if (choice.equals("enter")) {
            ui.display("\n=== ENTERING THE GATE ===");
            ui.display("You step forward into the swirling vortex of impossible geometries.");
            ui.display("Linna follows, her corruption complete, her humanity lost.");
            ui.display("You become the next 'Chosen', a puppet of the entities beyond.");
            ui.display("The Gate opens fully, unleashing horrors upon the world.");
            ui.display("You watch from your new prison of consciousness as reality unravels.");
            ui.display("\nYou are not the first. You will not be the last.");
        }

        ui.display("\n" + "=".repeat(50));
        ui.display("The town resets. The alley is empty again.");
        ui.display("A new key waits in the trash.");
        ui.display("And somewhere, the Gate whispers again:");
        ui.display("\"Not the first. Never the last.\"");
        ui.display("=".repeat(50));
        ui.display("\nThank you for playing Whispers of the Forgotten Gate!");
        ui.display("Type 'quit' to exit, or 'restart' to begin anew...");

        storyManager.setFlag("game_reset", true);
    }


   private void handleTalk(String npcName) {
       if (npcName.isEmpty()) {
           ui.display("Talk to whom? People here:");
           Room currentRoom = worldManager.getCurrentRoom();
           if (currentRoom.getNPCs().isEmpty()) {
               ui.display("There's no one here to talk to.");
           } else {
               for (NPC npc : currentRoom.getNPCs()) {
                   ui.display("- " + npc.getName());
               }
           }
           return;
       }

       Room currentRoom = worldManager.getCurrentRoom();

       for (NPC npc : currentRoom.getNPCs()) {
           if (npc.getName().toLowerCase().contains(npcName.toLowerCase()) ||
                   npcName.toLowerCase().contains("mayor") && npc.getName().contains("Mayor") ||
                   npcName.toLowerCase().contains("elias") && npc.getName().contains("Elias") ||
                   npcName.toLowerCase().contains("linna") && npc.getName().contains("Linna") ||
                   npcName.toLowerCase().contains("innkeeper") && npc.getName().contains("Innkeeper")) {

               String response = npc.interact();
               ui.display(npc.getName() + ": \"" + response + "\"");


               handleNPCInteractions(npc);
               return;
           }
       }

       ui.display("There's no one here by that name.");
       ui.display("People you can talk to:");
       for (NPC npc : currentRoom.getNPCs()) {
           ui.display("- " + npc.getName());
       }
   }

    private void handleNPCInteractions(NPC npc) {
        if (npc.getName().equals("Mayor Thorne")) {
            handleMayorInteraction();
        } else if (npc.getName().equals("Elias")) {
            handleEliasInteraction();
        } else if (npc.getName().equals("Linna")) {
            handleLinnaInteraction(npc);
        } else if (npc.getName().equals("Innkeeper")) {
            handleInnkeeperInteraction();
        }
    }

    private void handleMayorInteraction() {
        if (!storyManager.getFlag("met_mayor")) {
            storyManager.setFlag("met_mayor", true);
            ui.display("\n[You feel like you should visit Elias's shop]");
        } else if (storyManager.getFlag("got_letter") && !storyManager.getFlag("got_coin")) {
            player.getInventory().addItem(new game.items.AncientCoin());
            storyManager.setFlag("got_coin", true);
            ui.display("\nMayor Thorne hands you an ancient coin.");
            ui.display("\"Take this. There's something beneath the shop you'll need.\"");
        } else if (storyManager.getFlag("found_revolver") && !storyManager.getFlag("got_mission")) {
            storyManager.setFlag("got_mission", true);
            ui.display("\n\"Good, you found the weapon. Now I have a real mission for you.\"");
            ui.display("\"A scout named Linna went missing in the forest. Find her.\"");
        } else if (storyManager.getFlag("rescued_linna") && !storyManager.getFlag("linna_in_town")) {
            storyManager.setFlag("linna_in_town", true);
            ui.display("\n\"You found her? Bring her here. But... be careful. Something seems wrong about her.\"");
            // Move Linna to town square
            worldManager.getRooms().get("town_square").addNPC(new game.entities.Linna());
            ui.display("[Linna has been brought to the town square, but the townspeople seem disturbed by her presence.]");
        }
    }

    private void handleEliasInteraction() {
        if (storyManager.getFlag("met_mayor") && !storyManager.getFlag("got_letter")) {
            // Give the letter
            storyManager.setFlag("got_letter", true);
            ui.display("\nElias hands you a sealed letter.");
            ui.display("\"Take this back to the mayor. No questions asked.\"");
        } else if (storyManager.getFlag("found_documents") && storyManager.getFlag("found_letters") &&
                !storyManager.getFlag("elias_truth_revealed")) {
            storyManager.setFlag("elias_truth_revealed", true);
            ui.display("\nElias's facade finally crumbles as you confront him with the evidence.");
        }
    }

    private void handleLinnaInteraction(NPC linna) {
        Room currentRoom = worldManager.getCurrentRoom();
        if (currentRoom.getId().equals("ruined_glade") && !storyManager.getFlag("rescued_linna")) {
            storyManager.setFlag("rescued_linna", true);
            storyManager.setFlag("found_linna", true);
            ui.display("\n[You can now lead Linna back to town, but she seems... changed.]");
            ui.display("[Try going back to town square with her.]");
        } else if (storyManager.getFlag("linna_in_town")) {

            if (linna instanceof game.entities.Linna) {
                ((game.entities.Linna)linna).increaseCorruption();
            }
            ui.display("\n[The townspeople stare at Linna with fear and unease. Some avoid looking at her entirely.]");
        }
    }

    private void handleInnkeeperInteraction() {
        if (storyManager.getActNumber() >= 4) {
            ui.display("\n[The innkeeper's eyes seem hollow, like he's not entirely there.]");
        }
    }

    private void handleInventory() {
        String inventoryDisplay = inventoryManager.displayInventory(player);
        ui.display(inventoryDisplay);

        if (!player.getInventory().getItems().isEmpty()) {
            ui.display("\nUse 'use [item]' to use an item, or 'examine [item]' to look at it closer.");
        }
    }

    private void handleSave() {
        ui.display("Save functionality not yet implemented.");
        ui.display("The game automatically saves your progress at key story points.");
    }

    private void handleLoad() {
        ui.display("Load functionality not yet implemented.");
        ui.display("Use 'restart' to begin the game again.");
    }

    private void handleQuit() {
        ui.display("Are you sure you want to quit? Type 'yes' to confirm or anything else to continue.");
        String confirmation = ui.getInput();
        if (confirmation.toLowerCase().equals("yes") || confirmation.toLowerCase().equals("y")) {
            ui.display("Thanks for playing Whispers of the Forgotten Gate!");
            running = false;
        } else {
            ui.display("Continuing game...");
        }
    }

    private void handleRestart() {
        if (storyManager.getFlag("game_reset")) {
            ui.display("Restarting the eternal cycle...");
            ui.display("The Gate whispers: \"Welcome back, Chosen One.\"");
            ui.display("=".repeat(50));


            storyManager = new StoryManager();
            player = new Player();
            worldManager.loadWorld();

            displayCurrentRoomInfo();
        } else {
            ui.display("You can't restart until you've reached an ending.");
            ui.display("Continue playing to discover the truth about the Gate...");
        }
    }





    public WorldManager getWorldManager() {
        return worldManager;
    }

    public Player getPlayer() {
        return player;
    }


    public StoryManager getStoryManager() {
        return storyManager;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState state) {
        this.gameState = state;
    }

    enum GameState {
        MAIN_MENU,
        PLAYING,
        INVENTORY,
        PAUSED,
        GAME_OVER
    }
}