package com.javarush.task.task16.task1631;

import com.javarush.task.task16.task1631.common.*;

public class ImageReaderFactory {
    public static ImageReader getImageReader(ImageTypes imageTypes) {
        try {
            switch (imageTypes) {
                case BMP:
                    return new BmpReader();
                case JPG:
                    return new JpgReader();
                case PNG:
                    return new PngReader();
                default:
                    throw new IllegalArgumentException("Неизвестный тип картинки");
            }
        }catch (NullPointerException ex) {
            throw new IllegalArgumentException("Неизвестный тип картинки");
        }

        /*
        if (imageTypes == ImageTypes.BMP) return new BmpReader();
        if (imageTypes == ImageTypes.JPG) return new JpgReader();
        if (imageTypes == ImageTypes.PNG) return new PngReader();
        throw new IllegalArgumentException("Неизвестный тип картинки");
 */
    }
}
