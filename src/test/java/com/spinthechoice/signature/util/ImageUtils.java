package com.spinthechoice.signature.util;

import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public final class ImageUtils {
    private ImageUtils() {
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

    public static byte[] readData(final String name) throws IOException {
        final InputStream stream = ImageUtils.class.getResourceAsStream('/' + name);
        return IOUtils.toByteArray(stream);
    }
}
