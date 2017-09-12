package net.seabears.signature;

import net.seabears.signature.util.TestUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class PointsLittleEndianTest {
    @Test
    public void testSinglePoint() throws IOException {
        final byte[] data = new byte[] {2, 1, 4, 3};
        final List<Point> points = new PointsFactory(Endianness.LITTLE).parse(data);
        assertEquals(1, points.size());
        assertEquals(Point.valueOf(258, 772), points.get(0));
    }

    @Test
    public void testSinglePointWithNegativeNumbers() throws IOException {
        final byte[] data = new byte[] {-1, 1, 1, -1};
        final List<Point> points = new PointsFactory(Endianness.LITTLE).parse(data);
        assertEquals(1, points.size());
        assertEquals(Point.valueOf(511, -255), points.get(0));
    }

    @Test
    public void testPoints() throws IOException {
        final byte[] data = new byte[] {1, 0, 2, 0, 3, 0, 4, 0, -1, -1, -1, -1};
        final List<Point> points = new PointsFactory(Endianness.LITTLE).parse(data);
        assertEquals(3, points.size());
        assertEquals(Point.valueOf(1, 2), points.get(0));
        assertEquals(Point.valueOf(3, 4), points.get(1));
        assertSame(Point.PEN_UP, points.get(2));
    }

    @Test
    public void testImage() throws IOException {
        final InputStream input = getClass().getResourceAsStream("/points-little-endian.bin");
        final byte[] data = IOUtils.toByteArray(input);
        final List<Point> points = new PointsFactory(Endianness.LITTLE).parse(data);
        final RenderedImage image = new Converter().convert(points);
        assertEquals(12, image.getWidth());
        assertEquals(12, image.getHeight());
        assertEquals("iVBORw0KGgoAAAANSUhEUgAAAAwAAAAMCAAAAABzHgM7AAAALUlEQVR4XmP4DwcMDAwIJhAjC4IIuDQDggnmwNnI4ugcFGU" +
                "w06EcZKPBJIQPAPuaeobjxzn7AAAAAElFTkSuQmCC", TestUtils.encodeBase64(image, "png"));
    }
}
