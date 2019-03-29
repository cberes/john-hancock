package com.spinthechoice.signature;

import java.awt.Color;

/**
 * Signature conversion configuration.
 */
public class Config {
    /**
     * Configuration builder class.
     */
    public static class Builder {
        private Color background = Color.WHITE;
        private Color foreground = Color.BLACK;
        private int padding = 0;

        private Builder() {
        }

        /**
         * Sets the background color.
         * @param color background color
         * @return this instance
         * @see Config#getBackground()
         */
        public Builder withBackground(final Color color) {
            this.background = color;
            return this;
        }

        /**
         * Sets the foreground color.
         * @param color foreground color
         * @return this instance
         * @see Config#getForeground()
         */
        public Builder withForeground(final Color color) {
            this.foreground = color;
            return this;
        }

        /**
         * Sets the padding in pixels to be used.
         * @param paddingSize padding in pixels
         * @return this instance
         * @see Config#getPadding()
         */
        public Builder withPadding(final int paddingSize) {
            this.padding = paddingSize;
            return this;
        }

        /**
         * Creates a new configuration from this instance.
         * @return {@link Config} instance
         */
        public Config build() {
            return new Config(this);
        }
    }

    /**
     * The default configuration: black on white with no padding.
     */
    public static final Config DEFAULT = builder().build();

    private final Color background;
    private final Color foreground;
    private final int padding;

    private Config(final Builder builder) {
        this.background = builder.background;
        this.foreground = builder.foreground;
        this.padding = builder.padding;
    }

    /**
     * Returns the configured background color.
     * The default background color is {@link Color#WHITE}.
     * @return background color
     */
    public Color getBackground() {
        return background;
    }

    /**
     * Returns the configured foreground color.
     * The default foreground color is {@link Color#BLACK}.
     * @return foreground color
     */
    public Color getForeground() {
        return foreground;
    }

    /**
     * Returns the configured padding in pixels.
     * By default, there is no padding.
     * Padding is added to each side (top, left, bottom, right).
     * For example, if a padding of {@code 10} is used, the image will be 20 pixels wider and 20 pixels taller.
     * @return padding in pixels
     */
    public int getPadding() {
        return padding;
    }

    /**
     * Returns whether greyscale mode can be used for images created with this configuration.
     * @return whether greyscale can be used
     */
    public boolean isGreyscale() {
        return isGreyscale(background) && isGreyscale(foreground);
    }

    private static boolean isGreyscale(final Color color) {
        return color.getRed() == color.getGreen() && color.getGreen() == color.getBlue();
    }

    /**
     * Creates a new builder instance.
     * @return builder instance
     */
    public static Builder builder() {
        return new Builder();
    }
}
