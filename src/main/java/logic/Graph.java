package logic;

import java.util.ArrayList;

public class Graph{

    private final ArrayList<Shape> shapes;

    public Graph( ArrayList<Shape> shapes ) {
        this.shapes = shapes;
    }

    public boolean isInGraph(double x, double y) {
        for (Shape shape : shapes) {
            if (shape.isInShape(x,y)) return true;
        }
        return false;
    }
}