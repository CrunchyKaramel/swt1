/**
 * 
 */
package org.iMage.geometrify_parallel;

import org.iMage.geometrify.IPrimitive;

/**
 * A Cell for an array. This cell will be locked for other threads when a thread
 * tries to edit it.
 * 
 * @author Joshua Eilebrecht
 *
 */
public class MonitoredArrayCell {
	private IPrimitive content;
	private boolean isLocked;

	/**
	 * Creates an empty Cell.
	 */
	protected MonitoredArrayCell() {
		this.content = null;
		this.isLocked = false;
	}

	/**
	 * Returns the content of the cell.
	 * 
	 * @return contained IPrimitive
	 */
	protected IPrimitive getContent() {
		return this.content;
	}

	/**
	 * Sets the content of the cell. Will be locked for other threads when a
	 * thread calls this method.
	 * 
	 * @param triangle
	 *            the desired new content
	 * @return boolean true if the content was successfully changed, otherwise
	 *         false
	 */
	protected synchronized boolean setContent(IPrimitive triangle) {
		this.isLocked = true;
		if (this.content == null) {
			this.content = triangle;
			this.isLocked = false;
			return true;
		}
		this.isLocked = false;
		return false;
	}

	/**
	 * States if the cell is locked.
	 * 
	 * @return true if the cell is locked, otherwise false.
	 */
	protected boolean isLocked() {
		return this.isLocked;
	}
}
