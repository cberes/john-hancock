package net.seabears.signature;

import net.seabears.signature.util.TestUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VectorTextTest {
    @Test
    public void testLetterX() throws IOException {
        final RenderedImage image = createImage("vector-0.txt");
        assertEquals(101, image.getWidth());
        assertEquals(101, image.getHeight());
        assertEquals("iVBORw0KGgoAAAANSUhEUgAAAGUAAABlCAAAAABxF3ITAAACAklEQVR4Xr2YwZLCMAxD+/8/3WUphNSWZMcTV7dIT9YwcOI" +
                "4zn4dx9k/897onvlM9M6Mhc6ZaaBv5na/a8ac75lx1ztmwPH9M/D27hlyeu8MvbxzRhzeNyPv7poJztp3TfaKfXujIHfDGcBZlb/" +
                "gHWQtCfSBBb28UBt52EwKdqFJ3IxwE7vMDkV6xKa+FmsxnwdCtEMDkTDxBk9UBCV4EcnMS9Eq06GRZGUYpLM0qdMoHgq4IA7zSxE" +
                "V5TFwJpgQeIgIkSg/U0jA6PRShpGQyoZSkKB4MitHUYz5RkmMcNj1ynIQRB5UGgSkd5jypEPtW2gBNWy9GWiGy8VYP7raS+mLF2t" +
                "ZXXyttaD/Qqm0pso/auuNZyqPfJZHvpdHfmNfvFjL6UdXewnNcLkY6c7Wm1IWtW+hPOpJ7zClSQQiDyoLYg67XkmOYcw3ymGc4sm" +
                "sFKUglQ1lIM3o9FKCiZAoPzPII0QInAkmAqL8UkQFeRAPBZyOdTpLkzKVoZFkVagyL0WLTERQgucRT5h4gyY0EKIdFjBfi7WIT+x" +
                "QpIdt7GaEm9CFZlKwi0zk5YXawAPWkkDfW95Zlb/gHGcU5G5Yw75rslfM28ZVybO7Ruyl22vfiDi8c4Re3jtCTu8egbf3j4DjHS" +
                "Pues+IOd81crvfNzINdI6Mhd6Rz0T3yHujf+Q18wf/mOsrPQSuMwAAAABJRU5ErkJggg==",
                TestUtils.encodeBase64(image, "png"));
    }

    private RenderedImage createImage(final String name) throws IOException {
        final URL url = getClass().getResource('/' + name);
        final String data = IOUtils.toString(url, StandardCharsets.UTF_8);
        final List<Point> points = new VectorTextFactory().parse(data);
        return  new Converter().convert(points);
    }

    @Test
    public void testTrough() throws IOException {
        final RenderedImage image = createImage("vector-1.txt");
        assertEquals(81, image.getWidth());
        assertEquals(40, image.getHeight());
        assertEquals("iVBORw0KGgoAAAANSUhEUgAAAFEAAAAoCAAAAACjyUnjAAAAtUlEQVR4Xq3WRxLEQAgDQP7/aVwO5YAJYpCODOrCe1oRJUe" +
                "ETcqO2uEoh0YlT4xJXhbxy2+JRj4Q68w3wzG/CIM0BuHMnzA2nf7QdNsjM+gOzLC5bCa95ClLVls7My+tmFWlb9aFroms90xsuWO" +
                "iq7gJL8ImuHYEM6GlO8j/hXrDpDSrdyfFoeljmAyNX4qEZjQHEpj+FIxrerNGnB/0N2jHmnPRHsoQ9YOSRH1QnqgXShV1Rzd1P0p" +
                "fRUNknwAAAABJRU5ErkJggg==", TestUtils.encodeBase64(image, "png"));
    }

    @Test
    public void testXPowNeg1() throws IOException {
        final RenderedImage image = createImage("vector-2.txt");
        assertEquals(55, image.getWidth());
        assertEquals(14, image.getHeight());
        assertEquals("iVBORw0KGgoAAAANSUhEUgAAADcAAAAOCAAAAACuxdv/AAAAUUlEQVR4XqWRWQoAIAgFvf+lK1vMpch0vsTnICIUCzB0tpC" +
                "BnbWd2ef1IR/Y5bu+WwTTafhtEXz3h9bJeY7bFPJIP6gEtIwX0ZoU0vCHuuWiApusxVf+faT+AAAAAElFTkSuQmCC",
                TestUtils.encodeBase64(image, "png"));
    }
}
