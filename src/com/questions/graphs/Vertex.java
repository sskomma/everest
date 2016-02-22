package com.questions.graphs;

import java.util.ArrayList;
import java.util.List;

public 	class Vertex<T>{
	private long id;
	private T data;
	private List<Edge<T>> adjcentEdges = new ArrayList<Edge<T>>();
	private List<Vertex<T>> adjcentVerteces = new ArrayList<Vertex<T>>();
	
	public Vertex(long id)
	{
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public List<Vertex<T>> getAdjcentVerteces() {
		return adjcentVerteces;
	}
	public void addAdjcentVertex(Edge<T> edge, Vertex<T> adjcentVertex) {
		adjcentEdges.add(edge);
		adjcentVerteces.add(adjcentVertex);
	}
	public List<Edge<T>> getAdjcentEdges() {
		return adjcentEdges;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Vertex<T> other = (Vertex<T>) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Vertex [id=" + id + "]";
	}
}