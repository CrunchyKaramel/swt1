package org.jis.generator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GeneratorTest {

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
	public static void setUpBeforeClass() throws Exception {
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

	@Before
	public void setUp() throws Exception {
		testGenerator = new Generator(null, 0);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateZip() {
		testGenerator.createZip(new File("./target/data_test/testZip"), new Vector<File>());
	}

}
