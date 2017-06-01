package org.iMage.plugins;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
	public static List<JmjrstPlugin> getPlugins() {
		Iterator<JmjrstPlugin> plugins = ServiceLoader.load(JmjrstPlugin.class).iterator();
		ArrayList<JmjrstPlugin> list = new ArrayList<JmjrstPlugin>();
		while (plugins.hasNext()) {
			JmjrstPlugin plugin = plugins.next();
			if (list.isEmpty()) {
				list.add(plugin);
			} else {
				for (int i = 0; i < list.size(); i++) {
					if (!list.get(i).getPriority().name().equalsIgnoreCase("HIGH")
							&& !plugin.getPriority().name().equalsIgnoreCase("LOW")) {
						if (list.get(i).getPriority().name().equalsIgnoreCase("MID")
								&& plugin.getPriority().name().equalsIgnoreCase("HIGH")
								|| list.get(i).getPriority().name().equalsIgnoreCase("LOW")) {
							list.add(i, plugin);
							plugin = null;
							i = list.size();
						}
					}
				}
				if (plugin != null) {
					list.add(plugin);
				}
			}
		}
		return list;
	}
}
