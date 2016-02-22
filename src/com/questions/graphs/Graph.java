package com.questions.graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**Basic graph structure, that will be used in solving problems related to graphs. 
 * 
 * @author Ram Komma
 *
 * @param <T>
 */
public class Graph<T>
{
	private List<Edge<T>> allEdges;
	private Map<Long, Vertex<T>> vertecesMap;
	private boolean isDirected = false;
	
	public Graph(boolean isDirected)
	{
		this.isDirected = isDirected; 
		allEdges = new ArrayList<Edge<T>>();
		vertecesMap = new HashMap<Long, Vertex<T>>();
	}
	
	public void addEdge(long id1, long id2){
		addEdge(id1, id2, 0);
	}
	
	public void addEdge(long id1, long id2, int weight){
		Vertex<T> v1 = vertecesMap.get(id1);
		Vertex<T> v2 = vertecesMap.get(id2);
		
		if(v1 == null){
			v1 = new Vertex<T>(id1);
			vertecesMap.put(id1, v1);
		}
		if(v2 == null){
			v2 = new Vertex<T>(id2);
			vertecesMap.put(id2,v2);
		}
		
		Edge<T> edge = new Edge<T>(v1, v2, weight, isDirected);
		allEdges.add(edge);
		v1.addAdjcentVertex(edge, v2);
		if(!isDirected)
			v2.addAdjcentVertex(edge, v1);
	}
	
	public Vertex<T> addVertex(long id)
	{
		Vertex<T> v = vertecesMap.get(id);
		if(v == null){
			v = new Vertex<T>(id);
			vertecesMap.put(id, v);
		}
		return v;
	}
	
	public boolean isDirected() {
		return isDirected;
	}

	public void setDirected(boolean isDirected) {
		this.isDirected = isDirected;
	}

	public List<Edge<T>> getAllEdges() {
		return allEdges;
	}

	public Collection<Vertex<T>> getAllVerteces(){
		return vertecesMap.values();
	}
	
	public Vertex<T> getVertex(long id)
	{
		return vertecesMap.get(id);
	}
	public void setDataForVertex(long id, T obj)
	{
		if(vertecesMap.containsKey(id))
			getVertex(id).setData(obj);
	}

}