package net.seabears.signature;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class CurvesBuilderTest {
    @Test
    public void testExtraNewCurves() {
        final Point point = Point.valueOf(100, 200);
        final CurvesBuilder builder = new CurvesBuilder();
        builder.newCurve();
        builder.newCurve();
        builder.add(point);
        builder.newCurve();
        builder.newCurve();
        final List<Curve> points = builder.build();
        assertEquals(1, points.size());
        assertEquals(1, points.get(0).getPoints().size());
        assertSame(point, points.get(0).getPoints().get(0));
    }

    @Test
    public void testNoNewCurves() {
        final Point point = Point.valueOf(100, 200);
        final CurvesBuilder builder = new CurvesBuilder();
        builder.add(point);
        final List<Curve> points = builder.build();
        assertEquals(1, points.size());
        assertEquals(1, points.get(0).getPoints().size());
        assertSame(point, points.get(0).getPoints().get(0));
    }

    @Test
    public void testMultiplePoints() {
        final CurvesBuilder builder = new CurvesBuilder();
        builder.add(Point.valueOf(1, 1));
        builder.add(Point.valueOf(1, 2));
        builder.newCurve();
        builder.add(Point.valueOf(2, 1));
        builder.add(Point.valueOf(2, 2));
        final List<Curve> points = builder.build();
        assertEquals(2, points.size());
        for (int i = 0; i < points.size(); ++i) {
            assertEquals(2, points.get(i).getPoints().size());
            assertEquals(Point.valueOf(i + 1, 1), points.get(i).getPoints().get(0));
            assertEquals(Point.valueOf(i + 1, 2), points.get(i).getPoints().get(1));
        }
    }
}
