package org.jis.generator;

//todo reduce import 
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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GeneratorTest {
	
	//all necessary objects for the test
	Generator testGenerator;
	BufferedImage i;

	@Before
	public void setUp() throws Exception {
		testGenerator = new Generator(null, 0);
		
		//puts a picture in a BufferedImage object
		URL url = getClass().getResource("picture.jpg");
		File file = new File(url.getPath());
		i = null;
	    IIOMetadata imeta = null;

	    try
	    {
	      ImageInputStream iis = ImageIO.createImageInputStream(file);
	      ImageReader reader = ImageIO.getImageReadersByFormatName("jpg").next();
	      reader.setInput(iis, true);
	      ImageReadParam params = reader.getDefaultReadParam();
	      i = reader.read(0, params);
	      imeta = reader.getImageMetadata(0);
	    }
	    catch (IOException e)
	    {
	      System.err.println("Error while reading File: " + file.getAbsolutePath());
	      e.printStackTrace();
	      return;
	    }
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
