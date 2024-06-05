package interfaces;

import primitives.Velocity;
import sprites.Block;
import biuoop.DrawSurface;

import java.util.List;

/**
 * Data of a level.
 */
public interface LevelInformation {

    /**
     * @return number of the balls.
     */
    int numberOfBalls();

    /**
     * @return The initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the speed of the paddle
     */
    int paddleSpeed();

    /**
     * @return the width of the paddle
     */
    int paddleWidth();

    /**
     * @return the level name
     */
    String levelName();
    // Returns a sprite with the background of the level

    /**
     * @return the level background
     */
    Sprite getBackground();
    //

    /**
     * @return The Blocks that make up this level, each block contains its size, color and location.
     */
    List<Block> blocks();

    /**
     * @return Number of blocks that should be removed
     */
    int numberOfBlocksToRemove();

    /**
    *
    */
    public void drawBackground(DrawSurface d);
}
