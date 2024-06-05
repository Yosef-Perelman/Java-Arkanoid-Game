package interfaces;

import primitives.Point;
import primitives.Rectangle;
import primitives.Velocity;
import sprites.Ball;

/**
 * An interface.
 * Includes two functions: one that returns the object with which the ball collides,
 * and one that returns the new direction of the ball that collides
 */
public interface Collidable {

    /**
     * @return the rectangle we collided with
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     *
     * @param collisionPoint  the point of intersection between the rectangle and the ball
     * @param currentVelocity the current velocity of a ball
     * @param hitter          the ball that hit
     * @return the new velocity expected after the hit (based on the force the object inflicted on the ball).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}