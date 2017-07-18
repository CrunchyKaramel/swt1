package org.iMage.geometrify_parallel;

import org.iMage.geometrify.IPointGenerator;
import org.iMage.geometrify.IPrimitive;

public class ParallelSquarePictureFilter extends ParallelPictureFilter {

	public ParallelSquarePictureFilter(IPointGenerator pointGenerator, int cores) {
		super(pointGenerator, cores);
	}

	@Override
	protected IPrimitive generatePrimitive() {
		return new Square(this.pointGenerator.nextPoint(), this.pointGenerator.nextPoint());
	}
}
