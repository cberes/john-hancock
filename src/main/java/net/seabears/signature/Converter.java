package net.seabears.signature;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.LinkedList;
import java.util.List;

/**
 * Converts serialized signature data to {@link RenderedImage} instances.
 *
 * This implementation is thread-safe.
 */
public class Converter {
    private interface BiIntPredicate {
        boolean test(int a, int b);
    }

    private static BiIntPredicate GREATER_THAN = new BiIntPredicate() {
        @Override
        public boolean test(final int a, final int b) {
            return a > b;
        }
    };

    private static BiIntPredicate LESS_THAN = new BiIntPredicate() {
        @Override
        public boolean test(final int a, final int b) {
            return a < b;
        }
    };

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
        return convert(points);
    }

    RenderedImage convert(final List<Curve> curves) {
        final List<Point> allPoints = flatten(curves);
        final Point maximum = getPoint(allPoints, Point.MIN_VALUE, GREATER_THAN);
        final Point minimum = getPoint(allPoints, Point.MAX_VALUE, LESS_THAN);
        final Point offset = minimum.negate().add(Point.valueOf(config.getPadding(), config.getPadding()));

        final BufferedImage image = createImage(maximum, minimum);
        final Graphics2D g = initImage(image);
        for (Curve curve : curves) {
            drawCurve(g, curve, offset);
        }
        return image;
    }

    private static List<Point> flatten(final List<Curve> curves) {
        final List<Point> points = new LinkedList<>();
        for (Curve curve : curves) {
            points.addAll(curve.getPoints());
        }
        return points;
    }

    private static Point getPoint(final List<Point> points, final Point initial, final BiIntPredicate test) {
        int x = initial.getX();
        int y = initial.getY();
        for (Point point : points) {
            if (test.test(point.getX(), x)) {
                x = point.getX();
            }
            if (test.test(point.getY(), y)) {
                y = point.getY();
            }
        }
        return Point.valueOf(x, y);
    }

    private BufferedImage createImage(final Point maximum, final Point minimum) {
        final int totalPadding = config.getPadding() * 2;
        final int width = maximum.getX() - minimum.getX() + 1 + totalPadding;
        final int height = maximum.getY() - minimum.getY() + 1 + totalPadding;
        final int imageType = config.isGreyscale() ? BufferedImage.TYPE_BYTE_GRAY : BufferedImage.TYPE_INT_ARGB;
        return new BufferedImage(width, height, imageType);
    }

    private Graphics2D initImage(final BufferedImage image) {
        final Graphics2D g = image.createGraphics();
        g.setBackground(config.getBackground());
        g.clearRect(0, 0, image.getWidth(), image.getHeight());
        g.setColor(config.getForeground());
        return g;
    }

    private static void drawCurve(final Graphics2D g, final Curve curve, final Point offset) {
        Point previous = null;
        for (Point point : curve.getPoints()) {
            if (previous != null) {
                drawLine(g, previous, point, offset);
            }
            previous = point;
        }
    }

    private static void drawLine(final Graphics2D g, final Point start, final Point end, final Point offset) {
        final int x1 = start.add(offset).getX();
        final int y1 = start.add(offset).getY();
        final int x2 = end.add(offset).getX();
        final int y2 = end.add(offset).getY();
        g.drawLine(x1, y1, x2, y2);
    }
}
