package com.spinthechoice.signature;

import org.junit.Test;

import java.awt.Color;
import java.awt.image.RenderedImage;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ConversionTest {
    @Test
    public void testConvertSquare() {
        final List<Curve> points = buildPointsForSquare();
        final RenderedImage image = new Conversion(Config.DEFAULT, points).convert();
        assertEquals(111, image.getWidth());
        assertEquals(56, image.getHeight());
        assertArrayEquals(new int[] {0}, image.getData().getPixel(0, 0, new int[1]));
        assertArrayEquals(new int[] {255}, image.getData().getPixel(1, 1, new int[1]));
    }

    private static List<Curve> buildPointsForSquare() {
        final CurvesBuilder builder = new CurvesBuilder();
        builder.add(Point.valueOf(-10, -5));
        builder.add(Point.valueOf(100, -5));
        builder.newCurve();
        builder.add(Point.valueOf(100, -5));
        builder.add(Point.valueOf(100, 50));
        builder.newCurve();
        builder.add(Point.valueOf(100, 50));
        builder.add(Point.valueOf(-10, 50));
        builder.newCurve();
        builder.add(Point.valueOf(-10, 50));
        builder.add(Point.valueOf(-10, -5));
        return builder.build();
    }

    @Test
    public void testConvertSquareWithPadding() {
        final List<Curve> points = buildPointsForSquare();
        final Config config = Config.builder().withPadding(10).build();
        final RenderedImage image = new Conversion(config, points).convert();
        assertEquals(131, image.getWidth());
        assertEquals(76, image.getHeight());
        assertArrayEquals(new int[] {255}, image.getData().getPixel(0, 0, new int[1]));
        assertArrayEquals(new int[] {0}, image.getData().getPixel(10, 10, new int[1]));
    }

    @Test
    public void testConvertSquareWithColors() {
        final List<Curve> points = buildPointsForSquare();
        final Config config = Config.builder().withBackground(Color.BLUE).withForeground(Color.RED).build();
        final RenderedImage image = new Conversion(config, points).convert();
        assertEquals(111, image.getWidth());
        assertEquals(56, image.getHeight());
        assertArrayEquals(new int[] {255, 0, 0, 255}, image.getData().getPixel(0, 0, new int[4]));
        assertArrayEquals(new int[] {0, 0, 255, 255}, image.getData().getPixel(1, 1, new int[4]));
    }

    @Test
    public void testConvertLightningBolt() {
        final List<Curve> points = buildPointsForLightningBolt();
        final RenderedImage image = new Conversion(Config.DEFAULT, points).convert();
        assertEquals(111, image.getWidth());
        assertEquals(56, image.getHeight());
    }

    private static List<Curve> buildPointsForLightningBolt() {
        final CurvesBuilder builder = new CurvesBuilder();
        builder.add(Point.valueOf(-10, -5));
        builder.add(Point.valueOf(45, 30));
        builder.newCurve();
        builder.add(Point.valueOf(45, 30));
        builder.add(Point.valueOf(45, 15));
        builder.newCurve();
        builder.add(Point.valueOf(45, 15));
        builder.add(Point.valueOf(100, 50));
        return builder.build();
    }
}
