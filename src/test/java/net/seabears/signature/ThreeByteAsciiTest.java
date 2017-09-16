package net.seabears.signature;

import net.seabears.signature.util.ImageUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static net.seabears.signature.util.PointUtils.pt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;

public class ThreeByteAsciiTest {
    @Test
    public void testPointsFromImage() throws IOException {
        final byte[] data = ImageUtils.readData("3-byte-ascii.txt");
        final List<Curve> points = new ThreeByteAsciiFactory().parse(data);
        assertEquals(5, points.size());
        assertThat(points.get(0).getPoints(), contains(pt(0, 36), pt(0, 37), pt(2, 40),
                pt(4, 43), pt(8, 48), pt(14, 56), pt(20, 64), pt(28, 74),
                pt(37, 87), pt(46, 98), pt(51, 108), pt(56, 116)));
        assertEquals(79, points.get(1).getPoints().size());
        assertThat(points.get(2).getPoints(), contains(pt(191, 82), pt(186, 82), pt(181, 82),
                pt(174, 80), pt(165, 78), pt(155, 75), pt(141, 70), pt(124, 64),
                pt(109, 57), pt(98, 52)));
        assertEquals(39, points.get(3).getPoints().size());
        assertThat(points.get(4).getPoints(), contains(pt(345, 77), pt(343, 77), pt(337, 75),
                pt(328, 74), pt(317, 73), pt(305, 70), pt(288, 67), pt(265, 63),
                pt(241, 59), pt(219, 55), pt(199, 52), pt(183, 49), pt(171, 48)));
    }
}
