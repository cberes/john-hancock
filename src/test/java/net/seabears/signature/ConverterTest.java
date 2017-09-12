package net.seabears.signature;

import org.junit.jupiter.api.Test;

import java.awt.image.RenderedImage;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTest {
    @Test
    public void testConvertSquare() {
        final List<Curve> points = new LinkedList<>();
        Curve curve = new Curve();
        points.add(curve);
        curve.add(Point.valueOf(-10, -5));
        curve.add(Point.valueOf(100, -5));
        curve = new Curve();
        points.add(curve);
        curve.add(Point.valueOf(100, -5));
        curve.add(Point.valueOf(100, 50));
        curve = new Curve();
        points.add(curve);
        curve.add(Point.valueOf(100, 50));
        curve.add(Point.valueOf(-10, 50));
        curve = new Curve();
        points.add(curve);
        curve.add(Point.valueOf(-10, 50));
        curve.add(Point.valueOf(-10, -5));

        final RenderedImage image = new Converter().convert(points);
        assertEquals(111, image.getWidth());
        assertEquals(56, image.getHeight());
    }

    @Test
    public void testConvertLightningBolt() {
        final List<Curve> points = new LinkedList<>();
        Curve curve = new Curve();
        points.add(curve);
        curve.add(Point.valueOf(-10, -5));
        curve.add(Point.valueOf(45, 30));
        curve = new Curve();
        points.add(curve);
        curve.add(Point.valueOf(45, 30));
        curve.add(Point.valueOf(45, 15));
        curve = new Curve();
        points.add(curve);
        curve.add(Point.valueOf(45, 15));
        curve.add(Point.valueOf(100, 50));

        final RenderedImage image = new Converter().convert(points);
        assertEquals(111, image.getWidth());
        assertEquals(56, image.getHeight());
    }
}
