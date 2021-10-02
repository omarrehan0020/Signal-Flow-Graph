package application.graph;

import java.util.ArrayList;

public class BasicNode {
	private ArrayList<Node> nodes ;
	private int id;
	
	public BasicNode(int id) {
		nodes = new ArrayList<Node>();
		this.id = id;
	}
	
	public void AddnewNode(Node n) {
		nodes.add(n);
	}
	public Node getNode(int NodeID) {
		for (Node n : nodes) {
			if (n.getId()==NodeID) {
				return n;
			}
		}
		return null;
	}
	
	public boolean isContain(int NodeID) {
		for (Node n: nodes) {
			if(n.getId()==NodeID) {
				return true;
			}
		}
		return false;
	}
	public ArrayList<Node> getNodes() {
		return nodes;
	}
	
	public int getId() {
		return id;
	}
}
