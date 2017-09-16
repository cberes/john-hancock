package net.seabears.signature.util;

import java.lang.reflect.Method;

/**
 * Utilities for the Point class.
 * Unfortunately, the Point class is package-private is a different package.
 */
public final class PointUtils {
    private static final Class<?> POINT;
    private static final Method VALUE_OF;

    static {
        try {
            POINT = Class.forName("net.seabears.signature.Point");
            VALUE_OF = POINT.getDeclaredMethod("valueOf", int.class, int.class);
            VALUE_OF.setAccessible(true);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    private PointUtils() {
        throw new UnsupportedOperationException("cannot instantiate" + getClass());
    }

    public static Object pt(final int x, final int y) {
        return pt(x, y, POINT);
    }

    private static <T> T pt(final int x, final int y, final Class<T> type) {
        try {
            return type.cast(VALUE_OF.invoke(null, x, y));
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }
}
