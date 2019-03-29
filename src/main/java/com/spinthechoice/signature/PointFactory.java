package com.spinthechoice.signature;

import java.util.List;

interface PointFactory {
    List<Curve> parse(byte[] data);
}
