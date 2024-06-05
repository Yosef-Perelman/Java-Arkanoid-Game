// ID: 206344814

package sprites;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gameactions.GameLevel;
import interfaces.Collidable;
import interfaces.Sprite;
import primitives.Point;
import primitives.Rectangle;
import primitives.Velocity;

import java.awt.Color;

/**
 * A class that implement the sprite and collidable implements.
 * The class includes a variable of motion, and a rectangle and his color.
 */
public class Paddle implements Sprite, Collidable {

    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private Color color;
    private int paddlespeed;
    private int width;

    /**
     * Constructor.
     *
     * @param rect a rectangle.
     * @param color the rectangle's color
     * @param g an object of screen
     * @param width paddle width
     * @param speed paddle speed
     */
    public Paddle(Rectangle rect, java.awt.Color color, GUI g, int speed, int width) {
        keyboard = g.getKeyboardSensor();
        this.rect = rect;
        this.color = color;
        this.paddlespeed = speed;
        this.width = width;
    }

    /**
     * Pressing the left arrow button decreases the x value of the top left corner of the paddle by eight,
     * as long as the paddle does not exceed the boundary of the screen.
     */
    public void moveLeft() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            if (this.rect.getUpperLeft().getX() - paddlespeed >= 25) {
                this.rect.setUpperLeft(new Point(this.rect.getUpperLeft().getX() - paddlespeed,
                        this.rect.getUpperLeft().getY()));
            } else {
                this.rect.setUpperLeft(new Point(25,
                        this.rect.getUpperLeft().getY()));
            }
        }
    }

    /**
     * Pressing the right arrow button increases the x value of the top left corner of the paddle by eight,
     * as long as the paddle does not exceed the boundary of the screen.
     */
    public void moveRight() {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            if (this.rect.getUpperLeft().getX() + width + paddlespeed <= 775) {
                this.rect.setUpperLeft(new Point(this.rect.getUpperLeft().getX() + paddlespeed,
                        this.rect.getUpperLeft().getY()));
            } else {
                this.rect.setUpperLeft(new Point(775 - width,
                        this.rect.getUpperLeft().getY()));
            }
        }
    }

    @Override
    public void timePassed() {
        this.moveLeft();
        this.moveRight();
    }

    @Override
    public void drawOn(DrawSurface d)  {
        d.setColor(this.color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) (this.rect.getWidth()), (int) (this.rect.getHeight()));
        d.setColor(Color.black);
        d.drawRectangle((int) (this.rect.getUpperLeft().getX()), (int) (this.rect.getUpperLeft().getY()),
                (int) ((this.rect.getWidth())), (int) ((this.rect.getHeight())));
    }

    /**
     * @return the paddle color
     */
    public java.awt.Color getCollisionRectangleColor() { return this.color; }

    @Override
    public Rectangle getCollisionRectangle() { return this.rect; }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double angle, speed = 8;
        // Divide the paddle into five sections, each changing the angle of the ball differently
        if (collisionPoint.getY() == this.rect.getUpperLeft().getY()) {
            if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + (width / 5)) {
                angle = 300;
                currentVelocity = Velocity.fromAngleAndSpeed(angle, speed);
                return currentVelocity;
            }
            if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + ((width / 5) * 2)) {
                angle = 330;
                currentVelocity = Velocity.fromAngleAndSpeed(angle, speed);
                return currentVelocity;
            }
            if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + ((width / 5) * 3)) {
                currentVelocity.setDy(-currentVelocity.getDy());
                return currentVelocity;
            }
            if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + ((width / 5) * 4)) {
                angle = 30;
                currentVelocity = Velocity.fromAngleAndSpeed(angle, speed);
                return currentVelocity;
            }
            angle = 60;
            currentVelocity = Velocity.fromAngleAndSpeed(angle, speed);
            return currentVelocity;
        }
        // if the ball hit the side of the paddle
        currentVelocity.setDx(-currentVelocity.getDx());
        return currentVelocity;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g a game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}