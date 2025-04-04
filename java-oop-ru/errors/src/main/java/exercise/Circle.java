package exercise;

// BEGIN
import java.awt.Point;

public class Circle {
    private Point center;
    private int radius;

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("Радиус не может быть отрицательным");
        }
        return Math.PI * radius * radius;
    }
}
// END
