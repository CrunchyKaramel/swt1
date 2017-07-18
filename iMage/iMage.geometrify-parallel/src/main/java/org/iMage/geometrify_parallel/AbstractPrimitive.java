package org.iMage.geometrify_parallel;

import java.awt.Color;

import org.iMage.geometrify.BoundingBox;
import org.iMage.geometrify.IPrimitive;

/**
 * The {@link AbstractPrimitive} class centralizes functionality common to all
 * {@link IPrimitive}s
 * 
 * @author Tobias Hey
 *
 */
public abstract class AbstractPrimitive implements IPrimitive {
	private BoundingBox boundingBox;
	private Color color;

	@Override
	public BoundingBox getBoundingBox() {
		return boundingBox;
	}

	/**
	 * Sets the bounding box of this primitive.
	 * 
	 * @param boundingBox
	 *            the bounding box
	 */
	public void setBoundingBox(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color c) {
		color = c;
	}
}
