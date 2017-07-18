package org.iMage.geometrify_parallel;

import org.iMage.geometrify.IPointGenerator;
import org.iMage.geometrify.IPrimitive;

public class ParallelCirclePictureFilter extends ParallelPictureFilter {

	public ParallelCirclePictureFilter(IPointGenerator pointGenerator, int cores) {
		super(pointGenerator, cores);
	}

	@Override
	protected IPrimitive generatePrimitive() {
		return new Circle(this.pointGenerator.nextPoint(), this.pointGenerator.nextPoint());
	}
}
