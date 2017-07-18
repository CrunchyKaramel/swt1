package org.iMage.geometrify_parallel;

import org.iMage.geometrify.IPointGenerator;
import org.iMage.geometrify.IPrimitive;

/**
 * A picture filter that uses parallelization to determine the triangles that
 * get used for recreating the picture.
 * 
 * @author Joshua Eilebrecht
 *
 */
public class ParallelTrianglePictureFilter extends ParallelPictureFilter {

	/**
	 * Creates a new instance of this filter.
	 * 
	 * @param pointGenerator
	 *            the used point generator for the primitives
	 * @param cores
	 *            the cores of the CPU
	 */
	public ParallelTrianglePictureFilter(IPointGenerator pointGenerator, int cores) {
		super(pointGenerator, cores);
	}

	@Override
	protected IPrimitive generatePrimitive() {
		return new Triangle(this.pointGenerator.nextPoint(), this.pointGenerator.nextPoint(),
				this.pointGenerator.nextPoint());
	}
}
