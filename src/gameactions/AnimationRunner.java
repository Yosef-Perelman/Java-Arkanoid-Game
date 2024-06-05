package gameactions;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 * this class run the game.
 */
public class AnimationRunner {

    // the size of the screen
    private static final int SURFACE_WIDTH = 800;
    private static final int SURFACE_HEIGHT = 600;

    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor.
     */
    public AnimationRunner() {
        this.gui = new biuoop.GUI("Arkanoid", SURFACE_WIDTH, SURFACE_HEIGHT);
        this.sleeper = new biuoop.Sleeper();
        this.framesPerSecond = 60;
    }

    /**
     * @return the gui of the game.
     */
    public GUI getGui() { return this.gui; }

    /**
     * run the animation.
     *
     * @param animation an animation.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
