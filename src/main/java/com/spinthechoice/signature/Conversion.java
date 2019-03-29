package com.spinthechoice.signature;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.LinkedList;
import java.util.List;

class Conversion {
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
    private final List<Curve> curves;
    private Point maximum;
    private Point minimum;
    private Point offset;
    private BufferedImage image;
    private Graphics2D g;

    Conversion(final Config config, final List<Curve> curves) {
        this.config = config;
        this.curves = curves;
    }

    RenderedImage convert() {
        computeMaxAndMin();
        computePointOffset();
        initImage();
        initGraphics();
        drawCurves();
        return image;
    }

    private void computeMaxAndMin() {
        final List<Point> allPoints = flatten(curves);
        maximum = getPoint(allPoints, Point.MIN_VALUE, GREATER_THAN);
        minimum = getPoint(allPoints, Point.MAX_VALUE, LESS_THAN);
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

    private void computePointOffset() {
        offset = minimum.negate().add(Point.valueOf(config.getPadding(), config.getPadding()));
    }

    private void initImage() {
        final int totalPadding = config.getPadding() * 2;
        final int width = maximum.getX() - minimum.getX() + 1 + totalPadding;
        final int height = maximum.getY() - minimum.getY() + 1 + totalPadding;
        final int imageType = config.isGreyscale() ? BufferedImage.TYPE_BYTE_GRAY : BufferedImage.TYPE_INT_ARGB;
        image = new BufferedImage(width, height, imageType);
    }

    private void initGraphics() {
        g = image.createGraphics();
        g.setBackground(config.getBackground());
        g.clearRect(0, 0, image.getWidth(), image.getHeight());
        g.setColor(config.getForeground());
    }

    private void drawCurves() {
        for (Curve curve : curves) {
            drawCurve(curve);
        }
    }

    private void drawCurve(final Curve curve) {
        Point previous = null;
        for (Point point : curve.getPoints()) {
            if (previous != null) {
                drawLine(previous, point);
            }
            previous = point;
        }
    }

    private void drawLine(final Point start, final Point end) {
        final int x1 = start.add(offset).getX();
        final int y1 = start.add(offset).getY();
        final int x2 = end.add(offset).getX();
        final int y2 = end.add(offset).getY();
        g.drawLine(x1, y1, x2, y2);
    }
}
