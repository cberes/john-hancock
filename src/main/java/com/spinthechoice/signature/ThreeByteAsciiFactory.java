package com.spinthechoice.signature;

import java.util.List;

class ThreeByteAsciiFactory implements PointFactory {
    private static final byte PEN_UP = 0x70;
    private static final byte SEGMENT_START = 0x60;
    private static final int SEGMENT_START_BYTES = 4;
    private static final byte SEGMENT_START_X_MASK = 0x0C;
    private static final byte SEGMENT_START_Y_MASK = 0x03;
    private static final byte COORDINATE_OFFSET = 0x20;
    private static final int COORDINATE_BYTES = 3;
    private static final byte COORDINATE_X_MASK = 0x38;
    private static final byte COORDINATE_Y_MASK = 0x07;
    private static final int COORDINATE_VALUE_BITS = 9;
    private static final int X_UPPER_BITS_OFFSET = 0;
    private static final int X_LOWER_BITS_OFFSET = 2;
    private static final int Y_UPPER_BITS_OFFSET = 1;
    private static final int Y_LOWER_BITS_OFFSET = 2;

    @Override
    public List<Curve> parse(final byte[] data) {
        final CurvesBuilder builder = new CurvesBuilder();
        int offset = 0;
        while (offset < data.length) {
            offset = processBytes(builder, data, offset);
        }
        return builder.build();
    }

    private static int processBytes(final CurvesBuilder builder, final byte[] data, final int offset) {
        if (isPenUp(data[offset])) {
            builder.newCurve();
            return offset + 1;
        } else if (isSegmentStart(data[offset])) {
            builder.add(createSegmentStart(data, offset));
            return offset + SEGMENT_START_BYTES;
        } else {
            final Point delta = createCoordinate(data, offset);
            builder.add(builder.getLast().add(delta));
            return offset + COORDINATE_BYTES;
        }
    }

    private static boolean isPenUp(final byte datum) {
        return datum == PEN_UP;
    }

    private static boolean isSegmentStart(final byte datum) {
        return datum >= SEGMENT_START && datum < PEN_UP;
    }

    private static Point createSegmentStart(final byte[] data, final int offset) {
        final int x1 = ((data[offset] - SEGMENT_START) & SEGMENT_START_X_MASK) << 7;
        final int x0 = getXLowerBytes(data, offset + 1);
        final int y1 = ((data[offset] - SEGMENT_START) & SEGMENT_START_Y_MASK) << 9;
        final int y0 = getYLowerBytes(data, offset + 1);
        return Point.valueOf(x1 | x0, y1 | y0);
    }


    private static int getXLowerBytes(final byte[] data, final int offset) {
        final int x1 =  (data[offset + X_UPPER_BITS_OFFSET] - COORDINATE_OFFSET) << 3;
        final int x0 = ((data[offset + X_LOWER_BITS_OFFSET] - COORDINATE_OFFSET) & COORDINATE_X_MASK) >> 3;
        return x1 | x0;
    }

    private static int getYLowerBytes(final byte[] data, final int offset) {
        final int y1 = (data[offset + Y_UPPER_BITS_OFFSET] - COORDINATE_OFFSET) << 3;
        final int y0 = (data[offset + Y_LOWER_BITS_OFFSET] - COORDINATE_OFFSET) & COORDINATE_Y_MASK;
        return y1 | y0;
    }

    private static Point createCoordinate(final byte[] data, final int offset) {
        final int x = signExtend(getXLowerBytes(data, offset), COORDINATE_VALUE_BITS);
        final int y = signExtend(getYLowerBytes(data, offset), COORDINATE_VALUE_BITS);
        return Point.valueOf(x, y);
    }

    private static int signExtend(final int value, final int initialBits) {
        final int shiftPlaces = Integer.SIZE - initialBits;
        return (value << shiftPlaces) >> shiftPlaces;
    }
}
