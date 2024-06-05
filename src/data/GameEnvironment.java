// ID: 206344814

package data;

import interfaces.Collidable;
import primitives.Line;
import primitives.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of objects that a ball may collide with,
 * and if a particular line collides with one of the objects,
 * the class returns the point of collision closest to the beginning of the line.
 */
public class GameEnvironment {

    // The point of collision closest to the beginning of the line
    private CollisionInfo collisionInfo;
    // A collection of objects that a ball may collide with
    private List<Collidable> collidables;

    /**
     * Constructor. Initializes a new list.
     */
    public GameEnvironment() { this.collidables = new ArrayList<Collidable>(); }

    /**
     * @return the point of collision closest to the beginning of the line
     */
    public CollisionInfo getCollisionInfo() { return this.collisionInfo; }

    /**
     * add the given collidable to the environment.
     *
     * @param c a collidable
     */
    public void addCollidable(Collidable c) { collidables.add(c); }

    /**
     * remove the given collidable from the environment.
     *
     * @param c a collidable
     */
    public void removeCollidable(Collidable c) { this.collidables.remove(c); }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory "how the ball will move without any obstacles"
     * @return the point of collision closest to the beginning of the line
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // list of intersection points
        List<Point> closestPoint = new ArrayList<Point>();
        // list of collidables
        List<Collidable> collidables2 = new ArrayList<Collidable>();
        // If there is a collision, add the collision point
        // and the object with which the line collides with the corresponding lists
        for (Collidable i : collidables) {
            if (trajectory.closestIntersectionToStartOfLine(i.getCollisionRectangle()) != null) {
                closestPoint.add(trajectory.closestIntersectionToStartOfLine(i.getCollisionRectangle()));
                collidables2.add(i);
            }
        }
        // If the list of collision points is not empty,
        // it returns the closest collision point to the beginning of the line,
        // and also returns the object with which this collision happened
        if (!closestPoint.isEmpty()) {
            Point mostClosestPoint = closestPoint.get(0);
            Collidable obstacle = collidables2.get(0);
            for (int i = 1; i < closestPoint.size(); i++) {
                if (trajectory.start().distance(closestPoint.get(i - 1))
                        > trajectory.start().distance(closestPoint.get(i))) {
                    mostClosestPoint = closestPoint.get(i);
                    obstacle = collidables2.get(i);
                }
            }
            this.collisionInfo = new CollisionInfo(mostClosestPoint, obstacle);
            return this.collisionInfo;
        }
        return null;
    }
}
