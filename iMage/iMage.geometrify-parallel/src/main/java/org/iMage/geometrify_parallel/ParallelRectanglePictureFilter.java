package org.iMage.geometrify_parallel;

import org.iMage.geometrify.IPointGenerator;
import org.iMage.geometrify.IPrimitive;

public class ParallelRectanglePictureFilter extends ParallelPictureFilter {

	public ParallelRectanglePictureFilter(IPointGenerator pointGenerator, int cores) {
		super(pointGenerator, cores);
	}

	@Override
	protected IPrimitive generatePrimitive() {
		return new Rectangle(this.pointGenerator.nextPoint(), this.pointGenerator.nextPoint());
	}
}
