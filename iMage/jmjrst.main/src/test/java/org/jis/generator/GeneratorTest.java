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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GeneratorTest {

	// all necessary objects for the test
	Generator testGenerator;
	BufferedImage i;

	/**
	 * Creates all necessary objects for the tests.
	 * 
	 * @throws Exception
	 *             any type of exception that may occur.
	 */
	@Before
	public void setUp() throws Exception {
		testGenerator = new Generator(null, 0);

		// puts a picture in a BufferedImage object
		URL url = getClass().getResource("picture.jpg");
		File file = new File(url.getPath());
		i = null;
		IIOMetadata imeta = null;

		try {
			ImageInputStream iis = ImageIO.createImageInputStream(file);
			ImageReader reader = ImageIO.getImageReadersByFormatName("jpg").next();
			reader.setInput(iis, true);
			ImageReadParam params = reader.getDefaultReadParam();
			i = reader.read(0, params);
			imeta = reader.getImageMetadata(0);
		} catch (IOException e) {
			System.err.println("Error while reading File: " + file.getAbsolutePath());
			e.printStackTrace();
			return;
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * tests if rotateImage() will return the same image when a rotation value of 0 is given.
	 */
	@Test
	public void testRotateBufferedImage() {
		assertEquals(i, testGenerator.rotateImage(i, 0.0));
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
		testGenerator.rotateImage(i, 0.7);
	}
}
