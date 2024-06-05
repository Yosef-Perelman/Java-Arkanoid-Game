package gameactions;

import interfaces.HitListener;
import primitives.Counter;
import sprites.Ball;
import sprites.Block;

/**
 * A ScoreTrackingListener is a interfaces.HitListener that in charge of update the score counter
 * when blocks are being hit and removed.
 */
public class ScoreTrackingListener implements HitListener {

    // The current game score counter
    private Counter currentScore;
    // Remove block score
    private static final int REMOVE_BLOCK_SCORE = 5;

    /**
     * Constructor.
     *
     * @param scoreCounter the current game score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.addScore(REMOVE_BLOCK_SCORE);
    }

    /**
     * Add the score to the score counter.
     *
     * @param score a number.
     */
    public void addScore(int score) {
        currentScore.increase(score);
    }
}