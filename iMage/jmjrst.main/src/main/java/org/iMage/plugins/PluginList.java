package org.iMage.plugins;

/**
 * a list to store plugins in.
 * 
 * @author Joshua Eilebrecht
 *
 */
public class PluginList {

	private SortedList<JmjrstPlugin> list;

	/**
	 * constructor for the PluginList.
	 */
	public PluginList() {
		this.list = new SortedList<JmjrstPlugin>();
	}

	/**
	 * gets the list/Iterable.
	 * 
	 * @return theiterable object of this list.
	 */
	public Iterable<JmjrstPlugin> getPlugins() {
		return this.list;
	}

	SortedList<JmjrstPlugin> getIterable() {
		return this.list;
	}
}
