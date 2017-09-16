package net.seabears.signature;

import net.seabears.signature.util.ImageUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static net.seabears.signature.util.PointUtils.pt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;

public class VectorTextTest {
    @Test
    public void testPointsLetterX() throws IOException {
        final byte[] data = ImageUtils.readData("vector-0.txt");
        final List<Curve> points = new VectorTextFactory().parse(data);
        assertEquals(2, points.size());
        assertThat(points.get(0).getPoints(), contains(pt(10, 10), pt(110, 110)));
        assertThat(points.get(1).getPoints(), contains(pt(10, 110), pt(110, 10)));
    }

    @Test
    public void testPointsTrough() throws IOException {
        final byte[] data = ImageUtils.readData("vector-1.txt");
        final List<Curve> points = new VectorTextFactory().parse(data);
        assertEquals(1, points.size());
        assertThat(points.get(0).getPoints(), contains(pt(306, 48), pt(306, 49), pt(307, 49),
                pt(308, 50), pt(309, 50), pt(310, 51), pt(311, 52), pt(313, 53),
                pt(315, 55), pt(318, 56), pt(322, 59), pt(327, 61), pt(332, 64),
                pt(338, 67), pt(344, 70), pt(352, 74), pt(360, 77), pt(369, 81),
                pt(377, 84), pt(386, 87)));
    }

    @Test
    public void testPointsXPowNeg1() throws IOException {
        final byte[] data = ImageUtils.readData("vector-2.txt");
        final List<Curve> points = new VectorTextFactory().parse(data);
        assertEquals(1, points.size());
        assertThat(points.get(0).getPoints(), contains(pt(189, 45), pt(190, 45), pt(190, 44),
                pt(191, 43), pt(192, 42), pt(192, 41), pt(193, 40), pt(194, 39),
                pt(195, 39), pt(196, 37), pt(197, 36), pt(199, 35), pt(203, 33),
                pt(206, 33), pt(209, 32), pt(213, 32), pt(217, 32), pt(223, 33),
                pt(229, 34), pt(236, 35), pt(243, 36)));
    }
}
