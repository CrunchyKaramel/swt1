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
		if (primitive.pointA.y == primitive.pointB.y && primitive.pointA.y == primitive.pointC.y
				|| primitive.pointA.x == primitive.pointB.x && primitive.pointA.x == primitive.pointC.x
				|| primitive.pointA.x == primitive.pointB.x && primitive.pointA.y == primitive.pointB.y
				|| primitive.pointA.x == primitive.pointC.x && primitive.pointA.y == primitive.pointC.y
				|| primitive.pointB.x == primitive.pointC.x && primitive.pointB.y == primitive.pointC.y) {
			return generatePrimitive();
		}
		return primitive;
	}

	@Override
	protected int calculateDifference(BufferedImage original, BufferedImage current, IPrimitive primitive) {
		BufferedImage mem = current;
		addToImage(mem, primitive);
		long correctPixels = 0L;
		for (int a = primitive.getBoundingBox().getUpperLeftCorner().x; a <= primitive.getBoundingBox()
				.getLowerRightCorner().x; a++) {
			for (int b = primitive.getBoundingBox().getLowerRightCorner().y; b <= primitive.getBoundingBox()
					.getUpperLeftCorner().y; b++) {
				if (original.getRGB(a, b) == original.getRGB(a, b)) {
					correctPixels++;
				}
			}
		}
		return (int) (correctPixels / ((primitive.getBoundingBox().getLowerRightCorner().x
				- primitive.getBoundingBox().getUpperLeftCorner().x)
				* (primitive.getBoundingBox().getUpperLeftCorner().y
						- primitive.getBoundingBox().getLowerRightCorner().y)));
	}

	@Override
	protected void addToImage(BufferedImage image, IPrimitive primitive) {
		/*
		 * YOUR SOLUTION HERE
		 */
	}
}
