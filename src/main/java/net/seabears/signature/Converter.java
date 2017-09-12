package net.seabears.signature;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.List;
import java.util.function.BiPredicate;

public class Converter {
    public RenderedImage convert(final List<Point> points) {
        final Point maximum = getPoint(points, Point.MIN_VALUE, (a, b) -> a > b);
        final Point minimum = getPoint(points, Point.MAX_VALUE, (a, b) -> a < b);
        final Point offset = minimum.negate();

        final BufferedImage image = createImage(maximum, minimum);
        final Graphics2D g = initImage(image);
        Point previous = Point.PEN_UP;
        for (Point point : points) {
            if (point != Point.PEN_UP && previous != Point.PEN_UP) {
                drawLine(g, previous, point, offset);
            }
            previous = point;
        }
        return image;
    }

    private static Point getPoint(final List<Point> points, final Point initial, final BiPredicate<Integer, Integer> test) {
        int x = initial.getX();
        int y = initial.getY();
        for (Point point : points) {
            if (point == Point.PEN_UP) {
                continue;
            }
            if (test.test(point.getX(), x)) {
                x = point.getX();
            }
            if (test.test(point.getY(), y)) {
                y = point.getY();
            }
        }
        return Point.valueOf(x, y);
    }

    private static BufferedImage createImage(final Point maximum, final Point minimum) {
        final int width = maximum.getX() - minimum.getX() + 1;
        final int height = maximum.getY() - minimum.getY() + 1;
        return new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
    }

    private static Graphics2D initImage(final BufferedImage image) {
        final Graphics2D g = image.createGraphics();
        g.setBackground(Color.WHITE);
        g.clearRect(0, 0, image.getWidth(), image.getHeight());
        g.setColor(Color.BLACK);
        return g;
    }

    private static void drawLine(final Graphics2D g, final Point start, final Point end, final Point offset) {
        final int x1 = start.add(offset).getX();
        final int y1 = start.add(offset).getY();
        final int x2 = end.add(offset).getX();
        final int y2 = end.add(offset).getY();
        g.drawLine(x1, y1, x2, y2);
    }
}
