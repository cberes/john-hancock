package net.seabears.signature;

import net.seabears.signature.util.TestUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PointsBigEndianTest {
    @Test
    public void testSinglePoint() throws IOException {
        final byte[] data = new byte[] {1, 2, 3, 4};
        final List<Curve> points = new FourBytePointFactory(Endianness.BIG).parse(data);
        assertEquals(1, points.size());
        assertEquals(1, points.get(0).getPoints().size());
        assertEquals(Point.valueOf(258, 772), points.get(0).getPoints().get(0));
    }

    @Test
    public void testSinglePointWithNegativeNumbers() throws IOException {
        final byte[] data = new byte[] {1, -1, -1, 1};
        final List<Curve> points = new FourBytePointFactory(Endianness.BIG).parse(data);
        assertEquals(1, points.size());
        assertEquals(1, points.get(0).getPoints().size());
        assertEquals(Point.valueOf(511, -255), points.get(0).getPoints().get(0));
    }

    @Test
    public void testPoints() throws IOException {
        final byte[] data = new byte[] {0, 1, 0, 2, 0, 3, 0, 4, -1, -1, -1, -1};
        final List<Curve> points = new FourBytePointFactory(Endianness.BIG).parse(data);
        assertEquals(1, points.size());
        assertEquals(2, points.get(0).getPoints().size());
        assertEquals(Point.valueOf(1, 2), points.get(0).getPoints().get(0));
        assertEquals(Point.valueOf(3, 4), points.get(0).getPoints().get(1));
    }

    @Test
    public void testImage() throws IOException {
        final InputStream input = getClass().getResourceAsStream("/points-big-endian.bin");
        final byte[] data = IOUtils.toByteArray(input);
        final RenderedImage image = new Converter().convert(data, Format.POINTS_BIG_ENDIAN);
        assertEquals(151, image.getWidth());
        assertEquals(21, image.getHeight());
        TestUtils.saveIfEnabled(image, getClass().getSimpleName() + ".testImage.png");
    }
}
