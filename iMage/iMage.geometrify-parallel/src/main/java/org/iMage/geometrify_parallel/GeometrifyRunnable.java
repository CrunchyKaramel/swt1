/**
 * 
 */
package org.iMage.geometrify_parallel;

import org.iMage.geometrify.IPrimitive;

/**
 * runnable that creates primitives and sorts them after similarity to the
 * picture.
 * 
 * @author Joshua Eilebrecht
 *
 */
public class GeometrifyRunnable implements Runnable {

	private ParallelPictureFilter filter;
	private MonitoredArrayCell[][] levels;
	private IPrimitive currentTriangle;

	/**
	 * Creates a runnable for the filter.
	 * 
	 * @param filter
	 *            the associated filter
	 */
	protected GeometrifyRunnable(ParallelPictureFilter filter) {
		this.filter = filter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		for (int i = 0; i < this.filter.getLevels().length; i++) {
			this.levels = filter.getLevels();
			if (i == 0) {
				int j = 0;
				while (j < this.levels[i].length) {
					if (!this.levels[i][j].isLocked() && this.levels[i][j].setContent(currentTriangle)) {
						this.currentTriangle = this.filter.generatePrimitive();
					}
					j++;
				}
			} else {
				int j = 0;
				while (j < this.levels[i].length / Math.pow(2, i)) {
					if (!this.levels[i][j].isLocked()) {
						if (this.filter.calculateDifference(this.levels[i - 1][2 * j - 1].getContent()) < this.filter
								.calculateDifference(this.levels[i - 1][2 * j].getContent())) {
							this.levels[i][j].setContent(this.levels[i - 1][2 * j - 1].getContent());
						} else {
							this.levels[i][j].setContent(this.levels[i - 1][2 * j].getContent());
						}
					}
				}
			}
		}
	}
}
