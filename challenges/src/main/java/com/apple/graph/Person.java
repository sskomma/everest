package com.apple.graph;

import com.google.common.base.Objects;

import java.util.HashSet;
import java.util.Set;

/**
 * A node implementation.
 */
public class Person implements Node {

  private String name;
  private Set<Node> children;

  /**
   * Person constructor.
   * @param name name of the node.
   */
  public Person(String name) {
    this.name = name;
    this.children = new HashSet<>();
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public Set<Node> children() {
    return children;
  }

  /**
   * A method to add children to node.
   * @param person person to be added as child.
   * @return true if addition was successful; false otherwise.
   */
  public boolean addChild(Node person) {
    return children.add(person);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Person)) {
      return false;
    }
    Person person = (Person) o;
    return Objects.equal(name, person.name);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name);
  }

  @Override
  public String toString() {
    return "Person =" + name;
  }
}
