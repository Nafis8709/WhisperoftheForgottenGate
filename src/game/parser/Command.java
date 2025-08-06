package game.parser;

public class Command {
    private CommandType type;
    private String object;

    public Command(CommandType type, String object) {
        this.type = type;
        this.object = object;
    }

    public CommandType getType() { return type; }
    public String getObject() { return object; }
}
