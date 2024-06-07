import gameactions.AnimationRunner;
import gameactions.GameFlow;
import interfaces.LevelInformation;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import levels.Level4;

import java.util.ArrayList;
import java.util.List;

/**
 * The main class.
 * Initializes a game, its data, and runs it
 */

public class Main {
    /**
     * The main method.
     * Initializes a game, its data, and runs it
     *
     * @param args a string
     */
    public static void main(String[] args) {
        AnimationRunner ar = new AnimationRunner();
        GameFlow gf = new GameFlow(ar, ar.getGui().getKeyboardSensor());
        List<LevelInformation> levels = new ArrayList<>();
        if (args.length > 0) {
            for (String arg : args) {
                if (arg.length() == 1) {
                    switch (Integer.parseInt(arg)) {
                        case 1:
                            levels.add(new Level1());
                            break;
                        case 2:
                            levels.add(new Level2());
                            break;
                        case 3:
                            levels.add(new Level3());
                            break;
                        case 4:
                            levels.add(new Level4());
                            break;
                        default:
                            break;
                    }
                }
            }
            gf.runLevels(levels);
        }
        if (levels.isEmpty()) {
            levels.add(new Level3());
            levels.add(new Level4());
            levels.add(new Level1());
            levels.add(new Level2());


            gf.runLevels(levels);
        }
        gf.endGame();
    }
}