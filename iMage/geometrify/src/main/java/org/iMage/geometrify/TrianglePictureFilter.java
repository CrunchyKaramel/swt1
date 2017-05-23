package org.iMage.geometrify;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * A filter to recreate pictures using triangle shapes.
 * 
 * @author Joshua Eilebrecht
 * @version 1.0
 *
 */
public class TrianglePictureFilter extends AbstractPrimitivePictureFilter {

	/**
	 * Initializes the filter.
	 * 
	 * @param pointGenerator
	 *            a simple IPointGenerator.
	 */
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
						color = new Color(image.getRGB(a, b), true);
					} else {
						Color newColor = new Color(image.getRGB(a, b), true);
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
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		this.pointGenerator = new RandomPointGenerator(image.getWidth(), image.getHeight());
		for (int a = 0; a < numberOfIterations; a++) {
			IPrimitive triangle = null;
			for (int b = 0; b < numberOfSamples; b++) {
				IPrimitive currentTriangle = generatePrimitive();
				currentTriangle.setColor(calculateColor(image, currentTriangle));
				if (triangle == null || calculateDifference(image, newImage,
						currentTriangle) > calculateDifference(image, newImage, triangle)) {
					triangle = currentTriangle;
				}
			}
			addToImage(newImage, triangle);
		}
		return newImage;
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
		for (int a = primitive.getBoundingBox().getUpperLeftCorner().x; a <= primitive.getBoundingBox()
				.getLowerRightCorner().x; a++) {
			for (int b = primitive.getBoundingBox().getLowerRightCorner().y; b <= primitive.getBoundingBox()
					.getUpperLeftCorner().y; b++) {
				if (primitive.isInsidePrimitive(new Point(a, b))) {
					Color c0 = new Color(image.getRGB(a, b), true);
					Color c1 = primitive.getColor();
					double totalAlpha = c0.getAlpha() + c1.getAlpha();
					double weight0 = c0.getAlpha() / totalAlpha;
					double weight1 = c1.getAlpha() / totalAlpha;

					double c = weight0 * c0.getRed() + weight1 * c1.getRed();
					double d = weight0 * c0.getGreen() + weight1 * c1.getGreen();
					double e = weight0 * c0.getBlue() + weight1 * c1.getBlue();
					double f = Math.max(c0.getAlpha(), c1.getAlpha());
					Color color = new Color((int) c, (int) d, (int) e, (int) f);
					image.setRGB(a, b, color.getRGB());
				}
			}
		}
	}
}
