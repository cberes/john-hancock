package com.spinthechoice.signature;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ConfigTest {
    @Test
    public void testIsGreyscale() {
        assertTrue(Config.DEFAULT.isGreyscale());
        assertFalse(Config.builder().withBackground(Color.BLUE).build().isGreyscale());
        assertFalse(Config.builder().withForeground(Color.RED).build().isGreyscale());
    }
}
