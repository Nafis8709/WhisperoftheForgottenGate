package game.parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class CommandParser {
   /* public Command parseCommand(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new Command(CommandType.UNKNOWN, "");
        }

        String[] parts = input.trim().toLowerCase().split("\\s+");
        String verb = parts[0];
        String object = parts.length > 1 ? String.join(" ", Arrays.copyOfRange(parts, 1, parts.length)) : "";

        CommandType type = determineCommandType(verb);
        return new Command(type, object);
    }

    private CommandType determineCommandType(String verb) {
        switch (verb) {
            case "go": case "move": case "walk": case "travel":
                return CommandType.MOVE;
            case "look": case "examine": case "inspect": case "l":
                return CommandType.EXAMINE;
            case "take": case "get": case "pick": case "grab":
                return CommandType.TAKE;
            case "use": case "activate": case "employ":
                return CommandType.USE;
            case "talk": case "speak": case "chat": case "converse":
                return CommandType.TALK;
            case "inventory": case "inv": case "i":
                return CommandType.INVENTORY;
            case "help": case "h": case "?":
                return CommandType.HELP;
            case "quit": case "exit": case "q":
                return CommandType.QUIT;
            case "restart": case "reset": case "start":
                return CommandType.RESTART;
            case "seal":
                return CommandType.USE;
            case "enter":
                return CommandType.USE;
            default:
                return CommandType.UNKNOWN;
        }
    }*/
   private Map<String, CommandType> commandAliases;

    public CommandParser() {
        initializeCommandAliases();
    }

    private void initializeCommandAliases() {
        commandAliases = new HashMap<>();

        // Movement commands
        commandAliases.put("go", CommandType.MOVE);
        commandAliases.put("move", CommandType.MOVE);
        commandAliases.put("walk", CommandType.MOVE);
        commandAliases.put("travel", CommandType.MOVE);
        commandAliases.put("head", CommandType.MOVE);
        commandAliases.put("run", CommandType.MOVE);

        // Examination commands
        commandAliases.put("look", CommandType.LOOK);
        commandAliases.put("examine", CommandType.EXAMINE);
        commandAliases.put("inspect", CommandType.EXAMINE);
        commandAliases.put("check", CommandType.EXAMINE);
        commandAliases.put("search", CommandType.EXAMINE);
        commandAliases.put("l", CommandType.LOOK);
        commandAliases.put("ex", CommandType.EXAMINE);

        // Item commands
        commandAliases.put("take", CommandType.TAKE);
        commandAliases.put("get", CommandType.TAKE);
        commandAliases.put("pick", CommandType.TAKE);
        commandAliases.put("grab", CommandType.TAKE);
        commandAliases.put("collect", CommandType.TAKE);
        commandAliases.put("pickup", CommandType.TAKE);

        // Use commands
        commandAliases.put("use", CommandType.USE);
        commandAliases.put("activate", CommandType.USE);
        commandAliases.put("employ", CommandType.USE);
        commandAliases.put("apply", CommandType.USE);
        commandAliases.put("wield", CommandType.USE);

        // Social commands
        commandAliases.put("talk", CommandType.TALK);
        commandAliases.put("speak", CommandType.TALK);
        commandAliases.put("chat", CommandType.TALK);
        commandAliases.put("converse", CommandType.TALK);
        commandAliases.put("ask", CommandType.TALK);
        commandAliases.put("tell", CommandType.TALK);

        // Inventory commands
        commandAliases.put("inventory", CommandType.INVENTORY);
        commandAliases.put("inv", CommandType.INVENTORY);
        commandAliases.put("i", CommandType.INVENTORY);
        commandAliases.put("items", CommandType.INVENTORY);
        commandAliases.put("bag", CommandType.INVENTORY);

        // System commands
        commandAliases.put("help", CommandType.HELP);
        commandAliases.put("h", CommandType.HELP);
        commandAliases.put("?", CommandType.HELP);
        commandAliases.put("commands", CommandType.HELP);

        commandAliases.put("quit", CommandType.QUIT);
        commandAliases.put("exit", CommandType.QUIT);
        commandAliases.put("q", CommandType.QUIT);
        commandAliases.put("bye", CommandType.QUIT);
        commandAliases.put("leave", CommandType.QUIT);

        commandAliases.put("restart", CommandType.RESTART);
        commandAliases.put("reset", CommandType.RESTART);
        commandAliases.put("start", CommandType.RESTART);
        commandAliases.put("begin", CommandType.RESTART);

        commandAliases.put("save", CommandType.SAVE);
        commandAliases.put("load", CommandType.LOAD);

        // Special game commands
        commandAliases.put("seal", CommandType.USE);
        commandAliases.put("enter", CommandType.USE);
    }

    public Command parseCommand(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new Command(CommandType.UNKNOWN, "");
        }

        String[] parts = input.trim().toLowerCase().split("\\s+");
        String verb = parts[0];
        String object = parts.length > 1 ? String.join(" ", Arrays.copyOfRange(parts, 1, parts.length)) : "";

        // Handle multi-word commands
        String fullCommand = input.trim().toLowerCase();
        if (fullCommand.equals("seal gate")) {
            return new Command(CommandType.USE, "seal gate");
        } else if (fullCommand.equals("enter gate")) {
            return new Command(CommandType.USE, "enter gate");
        } else if (fullCommand.equals("look around")) {
            return new Command(CommandType.LOOK, "");
        }

        CommandType type = commandAliases.getOrDefault(verb, CommandType.UNKNOWN);

        // Handle directional movement without "go"
        if (type == CommandType.UNKNOWN && object.isEmpty()) {
            if (isDirectionWord(verb)) {
                return new Command(CommandType.MOVE, verb);
            }
        }

        return new Command(type, object);
    }

    private boolean isDirectionWord(String word) {
        String[] directions = {
                "north", "south", "east", "west", "n", "s", "e", "w",
                "up", "down", "in", "out", "door", "stairs", "basement",
                "forest", "shop", "town", "square", "back", "forward",
                "left", "right", "deeper", "glade", "shrine", "catacombs",
                "tunnels", "inn", "archives", "gate"
        };

        for (String direction : directions) {
            if (direction.equals(word)) {
                return true;
            }
        }
        return false;
    }

}
