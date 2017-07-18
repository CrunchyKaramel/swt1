package org.iMage.geometrify_parallel;

import org.iMage.geometrify.IPointGenerator;
import org.iMage.geometrify.IPrimitive;

public class ParallelTrianglePictureFilter extends ParallelPictureFilter {

	public ParallelTrianglePictureFilter(IPointGenerator pointGenerator, int cores) {
		super(pointGenerator, cores);
	}

	@Override
	protected IPrimitive generatePrimitive() {
		return new Triangle(this.pointGenerator.nextPoint(), this.pointGenerator.nextPoint(),
				this.pointGenerator.nextPoint());
	}
}
