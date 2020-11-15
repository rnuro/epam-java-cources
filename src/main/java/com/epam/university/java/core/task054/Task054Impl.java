package com.epam.university.java.core.task054;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;

public class Task054Impl implements Task054 {
    /**
     * Convert an image to black-and-white.
     * Tip: red, green and blue values of pixel have to have the same value.
     *
     * @param inputFilePath  - image to apply filter.
     * @param outputFilePath - image after filter applying.
     */
    @Override
    public BufferedImage grayscaleFilter(String inputFilePath, String outputFilePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(inputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
                int avg = (getRed(rgb) + getGreen(rgb) + getBlue(rgb)) / 3;
                int color = new Color(avg, avg, avg).getRGB();
                image.setRGB(x,y, color);
            }
        }
        try {
            ImageIO.write(image, "jpg", new File(outputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return image;
    }

    /**
     * Implement Sepia filter that make an image old-like looking.
     * Tip: to implement Sepia filter you need to set pixels value by this formulas:
     * Red = 0.393 * originalRed + 0.769 * originalGreen + 0.189 * originalBlue.
     * Green = 0.349 * originalRed + 0.686 * originalGreen + 0.168 * originalBlue.
     * Blue = 0.272 * originalRed + 0.534 * originalGreen + 0.131 * originalBlue.
     *
     * @param inputFilePath  - image to apply filter.
     * @param outputFilePath - image after filter applying.
     */
    @Override
    public BufferedImage sepiaFilter(String inputFilePath, String outputFilePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(inputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
                int r = getRed(rgb);
                int g = getGreen(rgb);
                int b = getBlue(rgb);
                int rs = Math.min((int) (0.393 * r + 0.769 * g + 0.189 * b), 255);
                int gs = Math.min((int) (0.349 * r + 0.686 * g + 0.168 * b), 255);
                int bs = Math.min((int) (0.272 * r + 0.534 * g + 0.131 * b), 255);
                int color = new Color(rs, gs, bs).getRGB();
                image.setRGB(x,y, color);
            }
        }
        try {
            ImageIO.write(image, "jpg", new File(outputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return image;
    }

    /**
     * Reflect an image.
     *
     * @param inputFilePath  - image to apply filter.
     * @param outputFilePath - image after filter applying.
     */
    @Override
    public BufferedImage reflectFilter(String inputFilePath, String outputFilePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(inputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth() / 2; x++) {
                int rgb1 = image.getRGB(x, y);
                int rgb2 = image.getRGB(image.getWidth() - x - 1, y);
                image.setRGB(x, y, rgb2);
                image.setRGB(image.getWidth() - x - 1, y, rgb1);
            }
        }
        try {
            ImageIO.write(image, "jpg", new File(outputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return image;
    }

    /**
     * Return original image from path.
     *
     * @param inputFilePath - image to return.
     * @return - original image.
     */
    @Override
    public BufferedImage originalImage(String inputFilePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(inputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * Return value of red in pixel.
     *
     * @param pixel - integer that represents pixel from ColorModel.class.
     * @return - integer that represents value of red in pixel.
     */
    @Override
    public int getRed(int pixel) {
        ColorModel model = ColorModel.getRGBdefault();
        return model.getRed(pixel);
    }

    /**
     * Return value of green in pixel.
     *
     * @param pixel - integer that represents pixel from ColorModel.class.
     * @return - integer that represents value of green in pixel.
     */
    @Override
    public int getGreen(int pixel) {
        ColorModel model = ColorModel.getRGBdefault();
        return model.getGreen(pixel);
    }

    /**
     * Return value of blue in pixel.
     *
     * @param pixel - integer that represents pixel from ColorModel.class.
     * @return - integer that represents value of blue in pixel.
     */
    @Override
    public int getBlue(int pixel) {
        ColorModel model = ColorModel.getRGBdefault();
        return model.getBlue(pixel);
    }
}
