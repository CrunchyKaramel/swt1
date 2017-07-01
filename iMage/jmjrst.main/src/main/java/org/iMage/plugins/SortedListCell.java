package org.iMage.plugins;

/**
 * A list cell for a doubly linked list.
 * 
 * @author Joshua Eilebrecht
 *
 * @param <E>
 *            any type of object
 */
public class SortedListCell<E extends JmjrstPlugin> {
	private SortedListCell<E> previous;
	private SortedListCell<E> next;
	private E content;

	/**
	 * creates a filled cell with no neighbors.
	 * 
	 * @param content
	 *            the content that this cell is filled with
	 */
	SortedListCell(E content) {
		this.content = content;
		this.previous = null;
		this.next = null;
	}

	/**
	 * gets the content of the cell.
	 * 
	 * @return the content of the cell
	 */
	E get() {
		return this.content;
	}

	/**
	 * gets the next neighbor.
	 * 
	 * @return the next neighbor
	 */
	SortedListCell<E> getNext() {
		return this.next;
	}

	/**
	 * gets the previous neighbor.
	 * 
	 * @return the previous neighbor
	 */
	SortedListCell<E> getPrevious() {
		return this.previous;
	}

	/**
	 * sets the content of the cell. Does not do anything when content is null.
	 * 
	 * @param content
	 *            the new content of the cell
	 */
	void set(E content) {
		if (content != null) {
			this.content = content;
		}
	}

	/**
	 * sets the next neighbor of the cell.
	 * 
	 * @param next
	 *            the new next neighbor of the cell
	 */
	void setNext(SortedListCell<E> next) {
		this.next = next;
	}

	/**
	 * sets the previous neighbor of the cell.
	 * 
	 * @param previous
	 *            the new previous neighbor of the cell
	 */
	void setPrevious(SortedListCell<E> previous) {
		this.previous = previous;
	}

}
