package gameactions;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * Responsible for game stop's like pause or end.
 */
public class KeyPressStoppableAnimation implements Animation {

    private Animation animation;
    private KeyboardSensor keyboard;
    private String key;

    /**
     * Constructor.
     *
     * @param sensor a KeyBoard sensor
     * @param key a keyboard that influence on the game
     * @param animation an internal animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.keyboard = sensor;
        this.key = key;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        if (this.keyboard.isPressed(this.key)) { return true; }
        return this.animation.shouldStop();
    }
}