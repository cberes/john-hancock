package net.seabears.signature;

import java.util.LinkedList;
import java.util.List;

class Curve {
    private final List<Point> points = new LinkedList<>();

    void add(final Point p) {
        points.add(p);
    }

    boolean isEmpty() {
        return points.isEmpty();
    }

    List<Point> getPoints() {
        return points;
    }
}
