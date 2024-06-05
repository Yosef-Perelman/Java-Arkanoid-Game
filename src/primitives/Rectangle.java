package primitives;

import java.util.ArrayList;
import java.util.List;

/**
 * A rectangle. The class includes the top left point of the rectangle, and its length and width.
 *  The department can also check if a particular line cuts the rectangle by comparing it to its sides.
 */
public class Rectangle {

    // the top left point of the rectangle
    private Point upperLeft;
    // the width and height of the rectangle
    private double width, height;

    // Create a new rectangle with location and width/height.

    /**
     * Constructor.
     *
     * @param upperLeft the top left point of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * @return the width of the rectangle
     */
    public double getWidth() { return this.width; }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() { return this.height; }

    /**
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() { return this.upperLeft; }

    /**
     * Initializes the upper-left point of the rectangle.
     *
     * @param point a point
     */
    public void setUpperLeft(Point point) { this.upperLeft = point; }

    /**
     * Creates an array of points of the four corners of the rectangle.
     *
     * @param point the upper-left point of the rectangle
     * @return an array of points
     */
    public Point[] createPoints(Point point) {
    Point[] points = new Point[4];
    points[0] = point;
    points[1] = new Point(point.getX() + this.width, point.getY());
    points[2] = new Point(point.getX() + this.width, point.getY() + this.height);
    points[3] = new Point(point.getX(), point.getY() + this.height);
    return points;
    }

    /**
     * Creates an array of lines of the four sides of the rectangle.
     *
     * @param points an array of point type variables of the four corners of the rectangle
     * @return an array of lines
     */
    public static Line[] createLines(Point[] points) {
        Line[] lines = new Line[4];
        lines[0] = new Line(points[0], points[1]);
        lines[1] = new Line(points[1], points[2]);
        lines[2] = new Line(points[2], points[3]);
        lines[3] = new Line(points[3], points[0]);
        return lines;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line a line.
     * @return a list of intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<Point>();
        // an array of lines of the four sides of the rectangle
        Line[] lines = createLines(createPoints(upperLeft));
        for (Line i : lines) {
            if (i.isIntersecting(line)) {
                list.add(i.interSectionWith(line));
            }
        }
        return list;
    }
}
