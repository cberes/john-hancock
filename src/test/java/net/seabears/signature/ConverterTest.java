package net.seabears.signature;

import org.junit.jupiter.api.Test;

import java.awt.image.RenderedImage;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTest {
    @Test
    public void testConvertSquare() {
        final List<Point> points = new LinkedList<>();
        points.add(Point.valueOf(-10, -5));
        points.add(Point.valueOf(100, -5));
        points.add(Point.PEN_UP);
        points.add(Point.valueOf(100, -5));
        points.add(Point.valueOf(100, 50));
        points.add(Point.PEN_UP);
        points.add(Point.valueOf(100, 50));
        points.add(Point.valueOf(-10, 50));
        points.add(Point.PEN_UP);
        points.add(Point.valueOf(-10, 50));
        points.add(Point.valueOf(-10, -5));

        final RenderedImage image = new Converter().convert(points);
        assertEquals(111, image.getWidth());
        assertEquals(56, image.getHeight());
    }

    @Test
    public void testConvertLightningBolt() {
        final List<Point> points = new LinkedList<>();
        points.add(Point.valueOf(-10, -5));
        points.add(Point.valueOf(45, 30));
        points.add(Point.PEN_UP);
        points.add(Point.valueOf(45, 30));
        points.add(Point.valueOf(45, 15));
        points.add(Point.PEN_UP);
        points.add(Point.valueOf(45, 15));
        points.add(Point.valueOf(100, 50));

        final RenderedImage image = new Converter().convert(points);
        assertEquals(111, image.getWidth());
        assertEquals(56, image.getHeight());
    }
}