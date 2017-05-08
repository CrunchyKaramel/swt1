package org.jis.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.jis.generator.Generator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * checks the method rotateImage.
 * 
 * @author joshua
 *
 */
public class RotateImageTest {

	// all necessary objects for the test
	static Generator testGenerator;
	static BufferedImage picture;
	BufferedImage result;

	/**
	 * Creates all necessary objects for the tests.
	 * 
	 * @throws Exception
	 *             any type of exception that may occur.
	 */
	@BeforeClass
	public static void setUpClass() throws Exception {
		File folder = new File("./target/data_test");
		folder.mkdirs();
		testGenerator = new Generator(null, 0);

		// reads in the original picture
		URL url = RotateImageTest.class.getResource("picture.jpg");
		File file = new File(url.getPath());
		picture = null;

		try {
			ImageInputStream iis = ImageIO.createImageInputStream(file);
			ImageReader reader = ImageIO.getImageReadersByFormatName("jpg").next();
			reader.setInput(iis, true);
			ImageReadParam params = reader.getDefaultReadParam();
			picture = reader.read(0, params);
		} catch (IOException e) {
			System.err.println("Error while reading File: " + file.getAbsolutePath());
			e.printStackTrace();
			return;
		}
	}

	/**
	 * deletes everything after tests are done.
	 * 
	 * @throws Exception
	 *             any type of exception.
	 */
	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	/**
	 * (re)sets result to null.
	 * 
	 * @throws Exception
	 *             any type of exception.
	 */
	@Before
	public void setUp() throws Exception {
		result = null;
	}

	/**
	 * saves the created result picture in ./target/data_test.
	 * 
	 * @throws Exception
	 *             any type of exception.
	 */
	@After
	public void tearDown() throws Exception {
		// write image to folder
		if (result != null) {
			File outputfile = new File("./target/data_test" + "/" + "rotatedPicture_"
					+ new SimpleDateFormat("HHmmss_SSS").format(new Date()) + ".jpg");
			ImageIO.write(result, "jpg", outputfile);
		}
	}

	/**
	 * tests if rotateImage() will return the same image when a rotation value of 0 is given.
	 */
	@Test
	public void testRotateBufferedImage() {
		result = testGenerator.rotateImage(picture, 0.0);
		assertEquals(picture, result);
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
		result = testGenerator.rotateImage(picture, Math.toRadians(90));
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
		result = testGenerator.rotateImage(picture, Math.toRadians(270));
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

	/**
	 * tests if rotateImage correctly turns the picture by 180 degrees.
	 */
	@Test
	public void testRotateBufferedImage180Degrees() {
		result = testGenerator.rotateImage(picture, Math.toRadians(180));
		assertEquals(picture.getHeight(), result.getHeight());
		assertEquals(picture.getWidth(), result.getWidth());
		// compares the picture pixel by pixel
		int width = picture.getWidth();
		int height = picture.getHeight();

		// Loop over every pixel.
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// Compare the pixels for equality.
				if (picture.getRGB(x, y) != result.getRGB(width - 1 - x, height - 1 - y)) {
					fail("Pictures do not match.");
				}
			}
		}
	}
}
