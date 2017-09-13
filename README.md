# john-hancock

Builds images from common customer signature serialization formats

[![Build Status](https://travis-ci.org/cberes/john-hancock.svg?branch=master)](https://travis-ci.org/cberes/john-hancock)

## Supported formats

### Vantiv

1. Points, big-endian
2. Points, little-endian
3. 3-byte ASCII

### Cayan

1. Vector text

## Installation

### Gradle

```groovy
compile 'net.seabears:john-hancock:1.0.0'
```

### Maven

```xml
<dependency>
  <groupId>net.seabears</groupId>
  <artifactId>john-hancock</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Example usage

```java
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.seabears.signature.Converter;
import net.seabears.signature.Format;

public class Main {
    static class Signature {
        public byte[] data;
    }

    public static void main(final String[] args) throws IOException {
        final String json = "{\"data\":\"/////wAAAAAAAAAUAAoAFP////8AAAAAAAoAAP////8AAAAKAAcACv//" +
                "//8AFAAAABQAFP////8AFAAAAB4AAAAeAAoAFAAK/////wAyAAAAKAAAACgACgAyAAoAMgAUACgAFP//" +
                "//8AUAAAAFAAFP////8AWgAAAFoAFP////8AUAACAFoAEv////8AZAAAAGQAFABuABQAbgAA/////wB4" +
                "AAAAeAAUAIIAFP////8AjAAAAIwAFACWABT/////\"}";
        final Signature signature = new ObjectMapper().readValue(json, Signature.class);
        final Converter converter = new Converter();
        final RenderedImage image = converter.convert(signature.data, Format.POINTS_BIG_ENDIAN);
        ImageIO.write(image, "png", new File("example.png"));
    }
}

```

## Configuration

[Converter](src/main/java/net/seabears/signature/Converter.java) accepts a [Config](src/main/java/net/seabears/signature/Config.java) instance. This allows you to configure

1. background color
2. foreground color
3. padding

# More information

- [Vantiv points format](https://developer.vantiv.com/thread/1830)
- [3-byte ASCII](https://social.msdn.microsoft.com/Forums/vstudio/en-US/1dc7421a-56dc-4698-ac33-9c79e25fde36/saving-series-of-point-data-in-c)

## License

Copyright © 2017 Corey Beres

Distributed under the MIT license

