package com.questions.salesforce.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Component {

	private long id;
	private List<Component> reqComponents;
	private List<Component> dependentComponents;
	
	public Component(long id)
	{
		this.id = id;
	}
	public void addRequiredComponent(Component d)
	{
		if(reqComponents == null)
			reqComponents = new ArrayList<>();
		reqComponents.add(d);
	}
	
	public List<Component> getRequiredComponents()
	{
		if( reqComponents == null)
			return Collections.emptyList();
		return reqComponents;
	}
	
	public void addDependentComponent(Component d)
	{
		if(dependentComponents == null)
			dependentComponents = new ArrayList<>();
		dependentComponents.add(d);
	}
	
	public List<Component> getDependentComponents()
	{
		if( dependentComponents == null)
			return Collections.emptyList();
		return dependentComponents;
	}
	public long getId()
	{
		return id;
	}
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Component other = (Component) obj;
		if (id != other.getId())
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Component [" + id + "]";
	}
	
}
