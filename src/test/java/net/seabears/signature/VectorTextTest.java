package net.seabears.signature;

import net.seabears.signature.util.ImageUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VectorTextTest {
    @Test
    public void testPointsLetterX() throws IOException {
        final byte[] data = ImageUtils.readData("vector-0.txt");
        final List<Curve> points = new VectorTextFactory().parse(data);
        assertEquals(2, points.size());
        assertEquals(2, points.get(0).getPoints().size());
        assertEquals(Point.valueOf(10, 10), points.get(0).getPoints().get(0));
        assertEquals(Point.valueOf(110, 110), points.get(0).getPoints().get(1));
        assertEquals(2, points.get(1).getPoints().size());
        assertEquals(Point.valueOf(10, 110), points.get(1).getPoints().get(0));
        assertEquals(Point.valueOf(110, 10), points.get(1).getPoints().get(1));
    }

    @Test
    public void testPointsTrough() throws IOException {
        final byte[] data = ImageUtils.readData("vector-1.txt");
        final List<Curve> points = new VectorTextFactory().parse(data);
        assertEquals(1, points.size());
        assertEquals(20, points.get(0).getPoints().size());
        // TODO verify points
    }

    @Test
    public void testPointsXPowNeg1() throws IOException {
        final byte[] data = ImageUtils.readData("vector-2.txt");
        final List<Curve> points = new VectorTextFactory().parse(data);
        assertEquals(1, points.size());
        assertEquals(21, points.get(0).getPoints().size());
        // TODO verify points
    }
}
