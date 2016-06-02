package com.questions.salesforce.component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ComponentInstallerImpl implements ComponentInstaller
{
	private Map<Long, Component> componentRegistry;
	private Set<Component> installedComponents;
	
	public ComponentInstallerImpl()
	{
		componentRegistry = new HashMap<>();
		installedComponents = new HashSet<>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void makeDependency(long c1, long c2) 
	{
		Component comp1, comp2;
		comp1 = componentRegistry.get(c1);
		if( comp1 == null)
		{
			comp1 = new Component(c1);
			componentRegistry.put(c1,comp1 );
		}
		
		comp2 = componentRegistry.get(c2);
		if(comp2 == null)
		{
			comp2 = new Component(c2);
			componentRegistry.put(c2, comp2);
		}
		comp1.addRequiredComponent(comp2);
		comp2.addDependentComponent(comp1);
		System.out.println(comp1 + " requires "+ comp2);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void install(long c1) {
		Component component = componentRegistry.get(c1);
		if(component == null)
		{
			component = new Component(c1);
			componentRegistry.put(c1, component);
		}
		for(Component comp: component.getRequiredComponents())
		{
			install(comp.getId());
		}
		installedComponents.add(component);
		System.out.println("Installed " + component);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(long c1) {
		removeDependentComponents(c1);
	
	}
	private void removeDependentComponents(long c1)
	{
		Component component = componentRegistry.get(c1);
		if(component == null)
			return;
		installedComponents.remove(component);
		System.out.println("Removed "+ component);
		//Remove all components that need on c1.
		for(Component comp: component.getDependentComponents())
		{
			removeDependentComponents(comp.getId());
		}
	}
	private boolean isComponentDangling(Component comp)
	{
		for(Component c:comp.getDependentComponents())
		{
			if(installedComponents.contains(c))
				return false;
		}
		return true;
	}
}
