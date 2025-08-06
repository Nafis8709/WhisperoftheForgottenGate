package game.items;

public class PersonalLetters extends Item{
    public PersonalLetters() {
        super("Personal Letters", "Private correspondence discussing the town's dark secrets.", true);
        this.examineDescription = "Letters between town officials discussing 'containment protocols', " +
                "'memory adjustments', and 'acceptable losses'. The signatures are from people who are still alive.";
        this.isUnique = true;
    }

    @Override
    public String use() {
        return "The letters reveal a conspiracy spanning decades. The town's leadership " +
                "has been actively covering up supernatural incidents and manipulating residents' memories.";
    }
}
