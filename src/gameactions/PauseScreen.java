package gameactions;

import biuoop.DrawSurface;
import interfaces.Animation;

/**
 * responsible for stops in the game.
 */
public class PauseScreen implements Animation {

    /**
     * Constructor.
     */
    public PauseScreen() { }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(175, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() { return false; }
}