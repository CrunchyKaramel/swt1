package org.iMage.plugins;

import org.jis.Main;

/**
 * plugin class used for testing purposes only.
 * 
 * @author Joshua Eilebrecht
 *
 */
public class TestingPlugin extends JmjrstPlugin {

	/**
	 * constructor for the test plugin.
	 * 
	 * @param priority
	 *            the assigned priority
	 * @param name
	 *            the assigned name
	 */
	TestingPlugin(PluginPriority priority, String name) {
		this.priority = priority;
		this.name = name;
	}

	private String name;

	@Override
	public PluginPriority getPriority() {
		return this.priority;
	}

	@Override
	public void setPriority(PluginPriority priority) {
		this.priority = priority;
	}

	@Override
	public String getMenuText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {

		return this.name;
	}

	@Override
	public void init(Main main) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isConfigurable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void configure() {
		// TODO Auto-generated method stub

	}
}
