package application.guiShapes;

import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleConnect {
	private Group root;
	private Circle c;
	private Circle connect;
	private double radius;
	private int NodeNum;
	private int Gain ;
	private ArcText at;
	public CircleConnect(Group root,Circle c,int NodeNum,int Gain) {
		this.root=root;
		this.c=c;
		this.NodeNum=NodeNum;
		this.Gain = Gain;
		drawConnect();
	}
	private void drawConnect() {
		setCalculations();
		connect = new Circle();
		connect.setCenterX(c.getCenterX());
		connect.setCenterY(c.getCenterY()-radius);
		connect.setRadius(radius);
		connect.setFill(null);
		connect.setStroke(Color.GOLD);
		connect.setStrokeWidth(4);
		connect.setEffect(new DropShadow());
		root.getChildren().add(2,connect);
		at = new ArcText(root, Gain, c.getCenterX()-10, c.getCenterY()-2*radius-15);
		c.centerXProperty().addListener(e -> {
			NodeNum++;
			setCalculations();
			connect.setCenterX(c.getCenterX());
			connect.setCenterY(c.getCenterY()-radius);
			connect.setRadius(radius);
			at.updataPosition(c.getCenterX()-10, c.getCenterY()-2*radius-15);
		});
	}
	private void setCalculations() {
		radius = 200 / (double)NodeNum;	
	}
}
