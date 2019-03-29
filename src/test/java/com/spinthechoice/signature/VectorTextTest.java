package com.spinthechoice.signature;

import com.spinthechoice.signature.util.ImageUtils;
import com.spinthechoice.signature.util.PointUtils;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class VectorTextTest {
    @Test
    public void testPointsLetterX() throws IOException {
        final byte[] data = ImageUtils.readData("vector-0.txt");
        final List<Curve> points = new VectorTextFactory().parse(data);
        assertEquals(2, points.size());
        assertThat(points.get(0).getPoints(), Matchers.contains(PointUtils.pt(10, 10), PointUtils.pt(110, 110)));
        assertThat(points.get(1).getPoints(), Matchers.contains(PointUtils.pt(10, 110), PointUtils.pt(110, 10)));
    }

    @Test
    public void testPointsTrough() throws IOException {
        final byte[] data = ImageUtils.readData("vector-1.txt");
        final List<Curve> points = new VectorTextFactory().parse(data);
        assertEquals(1, points.size());
        assertThat(points.get(0).getPoints(), Matchers.contains(PointUtils.pt(306, 48), PointUtils.pt(306, 49), PointUtils.pt(307, 49),
                PointUtils.pt(308, 50), PointUtils.pt(309, 50), PointUtils.pt(310, 51), PointUtils.pt(311, 52), PointUtils.pt(313, 53),
                PointUtils.pt(315, 55), PointUtils.pt(318, 56), PointUtils.pt(322, 59), PointUtils.pt(327, 61), PointUtils.pt(332, 64),
                PointUtils.pt(338, 67), PointUtils.pt(344, 70), PointUtils.pt(352, 74), PointUtils.pt(360, 77), PointUtils.pt(369, 81),
                PointUtils.pt(377, 84), PointUtils.pt(386, 87)));
    }

    @Test
    public void testPointsXPowNeg1() throws IOException {
        final byte[] data = ImageUtils.readData("vector-2.txt");
        final List<Curve> points = new VectorTextFactory().parse(data);
        assertEquals(1, points.size());
        assertThat(points.get(0).getPoints(), Matchers.contains(PointUtils.pt(189, 45), PointUtils.pt(190, 45), PointUtils.pt(190, 44),
                PointUtils.pt(191, 43), PointUtils.pt(192, 42), PointUtils.pt(192, 41), PointUtils.pt(193, 40), PointUtils.pt(194, 39),
                PointUtils.pt(195, 39), PointUtils.pt(196, 37), PointUtils.pt(197, 36), PointUtils.pt(199, 35), PointUtils.pt(203, 33),
                PointUtils.pt(206, 33), PointUtils.pt(209, 32), PointUtils.pt(213, 32), PointUtils.pt(217, 32), PointUtils.pt(223, 33),
                PointUtils.pt(229, 34), PointUtils.pt(236, 35), PointUtils.pt(243, 36)));
    }
}
