package com.apple.graph;

import java.util.Set;

/**
 * Graph node interface.
 */
public interface Node {

  /**
   * Gives name of the node.
   * @return name of node.
   */
  String name();

  /**
   * Gives all children of node.
   * @return {@link Set} of children.
   */
  Set<Node> children();
}