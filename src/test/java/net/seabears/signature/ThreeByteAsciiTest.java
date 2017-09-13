package net.seabears.signature;

import net.seabears.signature.util.TestUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThreeByteAsciiTest {
    @Test
    public void testImage() throws IOException {
        final InputStream input = getClass().getResourceAsStream("/3-byte-ascii.txt");
        final byte[] data = IOUtils.toByteArray(input);
        final RenderedImage image = new Converter().convert(data, Format.THREE_BYTE_ASCII);
        assertEquals(346, image.getWidth());
        assertEquals(86, image.getHeight());
        assertEquals("iVBORw0KGgoAAAANSUhEUgAAAVoAAABWCAAAAACrjwFlAAAFDUlEQVR4Xu2Z23LjMAxD8/8/nR07vkgkKICyZW8an5fGFEB" +
                "SqDvd6b7eDwWvHXuU5oQWfwSb5+F0j/r/CDhHWJR5vQ/Z/wZxhPEJ54kWv7AbzcMmryPm74fkOkEFEZOx2/zt8FwnNJXnh6PVI5O" +
                "FFbOrz/rd6MG+k+KVj6fH+dWks0obtlDzxm+mI6cO0w9Gm85oJelb5UnbF3Pgpjnrps7ZbuGUv5sctCfYJ1038yBLwn379rm6KEZ" +
                "dOJUhrdIRcFJ+jC+O9p1+gVXdOZTTBk2Wb14gWIpIxYCp4FyqcVXO4r4K+S7MgBZjG4cHg6jnfZ6qBeNVM6S7tPXR6bx3ELCvDMZ" +
                "H67ZyhS6SXZrq1uGaqgu4ZRqCGYi+3aUIvg8iOWcspn2qn7l1Y2Y6Hxdt/bwwl7clAxEn4wyV4UFJPci+wNdgVgg3Nz9c3ZvqxkA" +
                "YlB1mxVfxAl9EFZgtbEw7mYPuLVVjIAvKiHLS+vHCgP10P3dZxR50bygaoQoWQ7YUje2SgPf+2ye3xvapLM8FVxGRjFADiy0+o6B" +
                "tcMAg2nqP4gFs0b2ZYkQSVCOQ/IYFvPXEEdYz0QLdW3EjEICSAs9uQL4o2u2zG2afZ5xKhV0GnIKSwkvb8twXeEsRVP0QX5kJygL" +
                "tm/gzX1FYrtOctXNWwDja8NuMau+wLNG4BjgAJc5masyyHA94MdseUVdcjcoqwTDUFpQolSeahTmSL4427ofrh7/FgdsXfYUjXy6" +
                "g83YfR+2b2wStgvJM3wYryKtUON4DRlHSAftoVzvugqsF2QVKvNM+owoFWdwkkcT1bLSFD3aARYc+32KMvouvMAJH74Zv9XqLZP1" +
                "SOqAbFiHSeERp9C18hRE6ehec4fFumYI3HVlRLcR1VNl9voGvEBqG3v0WYGobUX0GHIJSEzt6fY43+rCee5WvEJoGsobEkrC9Uru" +
                "zP/UVzj56i3X6WpYXrA3OA6UWTH5GuCvVLdp9/amvnInN2MU9F22hDZeDGYdYliZd3bErjKGZrn1uo6jdiKOU70eEOWfyE1lXe7l" +
                "4c0toahpEHtbxvmjX664JF/GmtlDF/DVLQtvVAio/l2lcMXKNN7NFSpsRU3izUsHVJ2OynZ6meBN7JKTvc8MVWpWvzf7xImCO8y+" +
                "JBXtmoQID76giNNolgvh8ULaoFKArV4Tvl4TSZdUo2gHAm6IaQtXVwJFZpB6z6JRxPcDXFtYAosxxwm2lDpNIEo4BXxMWHZoKgac" +
                "m0Pz4xbmK4JK4WqNoQoK5KqJblA0iuCOuVgiSFod+o2nWe9/aKFphKa5gRLM5kvEl6oYR3S8o71CBQDScodhmjSIcRni78OBD+1S" +
                "GTAkQTItEUI4jvlx4MNE8zBDPjxEsq0SQDqNxtdaRLRygMSaAG3YF146jNTs4y4fRJtuO6wsFFw+jmRM8RLVjwDExVF0KqHgg7dn" +
                "2H6D2+SRSXam2ElD1OOittj8xKn9q7CbRmwrN21A9XYo0emisSegiRkD1w7hvcid04f8m2jtHd0H3tQL7fB33Te6D7msF9vlCbhz" +
                "dA13XCVzhMu6b3AVd1wlc4TpuHN0B39YpXOEy7pvcA9/WKVzhAcJz8gpfeQAIMTmJKzwglJicxhUeAFJKVmSfHxBaSlZlnx8AYkh" +
                "WZp8fPGpGRqfafhk5I/tn/OrpAZCIqJImfL9KJqIn2xSphFLinyeX1v/zH09fQDarJ1yZfFJ5x4/yD+XyCxwQzGeNAAAAAElFTkS" +
                "uQmCC", TestUtils.encodeBase64(image, "png"));
    }
}
