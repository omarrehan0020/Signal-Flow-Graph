package application.guiShapes;

import java.io.File;
import java.util.ArrayList;

import application.graph.Graph;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class NodeShapes {

	private int ScreenWidth = 1200, NodeNum = 2;
	private double radius, distance;
	private Group root;
	private ArrayList<Circle> circles;
	private ArrayList<Arc> Edges;
	private ArrayList<Text> texts;
	private Graph graph;
	AudioClip popup;
	AudioClip ALERT;

	public NodeShapes(Group root, Graph graph) {
		this.root = root;
		this.graph = graph;
		popup = new AudioClip(new File("popup.wav").toURI().toString());
		ALERT = new AudioClip(new File("Error Alert.wav").toURI().toString());
		circles = new ArrayList<Circle>();
		Edges = new ArrayList<Arc>();
		texts = new ArrayList<Text>();
		initialize();
	}

	private void SetNodeShapes() {
		Circle c;
		for (int i = 0; i < NodeNum; i++) {
			graph.addNewBasicNode(i);
			c = new Circle(i * distance + distance / 2, 350, radius);
			c.setEffect(new DropShadow());
			addActions(c);
			if (i == 0)
				c.setFill(Color.GREEN);
			else if (i == NodeNum - 1)
				c.setFill(Color.RED);
			else
				c.setFill(Color.BLACK);
			circles.add(c);
			root.getChildren().add(c);
			AddText(c, i);
		}
	}

	private void initialize() {
		setCalculations();
		SetNodeShapes();
	}

	public void AddNode() {
		NodeNum++;
		setCalculations();
		Circle c = null;
		int i;
		for (i = 0; i < circles.size(); i++) {
			c = circles.get(i);
			c.setCenterX(i * distance + distance / 2);
			c.setRadius(radius);
		}
		if (i != 0)
			c.setFill(Color.BLACK);
		c = new Circle(i * distance + distance / 2, 350, radius);
		c.setEffect(new DropShadow());
		addActions(c);
		c.setFill(Color.RED);
		circles.add(c);
		root.getChildren().add(c);
		AddText(c, i);
		modifyEdges();
		modifyText();
	}

	public void ClearData() {
		circles = new ArrayList<Circle>();
		Edges = new ArrayList<Arc>();
		texts = new ArrayList<Text>();
		initialize();
	}

	private void AddText(Circle circle, int id) {
		Text text = new Text(id + "");
		text.setFill(Color.BLACK);
		text.setStroke(Color.GOLD);
		text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 15));
		text.setStrokeWidth(1.5);
		text.setEffect(new DropShadow());
		text.setX(circle.getCenterX() - 10);
		text.setY(circle.getCenterY() + circle.getRadius() + 15);
		texts.add(text);
		// modifyText(circle, id);
		root.getChildren().add(text);
	}

	private void modifyText() {
		Text text;
		Circle circle;
		for (int i = 0; i < texts.size() - 1; i++) {
			text = texts.get(i);
			circle = circles.get(i);
			text.setX(circle.getCenterX() - 10);
			text.setY(circle.getCenterY() + circle.getRadius() + 15);
		}
	}

	private void setCalculations() {
		radius = 200 / (double) NodeNum;
		distance = ScreenWidth / (double) NodeNum;
	}

	private int Node1ID, Node2ID;
	private Circle circle1;
	private int Gain;
	private boolean AddEdge = false;
	private boolean firstClick = true;
	private Button AddEdgeButton;

	private void addActions(Circle c) {
		c.setOnMouseClicked(e -> {
			if (AddEdge) {
				if (firstClick) {
					popup.play();
					circle1 = c;
					firstClick = false;
					AddEdgeButton.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff");
				} else {
					Node1ID = circles.indexOf(circle1);
					Node2ID = circles.indexOf(c);
					if (graph.addNode(Node1ID, Node2ID, Gain)) {
						popup.play();
						if (Node1ID == Node2ID) {
							new CircleConnect(root, c, NodeNum, Gain);
						} else {
							Edges.add(new Arc(root, circle1, c, 4, Gain));
						}
					} else {
						ALERT.play();
					}
					AddEdgeButton.setStyle("");
					firstClick = true;
					AddEdge = false;
				}
			}
		});
	}

	private void modifyEdges() {
		// double w = 10 / (double) NodeNum ;
		for (Arc arc : Edges) {
			arc.setControl();
			// arc.setWidth(1.5*w);
		}
	}

	public void AddEdge(int Gain, Button b) {
		AddEdge = true;
		b.setStyle("-fx-background-color: #00ff00");
		this.Gain = Gain;
		this.AddEdgeButton = b;
	}

	public ArrayList<Circle> getNodes() {
		return circles;
	}

}
