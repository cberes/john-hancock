package net.seabears.signature;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class CurvesBuilder {
    private final List<Curve> curves = new LinkedList<>();
    private Curve current = new Curve();

    void add(final Point p) {
        if (current.isEmpty()) {
            curves.add(current);
        }
        current.add(p);
    }

    void newCurve() {
        if (!current.isEmpty()) {
            current = new Curve();
        }
    }

    List<Curve> build() {
        return new ArrayList<>(curves);
    }
}
