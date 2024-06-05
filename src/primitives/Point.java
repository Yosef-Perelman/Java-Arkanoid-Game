package primitives;

/**
 * A point with x and y values.
 */
public class Point {

// The x and y values of the point
    private double x, y;

    /**
     * Constructor.
     *
     * @param x the x value of the point
     * @param y the y value of the point
     */
    public Point(double x, double y) {
    this.x = x;
    this.y = y;
    }

    /**
     * @return the x value of this point
     */
    public double getX() { return this.x; }

    /**
     * @return the y value of this point
     */
    public double getY() { return this.y; }

    /**
     * @param other other point
     * @return the of this point to the other point
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y)));
    }

    /**
     * @param other other point
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) { return (this.x == other.x) && (this.y == other.y); }


}
