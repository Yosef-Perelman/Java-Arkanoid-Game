package sprites;

import biuoop.DrawSurface;
import gameactions.GameLevel;
import data.GameEnvironment;
import interfaces.Sprite;
import primitives.Line;
import primitives.Point;
import primitives.Velocity;

import java.awt.Color;

/**
 * A ball. The class includes the midpoint of the ball, its radius, its color, and its velocity.
 * The class also includes a method that moves the ball by changing its midpoint,
 * and a method that draws the ball on the screen.
 *
 * The class implement the interfaces.Sprite interface.
 */
public class Ball implements Sprite {

    // The center point of the ball
    private Point center;
    // The radius of the ball
    private int r;
    // The color of the ball
    private Color color;
    // The velocity of the ball
    private Velocity velocity;
    // A list of object in a game
    private GameEnvironment gameEnvironment;

    /**
     * Constructor 1.
     * Initializes the midpoint, radius and color of the ball
     *
     * @param x the x value of the center point of the ball
     * @param y the y value of the center point of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * Constructor 2.
     * Initializes the midpoint, radius and color of the ball
     *
     * @param center the center point of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * @return the x value of the center point of the ball as an int variable
     */
    public int getX() { return (int) this.center.getX(); }

    /**
     * @return the y value of the center point of the ball as an int variable
     */
    public int getY() { return (int) this.center.getY(); }

    /**
     * @return the radius of the ball
     */
    public int getSize() { return this.r; }

    /**
     * @return the color of the ball
     */
    public java.awt.Color getColor() { return this.color; }

    /**
     * @return the center point of the ball
     */
    public Point getCenter() { return this.center; }

    /**
     * Initializes the speed of the ball.
     *
     * @param v the velocity of the ball
     */
    public void setVelocity(Velocity v) { this.velocity = v; }

    /**
     * Initializes the movement of the ball.
     *
     * @param dx the angle of the movement of the ball
     * @param dy the speed of the movement of the ball
     */
    public void setVelocity(double dx, double dy) { this.velocity = new Velocity(dx, dy); }

    /**
     * @return the velocity of the ball
     */
    public Velocity getVelocity() { return this.velocity; }

    /**
     * Initializes the movement of the ball.
     *
     * @param environment a list of collidables
     */
    public void setGameEnvironment(GameEnvironment environment) { this.gameEnvironment = environment; }

    /**
     * @return the game environment of the ball
     */
    public GameEnvironment getGameEnvironment() { return this.gameEnvironment; }

    /**
     * Moves the ball by changing its center point.
     * If the ball hits something, it changes its direction.
     */
    public void moveOneStep() {
        // A line that the ball should advance
        Line trajectory;
        // Checks all possible directions of the ball
        // (don't need to check the direction that the ball came from)
        for (int i = 0; i < 3; i++) {
            trajectory = new Line(center.getX(), center.getY(),
                    center.getX() + this.velocity.getDx(), center.getY() + this.velocity.getDy());
            // If the ball does not hit anything in the way, it passes from the start of the line to the
            // end of the line.
            // Otherwise, change the direction of the ball
            if (gameEnvironment.getClosestCollision(trajectory) != null) {
                this.setVelocity(gameEnvironment.getCollisionInfo().collisionObject().
                        hit(this, gameEnvironment.getCollisionInfo().collisionPoint(), this.velocity));
            }
        }
        // Move the ball by changing his midpoint.
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Adds the ball to the game's sprite collection.
     *
     * @param g a game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Removes the ball to the game's sprite collection.
     *
     * @param g a game
     */
    public void removeFromGame(GameLevel g) { g.removeSprite(this); }
}
