// ID: 206344814

package data;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of sprites.
 */
public class SpriteCollection {

    // A list of sprites
    private List<Sprite> sprites;

    /**
     * Constructor.
     */
    public SpriteCollection() { this.sprites = new ArrayList<Sprite>(); }

    /**
     * add a sprite to the list.
     *
     * @param s a sprite
     */
    public void addSprite(Sprite s) { sprites.add(s); }

    /**
     * remove a sprite from the list.
     *
     * @param s a sprite
     */
    public void removeSprite(Sprite s) { sprites.remove(s); }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> newList = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : newList) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d a drawSurface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}
