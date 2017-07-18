package org.iMage.geometrify_parallel;

import org.iMage.geometrify.IPointGenerator;
import org.iMage.geometrify.IPrimitive;

public class ParallelNAATRPictureFilter extends ParallelPictureFilter {

	public ParallelNAATRPictureFilter(IPointGenerator pointGenerator, int cores) {
		super(pointGenerator, cores);
	}

	@Override
	protected IPrimitive generatePrimitive() {
		return new NonAxisAlignedRectangle(this.pointGenerator.nextPoint(), this.pointGenerator.nextPoint(),
				this.pointGenerator.nextPoint());
	}
}
