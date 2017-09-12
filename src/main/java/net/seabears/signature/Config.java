package net.seabears.signature;

import java.awt.Color;

public class Config {
    public static class Builder {
        private Color background = Color.WHITE;
        private Color foreground = Color.BLACK;
        private int padding = 0;

        private Builder() {
        }

        public Builder withBackground(final Color color) {
            this.background = color;
            return this;
        }

        public Builder withForeground(final Color color) {
            this.foreground = color;
            return this;
        }

        public Builder withPadding(final int paddingSize) {
            this.padding = paddingSize;
            return this;
        }

        public Config build() {
            return new Config(this);
        }
    }

    public static final Config DEFAULT = builder().build();

    private final Color background;
    private final Color foreground;
    private final int padding;

    private Config(final Builder builder) {
        this.background = builder.background;
        this.foreground = builder.foreground;
        this.padding = builder.padding;
    }

    public Color getBackground() {
        return background;
    }

    public Color getForeground() {
        return foreground;
    }

    public int getPadding() {
        return padding;
    }

    public boolean isGrayscale() {
        return isGrayscale(background) && isGrayscale(foreground);
    }

    private static boolean isGrayscale(final Color color) {
        return color.getRed() == color.getGreen() && color.getGreen() == color.getBlue();
    }

    public static Builder builder() {
        return new Builder();
    }
}
