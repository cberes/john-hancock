package net.seabears.signature;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PointTest {
    @Test
    public void testValueOf() {
        assertSame(Point.EMPTY, Point.valueOf(0, 0));
        assertSame(Point.PEN_UP, Point.valueOf(-1, -1));
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
        assertTrue(Point.EMPTY.equals(Point.valueOf(0, 0)));
        assertFalse(Point.EMPTY.equals(Point.PEN_UP));
        assertFalse(Point.EMPTY.equals(null));
        assertFalse(Point.EMPTY.equals("not a point"));
    }

    @Test
    public void testHashCode() {
        final Point a = Point.valueOf(4, 10);
        final Point b = Point.valueOf(4, 10);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a.hashCode(), Point.EMPTY);
    }
}