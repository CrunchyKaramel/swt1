package org.iMage.plugins;

import java.util.ListIterator;

/**
 * the iterator for the SortedList.
 * 
 * @author Joshua Eilebrecht
 *
 * @param <E>
 *            any type of object
 */
public class SortedListIterator<E> implements ListIterator<E> {
	int index;
	SortedListCell<E> previous;
	SortedListCell<E> next;
	E lastReturned;

	/**
	 * constructor for the iterator
	 */
	SortedListIterator() {
		this.index = 0;
		this.previous = null;
		this.next = null;
	}

	@Override
	public boolean hasNext() {
		if (this.next != null) {
			return true;
		}
		return false;
	}

	@Override
	public E next() {
		if (this.next != null) {
			this.previous = this.next;
			this.next = this.next.getNext();
			this.lastReturned = this.previous.get();
			return this.previous.get();
		}
		return null;
	}

	@Override
	public boolean hasPrevious() {
		if (this.previous != null) {
			return true;
		}
		return false;
	}

	@Override
	public E previous() {
		if (this.previous != null) {
			this.next = this.previous;
			this.previous = this.previous.getPrevious();
			this.lastReturned = this.next.get();
			return this.next.get();
		}
		return null;
	}

	@Override
	public int nextIndex() {
		return this.index;
	}

	@Override
	public int previousIndex() {
		if (this.index > 0) {
			return this.index;
		}
		return 0;
	}

	@Override
	public void remove() {
		SortedListCell<E> mem = this.next;
		int memIndex = 0;
		int callBack = this.index;
		while (mem.getPrevious() != null) {
			mem = mem.getPrevious();
		}
		while (mem != null && !mem.get().equals(lastReturned)) {
			mem = mem.getNext();
			memIndex++;
		}
		if (mem != null && mem.get().equals(lastReturned)) {
			while (this.index < memIndex) {
				this.next();
			}
			while (this.index > memIndex) {
				this.previous();
			}
			if (this.previous != null) {
				this.previous.setNext(this.next.getNext());
			}
			this.next.getNext().setPrevious(this.previous);
			this.next = this.next.getNext();
		}

		while (this.index != callBack) {
			if (this.index < callBack) {
				this.next();
			} else {
				this.previous();
			}
		}
	}

	@Override
	public void set(E e) {
		if (this.next != null) {
			this.next.set(e);
		} else if (this.previous != null) {
			this.previous.set(e);
		}
	}

	@Override
	public void add(E e) {
		SortedListCell<E> newCell = new SortedListCell<E>(e);
		if (this.previous != null) {
			this.previous.setNext(newCell);
			newCell.setPrevious(this.previous);
		}
		if (this.next != null) {
			this.next.setPrevious(newCell);
			newCell.setNext(this.next);
		}
		this.next = newCell;
	}

}
