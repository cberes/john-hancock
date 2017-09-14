package net.seabears.signature.util;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public final class TestUtils {
    private TestUtils() {
        throw new UnsupportedOperationException("cannot instantiate" + getClass());
    }

    public static void saveIfEnabled(final RenderedImage image, final String name) {
        final String path = System.getProperty("test.image.path", "");
        if (!path.isEmpty()) {
            new File(path).mkdirs();
            save(image, path + '/' + name);
        }
    }

    public static void save(final RenderedImage image, final String path) {
        try {
            ImageIO.write(image, getFileExtension(path), new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFileExtension(final String s) {
        final String[] parts = s.split("\\.");
        return parts[parts.length - 1];
    }

    public static String encodeBase64(final RenderedImage image, final String format) {
        try {
            final ByteArrayOutputStream output = new ByteArrayOutputStream();
            ImageIO.write(image, format, output);
            return Base64.encodeBase64String(output.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
