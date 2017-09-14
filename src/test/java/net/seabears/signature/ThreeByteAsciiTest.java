package net.seabears.signature;

import net.seabears.signature.util.TestUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class ThreeByteAsciiTest {
    @Test
    public void testImage() throws IOException {
        final InputStream input = getClass().getResourceAsStream("/3-byte-ascii.txt");
        final byte[] data = IOUtils.toByteArray(input);
        final RenderedImage image = new Converter().convert(data, Format.THREE_BYTE_ASCII);
        assertEquals(346, image.getWidth());
        assertEquals(86, image.getHeight());
        TestUtils.saveIfEnabled(image, getClass().getSimpleName() + ".testImage.png");
    }
}
