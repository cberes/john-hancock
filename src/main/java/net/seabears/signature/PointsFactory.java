package net.seabears.signature;

import java.util.LinkedList;
import java.util.List;

class PointsFactory {
    private static final int BYTES_PER_COORD = 2;
    private static final int BYTES_PER_POINT = BYTES_PER_COORD * 2;
    private static final int PEN_UP_X = -1;
    private static final int PEN_UP_Y = -1;

    private final Endianness endianness;

    PointsFactory(final Endianness endianness) {
        this.endianness = endianness;
    }

    List<Point> parse(final byte[] data) {
        final List<Point> points = new LinkedList<>();
        for (int i = 0; i < data.length; i += BYTES_PER_POINT) {
            if (data.length - i >= BYTES_PER_POINT) {
                final int x = endianness.fromBytes(data, i);
                final int y = endianness.fromBytes(data, i + BYTES_PER_COORD);
                points.add(createPoint(x, y));
            }
        }
        return points;
    }

    private static Point createPoint(final int x, final int y) {
        if (isPenUp(x, y)) {
            return Point.PEN_UP;
        } else {
            return Point.valueOf(x, y);
        }
    }

    private static boolean isPenUp(final int x, final int y) {
        return x == PEN_UP_X && y == PEN_UP_Y;
    }
}
