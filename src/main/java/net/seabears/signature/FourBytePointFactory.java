package net.seabears.signature;

import java.util.List;

class FourBytePointFactory implements PointFactory {
    private static final int BYTES_PER_COORD = 2;
    private static final int BYTES_PER_POINT = BYTES_PER_COORD * 2;
    private static final Point PEN_UP = Point.valueOf(-1, -1);

    private final Endianness endianness;

    FourBytePointFactory(final Endianness endianness) {
        this.endianness = endianness;
    }

    @Override
    public List<Curve> parse(final byte[] data) {
        final CurvesBuilder builder = new CurvesBuilder();
        for (int i = 0; i < data.length; i += BYTES_PER_POINT) {
            if (isPossiblePoint(data, i)) {
                final Point point = createPoint(data, i);
                process(point, builder);
            }
        }
        return builder.build();
    }

    private static boolean isPossiblePoint(final byte[] data, final int offset) {
        return data.length - offset >= BYTES_PER_POINT;
    }

    private Point createPoint(final byte[] data, final int offset) {
        final int x = endianness.fromBytes(data, offset);
        final int y = endianness.fromBytes(data, offset + BYTES_PER_COORD);
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
