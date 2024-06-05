package data;

import biuoop.DrawSurface;
import interfaces.Sprite;
import primitives.Counter;

import java.awt.Color;

/**
 * A ScoreIndicator is in charge of displaying the current score.
 */
public class ScoreIndicator implements Sprite {

    // the current score
    private Counter score;
    private String levelName;

    /**
     * Constuctor.
     *
     * @param score a score.
     * @param levelName a string of the level name.
     */
    public ScoreIndicator(Counter score, String levelName) {
        this.levelName = levelName;
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.gray);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.black);
        d.drawText(385, 17, "score:" + score.getValue(), 15);
        d.drawText(585, 17, "level name: " + this.levelName, 15);
    }

    @Override
    public void timePassed() {
    }
}
