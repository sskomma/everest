package com.questions.salesforce.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Component {

  private String component;
  private List<Component> reqComponents;
  private List<Component> dependentComponents;

  public Component(String component) {
    this.component = component;
  }

  public void addRequiredComponent(Component d) {
    if (reqComponents == null) {
      reqComponents = new ArrayList<Component>();
    }
    reqComponents.add(d);
  }

  public List<Component> getRequiredComponents() {
    if (reqComponents == null) {
      return Collections.emptyList();
    }
    return reqComponents;
  }

  public void addDependentComponent(Component d) {
    if (dependentComponents == null) {
      dependentComponents = new ArrayList<Component>();
    }
    dependentComponents.add(d);
  }

  public List<Component> getDependentComponents() {
    if (dependentComponents == null) {
      return Collections.emptyList();
    }
    return dependentComponents;
  }

  public String getComponentName() {
    return component;
  }

  public int hashCode() {
    return component.hashCode();
  }

  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Component other = (Component) obj;
    if (component.equals(other.getComponentName())) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Component [" + component + "]";
  }
}
