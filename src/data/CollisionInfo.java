package data;

import interfaces.Collidable;
import primitives.Point;

/**
 * A class that contains the point at which the ball collides with the object,
 * and the object itself.
 */
public class CollisionInfo {

    // the intersection point
    private Point collisionPoint;
    // the object with which the ball collides
    private Collidable obstacle;

    /**
     * Constructor.
     *
     * @param point the intersection point
     * @param obstacle the object with which the ball collides
     */
    public CollisionInfo(Point point, Collidable obstacle) {
        this.collisionPoint = point;
        this.obstacle = obstacle;
    }

    /**
     * @return the intersection point
     */
    public Point collisionPoint() { return this.collisionPoint; }

    /**
     * Initializes the intersection point.
     *
     * @param point the intersection point
     */
    public void setCollisionPoint(Point point) { this.collisionPoint = point; }

    //

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() { return this.obstacle; }

    /**
     * Initializes the object with which the ball collides.
     *
     * @param rect the object with which the ball collides
     */
    public void setObstacle(Collidable rect) { this.obstacle = rect; }
}
