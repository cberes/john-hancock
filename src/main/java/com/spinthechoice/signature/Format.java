package com.spinthechoice.signature;

/**
 * Supported signature serialization formats.
 */
public enum Format {
    /** Points, big-endian. */
    POINTS_BIG_ENDIAN(new FourBytePointFactory(Endianness.BIG)),
    /** Points, litte-endian. */
    POINTS_LITTLE_ENDIAN(new FourBytePointFactory(Endianness.LITTLE)),
    /** 3-byte ASCII. */
    THREE_BYTE_ASCII(new ThreeByteAsciiFactory()),
    /** Vector text. */
    VECTOR_TEXT(new VectorTextFactory());

    private final PointFactory factory;

    Format(final PointFactory factory) {
        this.factory = factory;
    }

    PointFactory getFactory() {
        return factory;
    }
}
