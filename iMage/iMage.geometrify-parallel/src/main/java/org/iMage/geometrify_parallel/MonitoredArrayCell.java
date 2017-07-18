/**
 * 
 */
package org.iMage.geometrify_parallel;

import org.iMage.geometrify.IPrimitive;

/**
 * @author Joshua Eilebrecht
 *
 */
public class MonitoredArrayCell {
	private IPrimitive content;
	private boolean isLocked;

	protected MonitoredArrayCell() {
		this.content = null;
		this.isLocked = false;
	}

	protected IPrimitive getContent() {
		return this.content;
	}

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

	protected boolean isLocked() {
		return this.isLocked;
	}
}
