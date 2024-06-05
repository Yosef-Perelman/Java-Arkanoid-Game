package interfaces;

import biuoop.DrawSurface;

/**
 * an animation that need to show.
 */
public interface Animation {

    /**
     * run the animation.
     *
     * @param d a drawSurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return if the game need to stop or not.
     */
    boolean shouldStop();
}
