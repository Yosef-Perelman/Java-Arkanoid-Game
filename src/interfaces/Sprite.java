package interfaces;

import biuoop.DrawSurface;

/**
 * A sprite interface.
 * Includes two methods: one that draws the object on the screen,
 * and the other that makes the changes dependent on the time.
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     *
     * @param d a DrawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
