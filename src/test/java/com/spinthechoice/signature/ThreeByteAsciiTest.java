package com.spinthechoice.signature;

import com.spinthechoice.signature.util.ImageUtils;
import com.spinthechoice.signature.util.PointUtils;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class ThreeByteAsciiTest {
    @Test
    public void testPointsFromImage() throws IOException {
        final byte[] data = ImageUtils.readData("3-byte-ascii.txt");
        final List<Curve> points = new ThreeByteAsciiFactory().parse(data);
        assertEquals(5, points.size());
        assertThat(points.get(0).getPoints(), Matchers.contains(PointUtils.pt(0, 36), PointUtils.pt(0, 37), PointUtils.pt(2, 40),
                PointUtils.pt(4, 43), PointUtils.pt(8, 48), PointUtils.pt(14, 56), PointUtils.pt(20, 64), PointUtils.pt(28, 74),
                PointUtils.pt(37, 87), PointUtils.pt(46, 98), PointUtils.pt(51, 108), PointUtils.pt(56, 116)));
        assertEquals(79, points.get(1).getPoints().size());
        assertThat(points.get(2).getPoints(), Matchers.contains(PointUtils.pt(191, 82), PointUtils.pt(186, 82), PointUtils.pt(181, 82),
                PointUtils.pt(174, 80), PointUtils.pt(165, 78), PointUtils.pt(155, 75), PointUtils.pt(141, 70), PointUtils.pt(124, 64),
                PointUtils.pt(109, 57), PointUtils.pt(98, 52)));
        assertEquals(39, points.get(3).getPoints().size());
        assertThat(points.get(4).getPoints(), Matchers.contains(PointUtils.pt(345, 77), PointUtils.pt(343, 77), PointUtils.pt(337, 75),
                PointUtils.pt(328, 74), PointUtils.pt(317, 73), PointUtils.pt(305, 70), PointUtils.pt(288, 67), PointUtils.pt(265, 63),
                PointUtils.pt(241, 59), PointUtils.pt(219, 55), PointUtils.pt(199, 52), PointUtils.pt(183, 49), PointUtils.pt(171, 48)));
    }
}
