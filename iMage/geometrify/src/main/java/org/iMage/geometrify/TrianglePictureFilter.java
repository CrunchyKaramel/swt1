package org.iMage.geometrify;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class TrianglePictureFilter extends AbstractPrimitivePictureFilter {

	public TrianglePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}

	@Override
	protected Color calculateColor(BufferedImage image, IPrimitive primitive) {
		Color color = null;
		for (int a = primitive.getBoundingBox().getUpperLeftCorner().x; a <= primitive.getBoundingBox()
				.getLowerRightCorner().x; a++) {
			for (int b = primitive.getBoundingBox().getUpperLeftCorner().y; b <= primitive.getBoundingBox()
					.getLowerRightCorner().y; b++) {
				if (primitive.isInsidePrimitive(new Point(a, b))) {
					if (color == null) {
						color = new Color(image.getRGB(a, b));
					} else {
						Color newColor = new Color(image.getRGB(a, b));
						double totalAlpha = color.getAlpha() + newColor.getAlpha();
						double weight0 = color.getAlpha() / totalAlpha;
						double weight1 = newColor.getAlpha() / totalAlpha;

						double c = weight0 * color.getRed() + weight1 * newColor.getRed();
						double d = weight0 * color.getGreen() + weight1 * newColor.getGreen();
						double e = weight0 * color.getBlue() + weight1 * newColor.getBlue();
						double f = Math.max(color.getAlpha(), newColor.getAlpha());
						color = new Color((int) c, (int) d, (int) e, (int) f);
					}
				}
			}
		}
		return color;
	}

	@Override
	public BufferedImage apply(BufferedImage image, int numberOfIterations, int numberOfSamples) {
		this.pointGenerator = new RandomPointGenerator(image.getWidth(), image.getHeight());
		/*
		 * extend solution, if possible
		 */
		return null;
	}

	@Override
	protected IPrimitive generatePrimitive() {
		Triangle primitive = new Triangle(this.pointGenerator.nextPoint(), this.pointGenerator.nextPoint(),
				this.pointGenerator.nextPoint());
		return primitive;
	}

	@Override
	protected int calculateDifference(BufferedImage original, BufferedImage current, IPrimitive primitive) {
		/*
		 * YOUR SOLUTION HERE
		 */
		return 0;
	}

	@Override
	protected void addToImage(BufferedImage image, IPrimitive primitive) {
		/*
		 * YOUR SOLUTION HERE
		 */
	}
}
