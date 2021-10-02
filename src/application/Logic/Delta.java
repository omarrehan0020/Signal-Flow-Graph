package application.Logic;

import application.graph.Graph;

import java.util.ArrayList;

public class Delta {

	private Integer[][] loops;
	Graph graph;
	public Delta(Graph graph) {
		this.graph=graph;
		LoopsDetector detector = new LoopsDetector();
	    this.loops = detector.findLoops(graph);
	}

	private String sumOfLoops(ArrayList<ArrayList<String>> loops) {
		String sum = "(" + loops.get(0).toString();
		for (int i = 1; i < loops.size(); i++)
			sum += "+" + loops.get(i).toString();
		sum += ")";
		return sum;
	}

	private Integer[][] cleanNull(Integer[][] loops, int c) {
		int k = 0;
		Integer[][] result = new Integer[loops.length - c][loops[0].length];
		for (int i = 0; i < loops.length; i++) {
			if (loops[i][0] == -1) {
				continue;
			}
			for (int j = 0; j < loops[i].length; j++) {
				result[k][j] = loops[i][j];
			}
			k++;
		}
		return result;
	}

	public String getDelta() {
		return calculateDelta(loops);
	}

	private String calculateDelta(Integer[][] loops) {
		String delta = "1";
		int j = 0;
		int i = 1;
		NonTouchingLoop temp = new NonTouchingLoop(i);
		ArrayList<ArrayList<String>> nonTouchingLoops = temp.getNonTouchingLoops(loops);
		while (!nonTouchingLoops.isEmpty()) {
			if (j % 2 == 0)
				delta += "-" + sumOfLoops(nonTouchingLoops);
			else
				delta += "+" + sumOfLoops(nonTouchingLoops);
			j++;
			i++;
			NonTouchingLoop temp1 = new NonTouchingLoop(i);
			nonTouchingLoops = temp1.getNonTouchingLoops(loops);
		}
		return delta;

	}

	public String getDeltaI(int v) {

		forwardPaths temp = new forwardPaths();
		ArrayList<forwardPaths.pair> allPaths = temp.get_forward_paths(graph,0,7);

		String[] result = new String[allPaths.size()];
		Integer[][] tempLoop = loops.clone();
		for (forwardPaths.pair p : allPaths) {
			tempLoop = loops.clone();
			int c = 0;
			int k = 0;
			for (Integer u : p.getPath()) {
				for (int i = 0; i < tempLoop.length; i++) {
					for (int j = 0; j < tempLoop[i].length; j++) {
						if (tempLoop[i][j] == u) {
							tempLoop[i][0] = -1;
							c++;
							break;
						}
					}
				}
			}
			if (c == tempLoop.length) {
				result[k] = "1";
				k++;
				continue;
			}
			tempLoop = cleanNull(tempLoop, c);
			result[k] = calculateDelta(tempLoop);
			k++;

		}

		return result[v];
	}

	public static void main(String[] args) {
		Graph graph = new Graph();
		graph.addNewBasicNode(0);
		graph.addNewBasicNode(1);
		graph.addNewBasicNode(2);
		graph.addNewBasicNode(3);
		graph.addNewBasicNode(4);
		graph.addNewBasicNode(5);
		graph.addNewBasicNode(6);
		graph.addNewBasicNode(7);
		graph.addNode(0, 1, 2);
		graph.addNode(1, 6, 2);
		graph.addNode(2, 3, 3);
		graph.addNode(3, 4, 4);
		graph.addNode(4, 3, 5);
		graph.addNode(4, 2, 6);
		graph.addNode(4, 5, 7);
		graph.addNode(5, 3, 8);
		graph.addNode(5, 6, 9);
		graph.addNode(6, 7, 7);
		Delta test = new Delta(graph);
		System.out.println(test.getDeltaI(0));
	}

}
