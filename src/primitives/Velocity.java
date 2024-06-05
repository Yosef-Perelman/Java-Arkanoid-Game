package primitives;

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {

    // the magnitude of the center point values change
    private double dx, dy;

    /**
     * Constructor 1.
     *
     * @param dx the magnitude of the center point x value change
     * @param dy the magnitude of the center point y value change
     */
    public Velocity(double dx, double dy) {
    this.dx = dx;
    this.dy = dy;
    }

    /**
     * Constructor 2. This method is overloading constructor 1.
     * This method gets angle and velocity and calculates the magnitude
     * of the change that need to be in the x and y values
     *
     * @param angle direction of the movement
     * @param speed speed of the movement
     * @return velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        return new Velocity(speed * Math.sin(Math.toRadians(angle)), -speed * Math.cos(Math.toRadians(angle)));
    }

    /**
     * @return the dx value
     */
    public double getDx() { return this.dx; }

    /**
     * @return the dy value
     */
    public double getDy() { return this.dy; }

    /**
     * @param dX the magnitude of the center point x value change
     */
    public void setDx(double dX) { this.dx = dX; }

    /**
     * @param dY the magnitude of the center point y value change
     */
    public void setDy(double dY) { this.dy = dY; }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p a center point of ball
     * @return point
     */
     public Point applyToPoint(Point p) { return new Point(p.getX() + dx, p.getY() + dy); }
}