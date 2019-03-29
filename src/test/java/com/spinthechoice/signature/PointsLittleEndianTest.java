package com.spinthechoice.signature;

import com.spinthechoice.signature.util.ImageUtils;
import com.spinthechoice.signature.util.PointUtils;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;

public class PointsLittleEndianTest {
    @Test
    public void testSinglePoint() throws IOException {
        final byte[] data = new byte[] {2, 1, 4, 3};
        final List<Curve> points = new FourBytePointFactory(Endianness.LITTLE).parse(data);
        assertEquals(1, points.size());
        assertEquals(1, points.get(0).getPoints().size());
        assertEquals(Point.valueOf(258, 772), points.get(0).getPoints().get(0));
    }

    @Test
    public void testSinglePointWithNegativeNumbers() throws IOException {
        final byte[] data = new byte[] {-1, 1, 1, -1};
        final List<Curve> points = new FourBytePointFactory(Endianness.LITTLE).parse(data);
        assertEquals(1, points.size());
        assertEquals(1, points.get(0).getPoints().size());
        assertEquals(Point.valueOf(511, -255), points.get(0).getPoints().get(0));
    }

    @Test
    public void testPoints() throws IOException {
        final byte[] data = new byte[] {1, 0, 2, 0, 3, 0, 4, 0, -1, -1, -1, -1};
        final List<Curve> points = new FourBytePointFactory(Endianness.LITTLE).parse(data);
        assertEquals(1, points.size());
        assertThat(points.get(0).getPoints(), Matchers.contains(PointUtils.pt(1, 2), PointUtils.pt(3, 4)));
    }

    @Test
    public void testPointsFromImage() throws IOException {
        final byte[] data = ImageUtils.readData("points-little-endian.bin");
        final List<Curve> points = new FourBytePointFactory(Endianness.LITTLE).parse(data);
        assertEquals(1, points.size());
        assertThat(points.get(0).getPoints(), Matchers.contains(PointUtils.pt(1315, 577), PointUtils.pt(1314, 577), PointUtils.pt(1312, 578),
                PointUtils.pt(1310, 579), PointUtils.pt(1307, 580), PointUtils.pt(1305, 581), PointUtils.pt(1305, 582), PointUtils.pt(1304, 584),
                PointUtils.pt(1305, 585), PointUtils.pt(1306, 586), PointUtils.pt(1307, 587), PointUtils.pt(1311, 588)));
    }
}
