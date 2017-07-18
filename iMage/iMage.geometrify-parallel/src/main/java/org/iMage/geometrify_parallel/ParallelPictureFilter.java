/**
 * 
 */
package org.iMage.geometrify_parallel;

import java.awt.image.BufferedImage;

import org.iMage.geometrify.IPointGenerator;
import org.iMage.geometrify.IPrimitive;
import org.iMage.geometrify.TrianglePictureFilter;

/**
 * an abstract that calculates a filter of an image using parallelisation.
 * 
 * @author Joshua Eilebrecht
 *
 */
public abstract class ParallelPictureFilter extends TrianglePictureFilter {

	private int numberOfIterations;
	private BufferedImage image;
	private MonitoredArrayCell[][] levels;
	private BufferedImage current;
	private int cores;

	public ParallelPictureFilter(IPointGenerator pointGenerator, int cores) {
		super(pointGenerator);
		this.cores = cores;
	}

	@Override
	public BufferedImage apply(BufferedImage image, int numberOfIterations, int numberOfSamples) {
		Thread[] threads = new Thread[this.cores];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new GeometrifyRunnable(this));
			threads[i].start();
		}
		this.image = image;
		this.numberOfIterations = numberOfIterations;
		this.levels = new MonitoredArrayCell[numberOfIterations][numberOfSamples];
		int width = image.getWidth();
		int height = image.getHeight();

		// construct "empty" image
		BufferedImage result = new BufferedImage(width, height, image.getType());

		for (int i = 0; i < numberOfIterations; i++) {
			addToImage(result, levels[levels.length - 1][1].getContent());
		}
		return result;
	}

	protected MonitoredArrayCell[][] getLevels() {
		return this.levels;
	}

	protected BufferedImage getImage() {
		return this.image;
	}

	/**
	 * a way to calculate the difference between the to be edited picture and a
	 * primitive.
	 * 
	 * @param primitive
	 *            any primitive
	 * @return how big the difference is. The bigger the number the beigger the
	 *         difference.
	 */
	protected int calculateDifference(IPrimitive primitive) {
		return this.calculateDifference(image, current, primitive);
	}

	@Override
	protected IPrimitive generatePrimitive() {
		// TODO implement me
		return null;
	}
}
