package net.seabears.signature;

import java.util.LinkedList;
import java.util.List;

class VectorTextFactory {
    private static final int PEN_UP_X = 0;
    private static final int PEN_UP_Y = 0xFFFF;
    private static final String END_TOKEN = "~";

    List<Point> parse(final String data) {
        final List<Point> points = new LinkedList<>();
        final String[] textPoints = data.split("\\^");
        for (String textPoint : textPoints) {
            if (!END_TOKEN.equals(textPoint)) {
                points.add(parsePoint(textPoint));
            }
        }
        return points;
    }

    private static Point parsePoint(final String s) {
        final String[] coords = s.split(",");
        final int x = Integer.parseInt(coords[0]);
        final int y = Integer.parseInt(coords[1]);
        return createPoint(x, y);
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
