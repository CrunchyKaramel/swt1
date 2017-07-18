package org.iMage.geometrify_parallel;

import org.iMage.geometrify.IPointGenerator;
import org.iMage.geometrify.IPrimitive;

public class ParallelEllipsePictureFilter extends ParallelPictureFilter {

	public ParallelEllipsePictureFilter(IPointGenerator pointGenerator, int cores) {
		super(pointGenerator, cores);
	}

	@Override
	protected IPrimitive generatePrimitive() {
		return new Ellipse(this.pointGenerator.nextPoint(), this.pointGenerator.nextPoint());
	}

}
