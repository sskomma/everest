package com.questions.salesforce.component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ComponentInstallerImpl implements ComponentInstaller {
  private Map<String, Component> componentRegistry;
  private Set<Component> installedComponents;

  public ComponentInstallerImpl() {
    componentRegistry = new HashMap<String, Component>();
    installedComponents = new HashSet<Component>();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void makeDependency(String c1, String c2) {
    Component comp1, comp2;
    comp1 = componentRegistry.get(c1);
    if (comp1 == null) {
      comp1 = new Component(c1);
      componentRegistry.put(c1, comp1);
    }

    comp2 = componentRegistry.get(c2);
    if (comp2 == null) {
      comp2 = new Component(c2);
      componentRegistry.put(c2, comp2);
    }
    comp1.addRequiredComponent(comp2);
    comp2.addDependentComponent(comp1);
    System.out.println(comp1 + " requires " + comp2);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void install(String c1) {
    Component component = componentRegistry.get(c1);
    if (component == null) {
      component = new Component(c1);
      componentRegistry.put(c1, component);
    }
    for (Component comp : component.getRequiredComponents()) {
      if (installedComponents.contains(comp)) {
        System.out.println(component.getComponentName() + " is already installed");
      } else {
        install(comp.getComponentName());
        installedComponents.add(component);
        System.out.println("Installing " + component);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void remove(String component) {
    Component c = componentRegistry.get(component);
    if (!isComponentDangling(c)) {
      System.out.println(c.getComponentName() + "is still Needed");
    } else {

    }
  }

  private boolean isComponentDangling(Component comp) {
    for (Component c : comp.getDependentComponents()) {
      if (installedComponents.contains(c)) {
        return false;
      }
    }
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void listInstalledComponents() {
    for (Component c : installedComponents) {
      System.out.println(c.getComponentName());
    }
  }
}
