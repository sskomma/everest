package com.questions.salesforce.component;

public class ComponentInstallerApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ComponentInstaller c = new ComponentInstallerImpl();
		c.makeDependency(2, 1);
		c.makeDependency(2, 5);
		c.makeDependency(3, 1);
		c.makeDependency(4, 3);
		c.install(4);
		c.install(2);
		c.remove(1);
	}

}
