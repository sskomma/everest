# Coding exercise:

## General Instructions:
* Sending you as a maven project as well, so it can be imported into an IDE.
* Java version: >= 1.8
* Maven version: >= 3.3.9

## Cache
<p>
For the given cache interface, a Least Recently used cache implementation is provided. LRUCache is a singleton class. To ensure, its singularity,
</p>

* Constructor is made private.
* A getInstance() method is written with double checked locking.
* Clone method is overridden to return the same instance.

To ensure type safety, a key interface is created and implemented that takes in Type of value being associated.

Time Complexity: O(1) for put and get operations.
Space Complexity: O(n) for cache, where n is the size of cache.

## Directed Graph
<p>
A directed graph is implemented as requested.
</p>
*Assumptions:*
* The graph is immutable.

*Future scope:*
<p>The graph can be made mutable by adding following functionality. </p>
* Add Node
* Remove Node
* Add Relation
* Remove Relation

The validation methods are package private so they are visible for testing, without using reflections api. They are also static because, they are state less.


### findConnections:

Find connections method builds a temporary data store of set of nodes at each level for each node. The idea is that, if I (as a node) know my connections from all my connections at each level from 1 to (depth - 1), can easily answer any requests to give all connections at level x (1 < x < depth).
Time complexity: O(nd) where n is number of nodes in graph, d is the depth asked.
Space Complexity: O(nd)

## connected

It uses good old BFS to solve.

Time complexity: O(n) where n is number of nodes in graph, d is the depth asked.
Space Complexity: O(n)