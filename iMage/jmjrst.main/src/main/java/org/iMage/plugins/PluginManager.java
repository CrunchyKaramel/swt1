package org.iMage.plugins;

import java.util.ServiceLoader;

/**
 * Knows all available plug-ins and is responsible for using the service loader
 * API to detect them.
 *
 */
public final class PluginManager {

	/**
	 * No constructor for utility class.
	 */
	private PluginManager() {
	}

	/**
	 * Return all available {@link JmjrstPlugin}s sorted accordingly to their
	 * priority.
	 * 
	 * @return all available plug-ins sorted according to their priority in
	 *         ascending order.
	 */
	public static Iterable<JmjrstPlugin> getPlugins() {
		ServiceLoader<JmjrstPlugin> serviceLoader = ServiceLoader.load(JmjrstPlugin.class);

		PluginList p = new PluginList();
		for (final JmjrstPlugin plugin : serviceLoader) {
			p.getIterable().getIterator().add(plugin);
		}
		return p.getPlugins();
	}
}
