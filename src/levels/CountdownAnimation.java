package levels;

import biuoop.DrawSurface;
import data.SpriteCollection;
import interfaces.Animation;

import java.awt.Color;

/**
 * display countdown before the level start.
 */
public class CountdownAnimation implements Animation {

    private SpriteCollection sprites;
    private double numOfSeconds;
    private int countFrom;
    private boolean stop;
    private boolean flag;
    private long startTime;

    /**
     * Constructor.
     *
     * @param numOfSeconds 3
     * @param countFrom 3
     * @param gameScreen the sprite's collection of the screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.sprites = gameScreen;
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.flag = true;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (flag) {
            startTime = System.currentTimeMillis();
            flag = false;
        }
        sprites.drawAllOn(d);
        int i = (int) ((((System.currentTimeMillis() - startTime) / 1000) * (numOfSeconds / countFrom)));
        if (countFrom - i == 0) {
            this.stop = true;
        } else {
            d.setColor(Color.cyan);
            d.drawText(380, 300, "" + (countFrom - i), 100);
        }
    }

    @Override
    public boolean shouldStop() { return stop; }
}