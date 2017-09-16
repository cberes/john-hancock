package net.seabears.signature;

import net.seabears.signature.util.ImageUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static net.seabears.signature.util.PointUtils.pt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
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
        assertThat(points.get(0).getPoints(), contains(pt(1, 2), pt(3, 4)));
    }

    @Test
    public void testPointsFromImage() throws IOException {
        final byte[] data = ImageUtils.readData("points-big-endian.bin");
        final List<Curve> points = new FourBytePointFactory(Endianness.BIG).parse(data);
        assertEquals(12, points.size());
        assertThat(points.get(0).getPoints(), contains(pt(0, 0), pt(0, 20), pt(10, 20)));
        assertThat(points.get(1).getPoints(), contains(pt(0, 0), pt(10, 0)));
        assertThat(points.get(2).getPoints(), contains(pt(0, 10), pt(7, 10)));
        assertThat(points.get(3).getPoints(), contains(pt(20, 0), pt(20, 20)));
        assertThat(points.get(4).getPoints(), contains(pt(20, 0), pt(30, 0), pt(30, 10),
                pt(20, 10)));
        assertThat(points.get(5).getPoints(), contains(pt(50, 0), pt(40, 0), pt(40, 10),
                pt(50, 10), pt(50, 20), pt(40, 20)));
        assertThat(points.get(6).getPoints(), contains(pt(80, 0), pt(80, 20)));
        assertThat(points.get(7).getPoints(), contains(pt(90, 0), pt(90, 20)));
        assertThat(points.get(8).getPoints(), contains(pt(80, 2), pt(90, 18)));
        assertThat(points.get(9).getPoints(), contains(pt(100, 0), pt(100, 20), pt(110, 20),
                pt(110, 0)));
        assertThat(points.get(10).getPoints(), contains(pt(120, 0), pt(120, 20), pt(130, 20)));
        assertThat(points.get(11).getPoints(), contains(pt(140, 0), pt(140, 20), pt(150, 20)));
    }
}
