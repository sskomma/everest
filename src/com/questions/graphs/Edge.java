package com.questions.graphs;

class Edge<T>
{
	private boolean isDirected;
	private Vertex<T> vertex1;
	private Vertex<T> vertex2;
	private int weight;
	public Edge(Vertex<T> v1, Vertex<T> v2, int weight, boolean isDirected)
	{
		vertex1 = v1; vertex2 = v2; this.weight = weight; this.isDirected = isDirected;
	}
	public boolean isDirected() {
		return isDirected;
	}
	public void setDirected(boolean isDirected) {
		this.isDirected = isDirected;
	}
	public Vertex<T> getVertex1() {
		return vertex1;
	}
	public void setVertex1(Vertex<T> vertex1) {
		this.vertex1 = vertex1;
	}
	public Vertex<T> getVertex2() {
		return vertex2;
	}
	public void setVertex2(Vertex<T> vertex2) {
		this.vertex2 = vertex2;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isDirected ? 1231 : 1237);
		result = prime * result
				+ ((vertex1 == null) ? 0 : vertex1.hashCode());
		result = prime * result
				+ ((vertex2 == null) ? 0 : vertex2.hashCode());
		result = prime * result + weight;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (isDirected != other.isDirected)
			return false;
		if (vertex1 == null) {
			if (other.vertex1 != null)
				return false;
		} else if (!vertex1.equals(other.vertex1))
			return false;
		if (vertex2 == null) {
			if (other.vertex2 != null)
				return false;
		} else if (!vertex2.equals(other.vertex2))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Edge [isDirected=" + isDirected + vertex1
				+ ", vertex2=" + vertex2 + ", weight=" + weight + "]";
	}
	
}