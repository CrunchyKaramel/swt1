package org.jis.generator;

import static org.junit.Assert.assertEquals;

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
	Generator testGenerator;
	BufferedImage picture;
	BufferedImage picture90deg;
	BufferedImage picture270deg;

	/**
	 * Creates all necessary objects for the tests.
	 * 
	 * @throws Exception
	 *             any type of exception that may occur.
	 */
	@BeforeClass
	public void setUp() throws Exception {
		testGenerator = new Generator(null, 0);

		// reads in the original picture
		URL url = getClass().getResource("picture.jpg");
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
		// reads in the 90 degree rotated picture
		url = getClass().getResource("picture90deg.jpg");
		file = new File(url.getPath());
		picture90deg = null;
		imeta = null;

		try {
			ImageInputStream iis = ImageIO.createImageInputStream(file);
			ImageReader reader = ImageIO.getImageReadersByFormatName("jpg").next();
			reader.setInput(iis, true);
			ImageReadParam params = reader.getDefaultReadParam();
			picture90deg = reader.read(0, params);
			imeta = reader.getImageMetadata(0);
		} catch (IOException e) {
			System.err.println("Error while reading File: " + file.getAbsolutePath());
			e.printStackTrace();
			return;
		}

		// reads in the 270 degree rotated picture
		url = getClass().getResource("picture270deg.jpg");
		file = new File(url.getPath());
		picture270deg = null;
		imeta = null;

		try {
			ImageInputStream iis = ImageIO.createImageInputStream(file);
			ImageReader reader = ImageIO.getImageReadersByFormatName("jpg").next();
			reader.setInput(iis, true);
			ImageReadParam params = reader.getDefaultReadParam();
			picture270deg = reader.read(0, params);
			imeta = reader.getImageMetadata(0);
		} catch (IOException e) {
			System.err.println("Error while reading File: " + file.getAbsolutePath());
			e.printStackTrace();
			return;
		}
	}

	@AfterClass
	public void tearDown() throws Exception {
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
		assertEquals(picture90deg, result);
	}

	/**
	 * tests if rotateImage does not change the width and height of the original picture.
	 */
	@Test
	public void testRotateBufferedImage270Degrees() {
		BufferedImage result = testGenerator.rotateImage(picture, Math.toRadians(270));
		assertEquals(picture.getHeight(), result.getWidth());
		assertEquals(picture.getWidth(), result.getHeight());
		assertEquals(picture270deg, result);
	}
}
