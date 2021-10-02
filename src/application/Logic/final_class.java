package application.Logic;

import java.util.Arrays;

import application.graph.Graph;

public class final_class {
	private static String required="";
	private forwardPaths forword;
	private static LoopsDetector loops=new LoopsDetector();
	private NonTouchingLoop nontouch;
	
	public static void solve(Graph t) {
		
		Integer[][] loop_to_string = loops.findLoops(t);
		int i=0;
		for(Integer[] list : loop_to_string) {
			required+="loop"+i+" :"+Arrays.deepToString(list)+"         gain:"+loops.loopGain(list, t)+"\n";
			i++;
		}
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
		final_class test=new final_class();
		test.solve(graph);
		System.out.println(required);
	}
}
