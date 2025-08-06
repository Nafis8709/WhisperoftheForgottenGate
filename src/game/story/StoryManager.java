package game.story;

import java.util.Map;
import java.util.HashMap;

public class StoryManager {
    private Map<String, Boolean> storyFlags;
    private String currentChapter;
    private int actNumber;

    public StoryManager() {
        this.storyFlags = new HashMap<>();
        this.currentChapter = "awakening";
        this.actNumber = 1;
        initializeStoryFlags();
    }

    private void initializeStoryFlags() {
        // Act I flags
        storyFlags.put("found_key", false);
        storyFlags.put("used_door", false);
        storyFlags.put("met_mayor", false);
        storyFlags.put("got_letter", false);
        storyFlags.put("got_coin", false);
        storyFlags.put("found_revolver", false);

        // Act II flags
        storyFlags.put("got_mission", false);
        storyFlags.put("entered_forest", false);
        storyFlags.put("found_linna", false);

        // Act III flags
        storyFlags.put("solved_shrine_puzzle", false);
        storyFlags.put("rescued_linna", false);
        storyFlags.put("linna_in_town", false);

        // Act IV flags
        storyFlags.put("found_documents", false);
        storyFlags.put("found_map", false);
        storyFlags.put("found_letters", false);
        storyFlags.put("elias_truth_revealed", false);
        storyFlags.put("reality_breaking", false);

        // Act V flags
        storyFlags.put("entered_catacombs", false);
        storyFlags.put("reached_gate", false);
        storyFlags.put("final_choice_made", false);
        storyFlags.put("game_reset", false);
    }

    public void initializeStory() {

        currentChapter = "awakening";
        actNumber = 1;
    }

    public boolean getFlag(String flagName) {
        return storyFlags.getOrDefault(flagName, false);
    }

    public void setFlag(String flagName, boolean value) {
        storyFlags.put(flagName, value);
        checkStoryProgression();
    }

    private void checkStoryProgression() {
        if (actNumber == 1 && allActIFlagsComplete()) {
            actNumber = 2;
            currentChapter = "the_weapon";
        } else if (actNumber == 2 && allActIIFlagsComplete()) {
            actNumber = 3;
            currentChapter = "the_girl_and_voices";
        } else if (actNumber == 3 && allActIIIFlagsComplete()) {
            actNumber = 4;
            currentChapter = "cracks_in_facade";
        } else if (actNumber == 4 && allActIVFlagsComplete()) {
            actNumber = 5;
            currentChapter = "truth_beneath";
        }

    }

    private boolean allActIFlagsComplete() {
        return getFlag("found_key") && getFlag("met_mayor") &&
                getFlag("got_coin") && getFlag("found_revolver");
    }
    private boolean allActIIFlagsComplete() {
        return getFlag("got_mission") && getFlag("entered_forest");
    }
    private boolean allActIIIFlagsComplete() {
        return getFlag("rescued_linna") && getFlag("linna_in_town");
    }

    private boolean allActIVFlagsComplete() {
        return getFlag("found_documents") && getFlag("found_letters") &&
                getFlag("elias_truth_revealed");
    }


    public String getCurrentChapter() {
        return currentChapter;
    }
    public int getActNumber() {
        return actNumber;
    }
}
