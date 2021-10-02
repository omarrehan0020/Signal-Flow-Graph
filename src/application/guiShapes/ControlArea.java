package application.guiShapes;

import java.io.File;
import application.Main;
import application.graph.Graph;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class ControlArea {
	private Group root;
	private NodeShapes shapes;
	private Graph graph;
	private int id = 2;
	AudioClip ALERT;

	public ControlArea(Group root) {
		ALERT = new AudioClip(new File("Error Alert.wav").toURI().toString());
		this.root = root;
		this.graph = new Graph();
		this.shapes = new NodeShapes(root,graph);
		initialize();
	}

	private void initialize() {
		// Creating a Label
		Label labelNode = new Label("Nodes Number:");
		Label labelEdge = new Label("Gain:");
		// Setting font to the label
		Font font = Font.font("Brush Script MT", FontWeight.BOLD, FontPosture.REGULAR, 15);
		labelNode.setFont(font);
		// Filling color to the label
		labelNode.setTextFill(Color.WHITE);
		// Setting the position
		labelNode.setTranslateX(1220 + 5);
		labelNode.setTranslateY(30 + 7);
		TextField Nodes = new TextField("2");
		Nodes.setLayoutX(1220 + 80);
		Nodes.setLayoutY(30 + 5);
		Nodes.setMaxWidth(90);
		Nodes.setMinWidth(90);
		Nodes.setEditable(false);

		Button addNode = new Button("Add Node");
		addNode.setLayoutX(1220 + 180);
		addNode.setLayoutY(30 + 5);
		addNode.setMaxWidth(80);
		addNode.setMinWidth(80);
		addNode.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				shapes.AddNode();
				graph.addNewBasicNode(id);
				id++;
				Nodes.setText(Integer.toString(id));
			}
		});

		labelEdge.setFont(font);
		// Filling color to the label
		labelEdge.setTextFill(Color.WHITE);
		labelEdge.setTranslateX(1220 + 15);
		labelEdge.setTranslateY(30 + 37);

		TextField Gain = new TextField();
		Gain.setLayoutX(1220 + 80);
		Gain.setLayoutY(30 + 35);
		Gain.setMaxWidth(90);
		Gain.setMinWidth(90);

		Button addEdge = new Button("Add Edge");
		addEdge.setLayoutX(1220 + 180);
		addEdge.setLayoutY(30 + 35);
		addEdge.setMaxWidth(80);
		addEdge.setMinWidth(80);
		addEdge.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String GainText = Gain.getText();
				if (!GainText.equals("")) {
					try {
						shapes.AddEdge(Integer.parseInt(GainText), addEdge);
					}catch (Exception e) {
						ALERT.play();
					}
				} else {
					ALERT.play();
				}

			}
		});

		Button clear = new Button("Clear");
		clear.setLayoutX(1220 + 20);
		clear.setLayoutY(30 + 75);
		clear.setMaxWidth(230);
		clear.setMinWidth(230);
		clear.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff");
		clear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				root.getChildren().clear();
				Main.setBackground(root);
				new ControlArea(root);
			}
		});

		Button solve = new Button("Solve");
		solve.setLayoutX(1220 + 20);
		solve.setLayoutY(30 + 125);
		solve.setMaxWidth(230);
		solve.setMinWidth(230);
		solve.setStyle("-fx-background-color: #00ff00");

		TextArea textArea = new TextArea();
		textArea.setLayoutX(1220 + 5);
		textArea.setLayoutY(30 + 160);
		textArea.setMaxWidth(260);
		textArea.setMinWidth(260);
		textArea.setMaxHeight(450);
		textArea.setMinHeight(450);
		textArea.setEditable(false);

		root.getChildren().add(labelNode);
		root.getChildren().add(labelEdge);
		root.getChildren().add(Nodes);
		root.getChildren().add(Gain);
		root.getChildren().add(addNode);
		root.getChildren().add(addEdge);
		root.getChildren().add(clear);
		root.getChildren().add(solve);
		root.getChildren().add(textArea);

	}
}
