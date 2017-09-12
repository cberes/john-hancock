package net.seabears.signature;

import java.util.List;

class VectorTextFactory implements PointFactory {
    private static final Point PEN_UP = Point.valueOf(0, 0xFFFF);
    private static final String POINT_DELIMITER = ",";

    @Override
    public List<Curve> parse(final byte[] data) {
        final CurvesBuilder builder = new CurvesBuilder();
        for (String textPoint : splitIntoPoints(data)) {
            if (isPossiblePoint(textPoint)) {
                final Point point = createPoint(textPoint);
                process(point, builder);
            }
        }
        return builder.build();
    }

    private static String[] splitIntoPoints(final byte[] data) {
        final String text = new String(data);
        return text.split("\\^");
    }

    private static boolean isPossiblePoint(final String s) {
        return s.contains(POINT_DELIMITER);
    }

    private static Point createPoint(final String point) {
        final String[] coords = point.split(POINT_DELIMITER);
        final int x = Integer.parseInt(coords[0]);
        final int y = Integer.parseInt(coords[1]);
        return Point.valueOf(x, y);
    }

    private static void process(final Point p, final CurvesBuilder builder) {
        if (isPenUp(p)) {
            builder.newCurve();
        } else {
            builder.add(p);
        }
    }

    private static boolean isPenUp(final Point p) {
        return PEN_UP.equals(p);
    }
}
