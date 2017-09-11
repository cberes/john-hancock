package net.seabears.signature;

import java.util.Objects;

final class Point {
    static final Point MAX_VALUE = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
    static final Point MIN_VALUE = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
    static final Point EMPTY = new Point(0, 0);
    static final Point PEN_UP = new Point(-1, -1);

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
        if (x == EMPTY.x && y == EMPTY.y) {
            return EMPTY;
        } else if (x == PEN_UP.x && y == PEN_UP.y) {
            return PEN_UP;
        } else {
            return new Point(x, y);
        }
    }
}
