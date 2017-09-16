package net.seabears.signature;

import net.seabears.signature.util.ImageUtils;
import org.junit.Test;

import java.awt.image.RenderedImage;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ConverterTest {
    @Test
    public void testPointsBigEndian() throws IOException {
        final byte[] data = ImageUtils.readData("points-big-endian.bin");
        final RenderedImage image = new Converter().convert(data, Format.POINTS_BIG_ENDIAN);
        assertEquals(151, image.getWidth());
        assertEquals(21, image.getHeight());
        ImageUtils.saveIfEnabled(image, getClass() + ".testPointsBigEndian.png");
    }

    @Test
    public void testPointsLittleEndian() throws IOException {
        final byte[] data = ImageUtils.readData("points-little-endian.bin");
        final RenderedImage image = new Converter().convert(data, Format.POINTS_LITTLE_ENDIAN);
        assertEquals(12, image.getWidth());
        assertEquals(12, image.getHeight());
        ImageUtils.saveIfEnabled(image, getClass() + ".testPointsLittleEndian.png");
    }

    @Test
    public void testThreeByteAscii() throws IOException {
        final byte[] data = ImageUtils.readData("3-byte-ascii.txt");
        final RenderedImage image = new Converter().convert(data, Format.THREE_BYTE_ASCII);
        assertEquals(346, image.getWidth());
        assertEquals(86, image.getHeight());
        ImageUtils.saveIfEnabled(image, getClass() + ".testThreeByteAscii.png");
    }

    @Test
    public void testLetterX() throws IOException {
        final byte[] data = ImageUtils.readData("vector-0.txt");
        final RenderedImage image = new Converter().convert(data, Format.VECTOR_TEXT);
        assertEquals(101, image.getWidth());
        assertEquals(101, image.getHeight());
        ImageUtils.saveIfEnabled(image, getClass() + ".testLetterX.png");
    }

    @Test
    public void testTrough() throws IOException {
        final byte[] data = ImageUtils.readData("vector-1.txt");
        final RenderedImage image = new Converter().convert(data, Format.VECTOR_TEXT);
        assertEquals(81, image.getWidth());
        assertEquals(40, image.getHeight());
        ImageUtils.saveIfEnabled(image, getClass() + ".testTrough.png");
    }

    @Test
    public void testXPowNeg1() throws IOException {
        final byte[] data = ImageUtils.readData("vector-2.txt");
        final RenderedImage image = new Converter().convert(data, Format.VECTOR_TEXT);
        assertEquals(55, image.getWidth());
        assertEquals(14, image.getHeight());
        ImageUtils.saveIfEnabled(image, getClass() + ".testXPowNeg1.png");
    }
}
