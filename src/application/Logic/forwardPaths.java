package application.Logic;

import java.util.ArrayList;

import application.graph.Graph;
import application.graph.Node;

public class forwardPaths {
	private ArrayList<pair> forward_paths = new ArrayList<>();
	private boolean vis[] = new boolean[1000];

	private void find_all_forward_paths(Graph g, int u, int dest, int cost, ArrayList<Integer> psf) {
		if (u == dest) {
			psf.add(u);
			ArrayList<Integer> tmp = (ArrayList<Integer>) psf.clone();
			forward_paths.add(new pair(tmp, cost));
			return;
		}
		vis[u] = true;
		psf.add(u);
		for (Node v : g.getBasicNode(u).getNodes()) {
			if (!vis[v.getId()])
				find_all_forward_paths(g, v.getId(), dest, cost * v.getGain(), psf);
		}
		vis[u] = false;
		psf.remove(psf.size() - 1);
		return;
	}

	public static class pair {
		private ArrayList<Integer> path;
		private int cost;

		public pair() {

		}

		public int getCost() {
			return cost;
		}

		pair(ArrayList<Integer> path, int cost) {
			this.path = path;
			this.cost = cost;
		}

		public ArrayList<Integer> getPath() {
			return path;
		}
	}

	public int size(){
		return forward_paths.size();
	}

	public ArrayList<pair> get_forward_paths(Graph g, int src, int dest) {
		find_all_forward_paths(g, src, dest, 1, new ArrayList<Integer>());
		return forward_paths;
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

		graph.addNode(0, 1, 1);
		graph.addNode(1, 2, 2);
		graph.addNode(2, 3, 3);
		graph.addNode(3, 4, 4);
		graph.addNode(4, 5, 6);
		graph.addNode(5, 6, 6);
		graph.addNode(2, 5, 6);

		forwardPaths solver = new forwardPaths();
		ArrayList<pair> res = solver.get_forward_paths(graph, 0, 6);

		for (pair p : res) {
			for (Integer u : p.path) {
				System.out.print(u + " ");
			}
			System.out.println("  Cost = " + p.cost);
		}
		/*Delta delta = new Delta();
		DeltaI deltaI = new DeltaI();

		for (pair p : res) {
			Graph newGraph = deltaI.deleteNodes(p, graph);
		}
		System.out.println();*/

	}
}
