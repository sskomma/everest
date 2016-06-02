package com.questions.salesforce.component;

public interface ComponentInstaller 
{
	/**Creates a dependency on one another.
	 * A component with id c1 requires a component with id c2. 
	 * 
	 * @param c1, id of a component that has a dependency.
	 * @param c2, id of a component that is required for c1. 
	 */
	public void makeDependency(long c1, long c2);
	
	/**Installs a component with id c1. 
	 * Recursively reaches the end of dependency tree and installs all required components 
	 * before installing c1. 
	 * 
	 * @param c1, id of the component to be installed. 
	 */
	public void install(long c1);
	
	/**Removes component from system. 
	 * It removes all components that require c1 directly or transitively. 
	 * 
	 * @param c1, id of the component to be removed from system. 
	 */
	public void remove(long c1);
}
