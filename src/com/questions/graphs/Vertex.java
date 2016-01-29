package com.questions.graphs;

public class Vertex {
	public static enum VertexState {
		WHITE("Unvisited"), GREY("Discovered"), BLACK("Explored");
		private VertexState(String state){
			this.state = state;
		}
		public String getState() {
			return state;
		}
		private final String state;
	}

	private VertexState state;
}
