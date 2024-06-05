package gameactions;

import interfaces.HitListener;
import primitives.Counter;
import sprites.Ball;
import sprites.Block;

/**
 * A BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    // A game
    private GameLevel gameLevel;
    // A counter of the number of blocks that remain
    private Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param gameLevel the current game.
     * @param removedBlocks the number of the blocks in the game.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Increase the number of the blocks in the game by a given number.
     *
     * @param number a number of blocks.
     */
    public void setRemainingBlocks(int number) { remainingBlocks.increase(number); }

    /**
     * @return the number of the blocks in the game.
     */
    public int getRemainingBlocks() { return this.remainingBlocks.getValue(); }


    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // removes this hit listener from the block that hit, removes the block from the game,
        // and decrease the number of the remaining blocks by 1.
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(gameLevel);
        remainingBlocks.decrease(1);
    }
}