package org.jis.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class GeneratorTest {

	// all necessary objects for the test
	static Generator testGenerator;
	static BufferedImage picture;

	/**
	 * Creates all necessary objects for the tests.
	 * 
	 * @throws Exception
	 *             any type of exception that may occur.
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		testGenerator = new Generator(null, 0);

		// reads in the original picture
		URL url = GeneratorTest.class.getResource("picture.jpg");
		File file = new File(url.getPath());
		picture = null;
		IIOMetadata imeta = null;

		try {
			ImageInputStream iis = ImageIO.createImageInputStream(file);
			ImageReader reader = ImageIO.getImageReadersByFormatName("jpg").next();
			reader.setInput(iis, true);
			ImageReadParam params = reader.getDefaultReadParam();
			picture = reader.read(0, params);
			imeta = reader.getImageMetadata(0);
		} catch (IOException e) {
			System.err.println("Error while reading File: " + file.getAbsolutePath());
			e.printStackTrace();
			return;
		}
	}

	@AfterClass
	public static void tearDown() throws Exception {
	}

	/**
	 * tests if rotateImage() will return the same image when a rotation value of 0 is given.
	 */
	@Test
	public void testRotateBufferedImage() {
		assertEquals(picture, testGenerator.rotateImage(picture, 0.0));
	}

	/**
	 * tests if rotateImage will return null when no picture is given.
	 */
	@Test
	public void testRotateNullImage() {
		assertEquals(null, testGenerator.rotateImage(null, 0.0));
	}

	/**
	 * tests if rotateImage throws an IllegalArgumentException when an illegal argument is given.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRotateImageIllegalArgumentException() {
		testGenerator.rotateImage(picture, 0.7);
	}

	/**
	 * tests if rotateImage does not change the width and height of the original picture.
	 */
	@Test
	public void testRotateBufferedImage90Degrees() {
		BufferedImage result = testGenerator.rotateImage(picture, Math.toRadians(90));
		assertEquals(picture.getHeight(), result.getWidth());
		assertEquals(picture.getWidth(), result.getHeight());

		// compares the picture pixel by pixel
		int width = picture.getWidth();
		int height = picture.getHeight();

		// Loop over every pixel.
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// Compare the pixels for equality.
				if (picture.getRGB(x, y) != result.getRGB(height - 1 - y, x)) {
					fail("Pictures do not match.");
				}
			}
		}
	}

	/**
	 * tests if rotateImage does not change the width and height of the original picture.
	 */
	@Test
	public void testRotateBufferedImage270Degrees() {
		BufferedImage result = testGenerator.rotateImage(picture, Math.toRadians(270));
		assertEquals(picture.getHeight(), result.getWidth());
		assertEquals(picture.getWidth(), result.getHeight());
		// compares the picture pixel by pixel
		int width = picture.getWidth();
		int height = picture.getHeight();

		// Loop over every pixel.
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// Compare the pixels for equality.
				if (picture.getRGB(x, y) != result.getRGB(y, width - 1 - x)) {
					fail("Pictures do not match.");
				}
			}
		}
	}
}
