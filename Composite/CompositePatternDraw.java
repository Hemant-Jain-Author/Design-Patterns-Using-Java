import java.util.HashSet;
import java.util.Set;

// Shape
abstract class Shape {
    public abstract void move(int x, int y);
    public abstract String draw();
}

// Rectangle
class Rectangle extends Shape {
    private int x, y, length, breadth;

    public Rectangle(int x, int y, int length, int breadth) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.breadth = breadth;
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public String draw() {
        System.out.println("Draw a Rectangle at (" + x + ", " + y + ").");
        return "<Rectangle>";
    }
}

// Circle
class Circle extends Shape {
    private int x, y, radius;

    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public String draw() {
        System.out.println("Draw a Circle of radius " + radius + " at (" + x + ", " + y + ").");
        return "<Circle>";
    }
}

// CompoundShape
class CompoundShape extends Shape {
    private Set<Shape> children = new HashSet<>();

    public void add(Shape child) {
        children.add(child);
    }

    public void remove(Shape child) {
        children.remove(child);
    }

    @Override
    public void move(int x, int y) {
        for (Shape child : children) {
            child.move(x, y);
        }
    }

    @Override
    public String draw() {
        String st = "Shapes(";
        for (Shape child : children) {
            st += child.draw();
        }
        st += ")";
        return st;
    }
}

// Client code
public class CompositePatternDraw {
    public static void main(String[] args) {
        CompoundShape all = new CompoundShape();
        all.add(new Rectangle(1, 2, 1, 2));
        all.add(new Circle(5, 3, 10));

        CompoundShape group = new CompoundShape();
        group.add(new Rectangle(5, 7, 1, 2));
        group.add(new Circle(2, 1, 2));

        all.add(group);
        System.out.println(all.draw());
    }
}

/*
Draw a Circle of radius 10 at (5, 3).
Draw a Rectangle at (5, 7).
Draw a Circle of radius 2 at (2, 1).
Draw a Rectangle at (1, 2).
Shapes(<Circle>Shapes(<Rectangle><Circle>)<Rectangle>)
*/
