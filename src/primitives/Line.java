package primitives;

import java.util.List;

/**
 * A line, represented by two points: start and end.
 * The class also includes the midpoint of the line,
 * its slope, and the intersection with the y axis.
 */
public class Line {

    // Three points: start, end and middle
    private Point start, end, middle;
    // The slope of the line and his intersection with the y axis
    private double slope, yintersect;

    /**
     * Constructor 1. Gets two points: start and end, and calculates the midpoint,
     * slope, and intersection with the y axis.
     *
     * @param start the start point of the line
     * @param end the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        // If the slope is not set then the slope and intersection
        // with the y axis are defined as infinity
        if ((start.getX() - end.getX() == 0)) {
            this.slope = Double.POSITIVE_INFINITY;
            this.yintersect = Double.POSITIVE_INFINITY;
        } else {
            this.slope = (start.getY() - end.getY()) / (start.getX() - end.getX());
            this.yintersect = this.start.getY() - this.slope * this.start.getX();
        }
        this.middle = new Point(((start.getX() + end.getX()) / 2), ((start.getY() + end.getY()) / 2));

    }

    /**
     * Constructor 2. Gets the x and y values of the start and end points,
     * and calculates the midpoint, slope, and intersection with the y axis.
     *
     * @param x1 the x value of the start point
     * @param x2 the x value of the end point
     * @param y1 the y value of the start point
     * @param y2 the x value of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
      this.start = new Point(x1, y1);
      this.end = new Point(x2, y2);
        // If the slope is not set then the slope and intersection
        // with the y axis are defined as infinity
      if (x1 - x2 == 0) {
          this.slope = Double.POSITIVE_INFINITY;
          this.yintersect = Double.POSITIVE_INFINITY;
      } else {
          this.slope = (y1 - y2) / (x1 - x2);
          this.yintersect = y1 - this.slope * x1;
      }
      this.middle = new Point(((x1 + x2) / 2), ((y1 + y2) / 2));
    }

    /**
     * @return the length of the line
     */
    public double length() { return this.start.distance(this.end); }

    /**
     * @return the midpoint of the line
     */
    public Point middle() { return this.middle; }

    /**
     * @return the start point of the line
     */
    public Point start() { return this.start; }

    /**
     * @return the end point of the line
     */
    public Point end() { return this.end; }

    /**
     * Check whether two lines intersect by checking if their slope is different,
     * and if so, it checks whether the x and y value of their intersect point is in the lines.
     *
     * @param other the line we compare
     * @return 'true' if the lines intersect, 'false' otherwise
     */
    public boolean isIntersecting(Line other) {
        // If the slopes are different
        if (this.slope != other.slope && this.slope != -other.slope) {
            // the values of the intersect point
            double x, y;
            // If the slope of one of the lines is not set
            if (this.slope == Double.POSITIVE_INFINITY) {
                x = this.start.getX();
                y = other.slope * x + other.yintersect;
            } else if (other.slope == Double.POSITIVE_INFINITY) {
                x = other.start.getX();
                y = this.slope * x + this.yintersect;
            } else {
                // Calculate the X value by dividing the intersection of one line with the y axis
                // less the intersection of the other line with the y axis divided by the slope of
                // one line less the slope of the other
                x = (other.yintersect - this.yintersect) / (this.slope - other.slope);
                // Calculate the y value by placing the x value we found in the straight equation of one of the lines
                y = this.slope * x + this.yintersect;
            }
            // Finds the maximum and minimum x and y values of both lines
            double minX1 = Math.min(this.start.getX(), this.end.getX());
            double maxX1 = Math.max(this.start.getX(), this.end.getX());
            double minX2 = Math.min(other.start.getX(), other.end.getX());
            double maxX2 = Math.max(other.start.getX(), other.end.getX());
            double minY1 = Math.min(this.start.getY(), this.end.getY());
            double maxY1 = Math.max(this.start.getY(), this.end.getY());
            double minY2 = Math.min(other.start.getY(), other.end.getY());
            double maxY2 = Math.max(other.start.getY(), other.end.getY());
            // Check if the intersect point is in the lines,
            // If so, returns 'true', otherwise return 'false'
            return minX1 <= x && x <= maxX1 && minX2 <= x && x <= maxX2
                    && minY1 <= y && y <= maxY1 && minY2 <= y && y <= maxY2;
        }
        // if the slopes are even, return 'true' only if the intersect is at the edge,
        // otherwise return 'false'
        if (this.slope == other.slope) {
            return this.start.equals(other.end) || this.end.equals(other.start);
        }
        if (this.slope == -other.slope) {
            return this.start.equals(other.start) || this.end.equals(other.end);
        }
        return false;
    }

    /**
     *  Find the intersection point if the lines intersect, and null otherwise.
     *
     * @param other the line we compare
     * @return the intersection point if the lines intersect, and null otherwise
     */
    //
    public Point interSectionWith(Line other) {
        // check if the lines intersect
        if (this.isIntersecting(other)) {
            // If the slopes are different
            if (this.slope != other.slope && this.slope != -other.slope) {
                // The values of the intersect point
                double x, y;
                // If the slope of one of the lines is not set
                if (this.slope == Double.POSITIVE_INFINITY) {
                    x = this.start.getX();
                    y = other.slope * x + other.yintersect;
                } else if (other.slope == Double.POSITIVE_INFINITY) {
                    x = other.start.getX();
                    y = this.slope * x + this.yintersect;
                } else {
                    // Calculate the X value by dividing the intersection of one line with the y axis
                    // less the intersection of the other line with the y axis divided by the slope of
                    // one line less the slope of the other
                    x = (other.yintersect - this.yintersect) / (this.slope - other.slope);
                    // Calculate the y value by placing the x value we found in
                    // the straight equation of one of the lines
                    y = this.slope * x + this.yintersect;
                }
                return new Point(x, y);
            } else if (this.slope == other.slope) {
                if (this.start.equals(other.end)) {
                    return this.start;
                }
                return this.end;
            } else if (this.slope == -other.slope) {
                if (this.start.equals(other.start)) {
                    return this.start;
                }
                return this.end;
            }
        }
        // if the lines not intersect, return null
        return null;
    }

    /**
     * Checks if two lines are equal by comparing their start and end point.
     *
     * @param other a line
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.start == other.start) && (this.end == other.end);
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect a rectangle
     * @return the closest Intersection To Start Of primitives.Line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // List of intersection points between the line and rectangle
        List<Point> list = rect.intersectionPoints(this);
       if (list.isEmpty()) {
           return null;
       }
       // The closest point to the start of the line
       Point closestPoint = list.get(0);
       // A loop that goes through all the points in the list and puts in "closestPoint"
       // the closest point to the beginning of the line
       for (int i = 1; i < list.size(); i++) {
            if (this.start.distance(list.get(i - 1)) > this.start.distance(list.get(i))) {
                closestPoint = list.get(i);
           }
       }
       return closestPoint;
    }
}
