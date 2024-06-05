// ID: 206344814

package gameactions;

import interfaces.HitListener;
import primitives.Counter;
import sprites.Ball;
import sprites.Block;

/**
 * A BallRemover is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that remain.
 */
public class BallRemover implements HitListener {

    // A game
    private GameLevel gameLevel;
    // A number of balls that remain
    private Counter remainingBalls;

    /**
     * Constructor.
     *
     * @param gameLevel the current game.
     * @param ballsNumber the number of the balls in the game.
     */
    public BallRemover(GameLevel gameLevel, Counter ballsNumber) {
        this.gameLevel = gameLevel;
        this.remainingBalls = ballsNumber;
    }

    /**
     * Increase the number of the balls in the game by a given number.
     *
     * @param number a number of balls.
     */
    public void setRemainingBalls(int number) { remainingBalls.increase(number); }

    /**
     * @return the number of the balls in the game.
     */
    public int getRemainingBalls() { return this.remainingBalls.getValue(); }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Removes the hitter ball
        hitter.removeFromGame(this.gameLevel);
        // Decrease the number of the remaining balls by 1
        remainingBalls.decrease(1);
    }
}
