package net.seabears.signature;

enum Endianness {
    BIG {
        @Override
        int fromBytes(final byte[] bytes, final int offset) {
            return (bytes[offset] << Byte.SIZE) | Byte.toUnsignedInt(bytes[offset + 1]);
        }
    },
    LITTLE {
        @Override
        int fromBytes(final byte[] bytes, final int offset) {
            return (bytes[offset + 1] << Byte.SIZE) | Byte.toUnsignedInt(bytes[offset]);
        }
    };

    abstract int fromBytes(byte[] bytes, int offset);
}
