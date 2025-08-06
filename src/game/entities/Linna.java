package game.entities;

public class Linna extends NPC {
        private boolean isRescued = false;
        private boolean inTown = false;
        private int corruptionLevel = 1;

    public Linna() {
            super("Linna", "A young scout with wild eyes and an unsettling presence.");
        }

        @Override
        protected void initializeDialogue() {
            dialogue.add("The whispers... they never stop. Do you hear them too?");
            dialogue.add("This isn't the town I remember. Everyone feels... hollow.");
            dialogue.add("The Gate calls to me. I can see it in my dreams.");
            dialogue.add("We're all puppets dancing to its tune. Don't you see?");
            dialogue.add("The truth is beneath. It's always been beneath.");
        }

        @Override
        public String interact() {
            if (!isRescued) {
                isRescued = true;
                return "Linna looks at you with recognition, but her eyes are wrong. " +
                        "\"You... you're real, aren't you? Not like the others. " +
                        "Help me get back to town, but beware - nothing is as it seems.\"";
            }

            if (inTown && corruptionLevel < dialogue.size()) {
                return dialogue.get(corruptionLevel - 1);
            }

            return "Linna stares through you, muttering about whispers and gates.";
        }

        public void increaseCorruption() {
            if (corruptionLevel < dialogue.size()) corruptionLevel++;
        }

        public boolean isRescued() { return isRescued; }
        public void setInTown(boolean inTown) { this.inTown = inTown; }
        public int getCorruptionLevel() { return corruptionLevel; }
}
