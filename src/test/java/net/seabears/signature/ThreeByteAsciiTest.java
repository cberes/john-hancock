package net.seabears.signature;

import net.seabears.signature.util.ImageUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ThreeByteAsciiTest {
    @Test
    public void testPointsFromImage() throws IOException {
        final byte[] data = ImageUtils.readData("3-byte-ascii.txt");
        final List<Curve> points = new ThreeByteAsciiFactory().parse(data);
        assertEquals(5, points.size());
        // TODO verify points
    }
}
