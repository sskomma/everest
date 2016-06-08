package com.questions.salesforce.component;

public interface ComponentInstaller 
{
	/**Creates a dependency on one another.
	 * A component with id c1 requires a component with id c2. 
	 * 
	 * @param c1, id of a component that has a dependency.
	 * @param c2, id of a component that is required for c1. 
	 */
	public void makeDependency(String c1, String c2);
	
	/**Installs a component with id c1. 
	 * Recursively reaches the end of dependency tree and installs all required components 
	 * before installing c1. 
	 * 
	 * @param c1, id of the component to be installed. 
	 */
	public void install(String c1);
	
	/**Removes component from system, if this is not required by other components 
	 * It also, tries to remove components that  
	 * 
	 * @param c1, id of the component to be removed from system. 
	 */
	public void remove(String c1);
	
	/**List all components that are installed so far. 
	 * 
	 */
	public void listInstalledComponents();
}
