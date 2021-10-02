package application.graph;

import java.util.ArrayList;

public class Graph {
	private ArrayList<BasicNode> graph;
	public Graph() {
		graph = new ArrayList<BasicNode>();
	}
	public void addNewBasicNode(int id) {
		graph.add(new BasicNode(id));
	}
	public boolean addNode(int BasicNodeID , int NodeID ,int gain) {
		BasicNode bn = graph.get(BasicNodeID);
		if (bn.isContain(NodeID))
			return false;
		bn.AddnewNode(new Node(NodeID,gain));
		return true;
	}
	public int size(){
		return graph.size();
	}
	public int getGain (int BasicNodeID,int NodeID) {
		return graph.get(BasicNodeID).getNode(NodeID).getGain();
	}
	public BasicNode getBasicNode(int id) {
		return graph.get(id);
	}
	public ArrayList<BasicNode> getGraph() {
		return graph;
	}
}
