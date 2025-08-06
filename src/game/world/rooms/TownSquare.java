package game.world.rooms;

import game.entities.Mayor;

public class TownSquare extends Room {
        public TownSquare() {
            super("town_square", "Town Square",
                    "A bustling town square with people going about their daily business.");
            this.detailedDescription = "You emerge in a bustling town square, the sun shining " +
                    "and people going about their day. The door you came through is gone. " +
                    "No one seems to have noticed anything unusual.";
        }

        @Override
        protected void initializeRoom() {
            addNPC(new Mayor());

            examineDescriptions.put("people", "The townspeople seem ordinary, but none acknowledge your sudden appearance.");
            examineDescriptions.put("fountain", "A stone fountain sits in the center, water trickling peacefully.");
            examineDescriptions.put("buildings", "Various shops and buildings surround the square.");
        }
    }


