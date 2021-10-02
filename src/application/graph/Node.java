package application.graph;

public class Node {
	private int id;
	private int gain;
	public Node(int id ,int gain) {
		this.id = id;
		this.gain = gain;
	}
	public int getId() {
		return id;
	}
	public int getGain() {
		return gain;
	}

}
