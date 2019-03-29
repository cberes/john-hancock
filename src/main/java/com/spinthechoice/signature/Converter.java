package com.spinthechoice.signature;

import java.awt.image.RenderedImage;
import java.util.List;

/**
 * Converts serialized signature data to {@link RenderedImage} instances.
 *
 * This implementation is thread-safe.
 */
public class Converter {
    private final Config config;

    /**
     * Creates a new instance with the default configuration.
     */
    public Converter() {
        this(Config.DEFAULT);
    }

    /**
     * Creates a new instance with the specified configuration.
     * @param config configuration
     */
    public Converter(final Config config) {
        this.config = config;
    }

    /**
     * Builds an image from the specified data using the specified deserialization format.
     * @param data serialized signature data
     * @param format deserialization format
     * @return rendered image
     */
    public RenderedImage convert(final byte[] data, final Format format) {
        final List<Curve> points = format.getFactory().parse(data);
        final Conversion conversion = new Conversion(config, points);
        return conversion.convert();
    }
}
