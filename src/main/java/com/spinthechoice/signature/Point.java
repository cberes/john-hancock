package com.spinthechoice.signature;

import java.util.Objects;

final class Point {
    static final Point MAX_VALUE = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
    static final Point MIN_VALUE = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
    static final Point ORIGIN = new Point(0, 0);

    private final int x, y;

    private Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point add(final Point other) {
        return valueOf(x + other.x, y + other.y);
    }

    public Point negate() {
        return valueOf(-x, -y);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Point)) {
            return false;
        }

        final Point other = (Point) obj;
        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ',' + y + ')';
    }

    static Point valueOf(final int x, final int y) {
        if (x == ORIGIN.x && y == ORIGIN.y) {
            return ORIGIN;
        } else {
            return new Point(x, y);
        }
    }
}
