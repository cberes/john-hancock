package net.seabears.signature;

public enum Format {
    POINTS_BIG_ENDIAN(new FourBytePointFactory(Endianness.BIG)),
    POINTS_LITTLE_ENDIAN(new FourBytePointFactory(Endianness.LITTLE)),
    THREE_BYTE_ASCII(new ThreeByteAsciiFactory()),
    VECTOR_TEXT(new VectorTextFactory());

    private final PointFactory factory;

    Format(final PointFactory factory) {
        this.factory = factory;
    }

    PointFactory getFactory() {
        return factory;
    }
}
