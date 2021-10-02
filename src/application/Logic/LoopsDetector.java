package application.Logic;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import application.graph.*;

public class LoopsDetector {

	private List<Integer[]> loopAsList;
	public List<Integer[]> loopAsList() {
		return loopAsList;
	}
	
	public Integer[][] findLoops(Graph graph) {
		List<Integer[]> loops = new LinkedList<Integer[]>();
		Stack<Integer> visited = new Stack<Integer>();

		for (BasicNode Bnode : graph.getGraph()) {
			for(Node node : Bnode.getNodes()) {
				visited.clear();
				visited.add(Bnode.getId());
				traverse(node, Bnode.getId(), visited, graph, loops);
			}
		}
		loopAsList = loops;
		return loops.toArray(new Integer[loops.size()][]);
	}
	
	public int loopGain(Integer[] loop, Graph graph) {
		int i, gain = 1;
		for(i=0; i<loop.length-1; i++) {
			gain *= graph.getGain(loop[i], loop[i+1]);
		}
		gain *= graph.getGain(loop[i], loop[0]);
		return gain;
	}

	private void traverse(Node current, int startID, Stack<Integer> visited, Graph graph, List<Integer[]> loops)  {

		if(current.getId() == startID) {
			if(!isExist(visited, loops))
				loops.add(visited.toArray(new Integer[visited.size()]));
			return;
		}
		if(visited.contains(current.getId())) return;

		BasicNode currentBasicNode = graph.getGraph().get(current.getId());
		if(currentBasicNode.getNodes().size() == 0) return;

		visited.add(current.getId());
		for(Node node : currentBasicNode.getNodes()) {
			traverse(node, startID, visited, graph, loops);
		}
		visited.pop();
	}

	private boolean isExist(List<Integer> path, List<Integer[]> loop) {
		for(Integer[] oldPath : loop)
		{
			if(path.size() == oldPath.length) {
				List<Integer> checkList = new LinkedList<Integer>();

				for(Integer num : oldPath) checkList.add(num);
				for(Integer num : path) checkList.remove(Integer.valueOf(num));
				if(checkList.isEmpty()) return true;
			}
		}
		return false;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		LoopsDetector m = new LoopsDetector();

		Graph graph = m.makeGraph();
		m.findLoops(graph);
	}
	private Graph makeGraph() {
		Graph graph = new Graph();

		graph.addNewBasicNode(0);
		graph.addNewBasicNode(1);
		graph.addNewBasicNode(2);
		graph.addNewBasicNode(3);
		graph.addNewBasicNode(4);
		graph.addNewBasicNode(5);
		graph.addNewBasicNode(6);
		graph.addNewBasicNode(7);
		
		/*graph.addNode(0, 1, -1);
		graph.addNode(1, 6, -1);
		graph.addNode(2, 3, -1);
		graph.addNode(3, 4, -1);
		graph.addNode(4, 3, -1);
		graph.addNode(4, 2, -1);
		graph.addNode(4, 5, -1);
		graph.addNode(5, 3, -1);
		graph.addNode(5, 6, -1);
		graph.addNode(6, 7, -1);*/
		
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

		return graph;
	}
}
