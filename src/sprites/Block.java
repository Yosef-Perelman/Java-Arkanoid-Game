// ID: 206344814

package sprites;

import biuoop.DrawSurface;
import gameactions.GameLevel;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import primitives.Point;
import primitives.Rectangle;
import primitives.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A block. The class includes a rectangle and his color.
 * The class also has a function that changes the direction of the ball
 * according to where it hit the rectangle.
 *
 * The class implements the collidable and sprite interfaces.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    // A rectangle and his color
    private Rectangle rect;
    private Color color;
    // A list of hit listeners
    private List<HitListener> hitListeners;

    /**
     * Constructor.
     *
     * @param rect a rectangle
     * @param color a color
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * @return the object color
     */
    public java.awt.Color getCollisionRectangleColor() { return this.color; }

    @Override
    public Rectangle getCollisionRectangle() { return this.rect; }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        // Compares the intersection point to the sides of the rectangle.
        // If the X value equals one of the sides of the rectangle then the hit is left or right,
        // and the horizontal direction needs to be changed.
        // If the y value is equal to the upper or lower side of the rectangle then the hit is up or down,
        // and the vertical direction needs to be changed
        // In every corner, it checks which direction the ball came from, and that sets the new velocity.

        // Hit the corners

        // up left point
        if ((collisionPoint.getY() == this.rect.getUpperLeft().getY())
                && (collisionPoint.getX() == this.rect.getUpperLeft().getX())) {
            if (currentVelocity.getDy() < 0) {
                currentVelocity.setDx(-currentVelocity.getDx());
            } else if (currentVelocity.getDx() < 0) {
                currentVelocity.setDy(-currentVelocity.getDy());
            } else {
                currentVelocity.setDy(-currentVelocity.getDy());
                currentVelocity.setDx(-currentVelocity.getDx());
            }
        }
        // down left point
        if ((collisionPoint.getY() == this.rect.getUpperLeft().getY() + this.rect.getHeight())
                && (collisionPoint.getX() == this.rect.getUpperLeft().getX())) {
            if (currentVelocity.getDx() > 0 && currentVelocity.getDy() > 0) {
                currentVelocity.setDx(-currentVelocity.getDx());
            } else if (currentVelocity.getDx() < 0 && currentVelocity.getDy() < 0) {
                currentVelocity.setDy(-currentVelocity.getDy());
            } else {
                currentVelocity.setDy(-currentVelocity.getDy());
                currentVelocity.setDx(-currentVelocity.getDx());
            }
        }
        // up right point
        if ((collisionPoint.getY() == this.rect.getUpperLeft().getY())
                && (collisionPoint.getX() == this.rect.getUpperLeft().getX() + this.rect.getWidth())) {
            if (currentVelocity.getDx() > 0 && currentVelocity.getDy() > 0) {
                currentVelocity.setDy(-currentVelocity.getDy());
            } else if (currentVelocity.getDx() < 0 && currentVelocity.getDy() < 0) {
                currentVelocity.setDx(-currentVelocity.getDx());
            } else {
                currentVelocity.setDy(-currentVelocity.getDy());
                currentVelocity.setDx(-currentVelocity.getDx());
            }
        }
        // down right point
        if ((collisionPoint.getY() == this.rect.getUpperLeft().getY() + this.rect.getHeight())
                && (collisionPoint.getX() == this.rect.getUpperLeft().getX() + this.rect.getWidth())) {
            if (currentVelocity.getDx() < 0 && currentVelocity.getDy() > 0) {
                currentVelocity.setDx(-currentVelocity.getDx());
            } else if (currentVelocity.getDx() > 0 && currentVelocity.getDy() < 0) {
                currentVelocity.setDy(-currentVelocity.getDy());
            } else {
                currentVelocity.setDy(-currentVelocity.getDy());
                currentVelocity.setDx(-currentVelocity.getDx());
            }
        }

        // Hit with the sides (not with the corners)
        if ((collisionPoint.getY() == this.rect.getUpperLeft().getY())
                || (collisionPoint.getY() == (this.rect.getUpperLeft().getY() + this.rect.getHeight()))) {
            currentVelocity.setDy(-currentVelocity.getDy());
        } else {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        return currentVelocity;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        // draw the rectangle and a frame
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) (this.rect.getWidth()), (int) (this.rect.getHeight()));
        surface.setColor(Color.black);
        surface.drawRectangle((int) (this.rect.getUpperLeft().getX()), (int) (this.rect.getUpperLeft().getY()),
                (int) ((this.rect.getWidth())), (int) ((this.rect.getHeight())));
    }

    @Override
    public void timePassed() {
    }

    /**
     * Adds the ball to the game's sprite collection, and to game environment.
     *
     * @param g a game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Removes the ball from the game's sprite collection, and from the game environment.
     *
     * @param gameLevel a game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify all hit listeners about a hit event.
     *
     * @param hitter the ball that hit.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
