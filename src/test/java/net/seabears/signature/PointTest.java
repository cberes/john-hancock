package net.seabears.signature;

import org.junit.Test;

import static org.junit.Assert.*;

public class PointTest {
    @Test
    public void testValueOf() {
        assertSame(Point.ORIGIN, Point.valueOf(0, 0));
        assertNotSame(Point.ORIGIN, Point.valueOf(-1, -1));
    }

    @Test
    public void testGetters() {
        final Point p = Point.valueOf(4, 10);
        assertEquals(4, p.getX());
        assertEquals(10, p.getY());
    }

    @Test
    public void testToString() {
        assertEquals("(4,10)", Point.valueOf(4, 10).toString());
    }

    @Test
    public void testEquals() {
        assertTrue(Point.valueOf(1, 2).equals(Point.valueOf(1, 2)));
        assertFalse(Point.valueOf(1, 2).equals(Point.valueOf(1, 3)));
        assertFalse(Point.valueOf(1, 2).equals(Point.valueOf(2, 2)));

        assertTrue(Point.ORIGIN.equals(Point.valueOf(0, 0)));
        assertFalse(Point.ORIGIN.equals(null));
        assertFalse(Point.ORIGIN.equals("not a point"));
    }

    @Test
    public void testHashCode() {
        final Point a = Point.valueOf(4, 10);
        final Point b = Point.valueOf(4, 10);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a.hashCode(), Point.ORIGIN);
    }
}
