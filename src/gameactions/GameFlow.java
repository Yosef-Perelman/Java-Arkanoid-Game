package gameactions;

import biuoop.KeyboardSensor;
import interfaces.Animation;
import interfaces.LevelInformation;
import levels.EndScreen;
import primitives.Counter;

import java.util.List;

/**
 * Responsible for th flow of the game and the levels.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private boolean winCheck;

    /**
     * Constructor.
     *
     * @param ar an animation runner
     * @param ks a keyboard sensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter();
        this.winCheck = true;
    }

    /**
     * @return the score.
     */
    public Counter getScore() { return this.score; }

    /**
     * increase the score.
     *
     * @param points points that need to add to the score.
     */
    public void setScore(int points) { this.score.increase(points); }

    /**
     * run the levels one by one.
     *
     * @param levels a list of requested levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.animationRunner,
                    this.keyboardSensor, this.score, this.winCheck);

            level.initialize();

            while (level.getBlockRemover().getRemainingBlocks() > 0 && level.getBallRemover().getRemainingBalls() > 0) {
                level.run();
            }

            // if the level end without win the "winCheck" change to FALSE.
            this.winCheck = level.getBlocksCheck();
            if (!winCheck) { break; }
        }
    }

    /**
     * run the end screen.
     */
    public void endGame() {
        Animation endScreen = new KeyPressStoppableAnimation(
                this.keyboardSensor, KeyboardSensor.SPACE_KEY, new EndScreen(this.score.getValue(), winCheck));
        this.animationRunner.run(endScreen);
        this.animationRunner.getGui().close();
    }
}
