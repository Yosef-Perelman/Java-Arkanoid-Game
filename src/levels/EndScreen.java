package levels;

import biuoop.DrawSurface;
import interfaces.Animation;

import java.awt.Color;

/**
 * the end screen animation.
 */
public class EndScreen implements Animation {

    private int score;
    private boolean winCheck;

    /**
     * Constructor.
     *
     * @param score the score
     * @param winCheck check if win the level
     */
    public EndScreen(int score, boolean winCheck) {
        this.score = score;
        this.winCheck = winCheck;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.gray);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.black);
        d.drawText(385, 17, "score:" + this.score, 15);
        d.drawText(585, 17, "end screen", 15);
        d.fillRectangle(25, 40, 750, 560);
        d.setColor(Color.yellow);
        d.fillRectangle(0, 20, 800, 20);
        d.fillRectangle(0, 40, 25, 560);
        d.fillRectangle(775, 40, 25, 560);
        d.setColor(Color.red);
        if (!winCheck) {
            d.drawText(190, 300, "Game Over. Your score is :" + this.score, 30);
        } else {
            d.drawText(200, 300, "You Win!. Your score is :" + this.score, 30);
        }
    }

    @Override
    public boolean shouldStop() { return false; }
}
