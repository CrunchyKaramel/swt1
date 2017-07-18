package org.iMage.geometrify_parallel;

import org.iMage.geometrify.IPointGenerator;
import org.iMage.geometrify.IPrimitive;

/**
 * A picture filter that uses parallelization to determine the non-axis alligned
 * rectangles that get used for recreating the picture.
 * 
 * @author Joshua Eilebrecht
 *
 */
public class ParallelNAATRPictureFilter extends ParallelPictureFilter {

	/**
	 * Creates an instance of this filter.
	 * 
	 * @param pointGenerator
	 *            the point generator used for the primitives.
	 * @param cores
	 *            the cores of the CPU
	 */
	public ParallelNAATRPictureFilter(IPointGenerator pointGenerator, int cores) {
		super(pointGenerator, cores);
	}

	@Override
	protected IPrimitive generatePrimitive() {
		return new NonAxisAlignedRectangle(this.pointGenerator.nextPoint(), this.pointGenerator.nextPoint(),
				this.pointGenerator.nextPoint());
	}
}
