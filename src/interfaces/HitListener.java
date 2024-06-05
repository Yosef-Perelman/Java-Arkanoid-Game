package interfaces;

import sprites.Ball;
import sprites.Block;

/**
 * An interface of objects that need to know that a collision occurred.
 */
public interface HitListener {
    //

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the object that hit.
     * @param hitter the sprites.Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
