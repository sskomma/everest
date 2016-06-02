package com.questions.salesforce.component;

public interface ComponentInstaller 
{
	public void makeDependency(long c1, long c2);
	public void install(long c1);
	public void remove(long c1);
	public void printInstalledComponents();

}
