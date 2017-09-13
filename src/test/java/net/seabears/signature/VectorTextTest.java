package net.seabears.signature;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VectorTextTest {
    @Test
    public void testPoints() throws IOException {
        final byte[] data = readData("vector-0.txt");
        final List<Curve> points = new VectorTextFactory().parse(data);
        assertEquals(2, points.size());
        assertEquals(2, points.get(0).getPoints().size());
        assertEquals(Point.valueOf(10, 10), points.get(0).getPoints().get(0));
        assertEquals(Point.valueOf(110, 110), points.get(0).getPoints().get(1));
        assertEquals(2, points.get(1).getPoints().size());
        assertEquals(Point.valueOf(10, 110), points.get(1).getPoints().get(0));
        assertEquals(Point.valueOf(110, 10), points.get(1).getPoints().get(1));
    }

    private byte[] readData(final String name) throws IOException {
        final URL url = getClass().getResource('/' + name);
        return IOUtils.toByteArray(url);
    }

    @Test
    public void testLetterX() throws IOException {
        final RenderedImage image = createImage("vector-0.txt");
        assertEquals(101, image.getWidth());
        assertEquals(101, image.getHeight());
    }

    private RenderedImage createImage(final String name) throws IOException {
        final byte[] data = readData(name);
        return new Converter().convert(data, Format.VECTOR_TEXT);
    }

    @Test
    public void testTrough() throws IOException {
        final RenderedImage image = createImage("vector-1.txt");
        assertEquals(81, image.getWidth());
        assertEquals(40, image.getHeight());
    }

    @Test
    public void testXPowNeg1() throws IOException {
        final RenderedImage image = createImage("vector-2.txt");
        assertEquals(55, image.getWidth());
        assertEquals(14, image.getHeight());
    }
}
