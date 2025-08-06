package game.parser;

import java.util.Arrays;
import java.util.List;


public class CommandParser {
    public Command parseCommand(String input) {
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
    }
}
