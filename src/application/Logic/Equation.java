package application.Logic;

import application.graph.Graph;

import java.util.ArrayList;

public class Equation {
    Graph graph;
    int src;
    int dest;
    private Equation(Graph graph, int src, int dest){
        this.graph = graph;
        this.src = src;
        this.dest = dest;
    }

    private forwardPaths.pair getPath(int i){
        forwardPaths temp = new forwardPaths();
        ArrayList<forwardPaths.pair> allPaths = temp.get_forward_paths(graph, src, dest);
        return allPaths.get(i);
    }
    private String calcNumerator(){
        forwardPaths paths = new forwardPaths();
        Delta deltaI = new Delta(graph);// Error Here***********************************************
        String numerator = "[" + String.valueOf(getPath(0).getCost()) + deltaI.getDeltaI(0) + "]";
        for (int i = 1 ; i < paths.size() ; i++){
            numerator += "[" + String.valueOf(getPath(i).getCost()) + deltaI.getDeltaI(i) + "]";
        }
        return numerator;
    }

    private String calcDenominator(){
        Delta delta = new Delta(graph);
        return delta.getDelta();
    }
    public String getFunction(){
        return "C/R = " + calcNumerator() + " / " + calcDenominator();
    }
}
