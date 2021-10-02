package application.guiShapes;

import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.QuadCurve;

public class Arc {
	private Group root;
	private Circle c1;
	private Circle c2;
	QuadCurve arc;
	private Arrow arrow;
	private double width;
	private int Gain;
	private ArcText at;

	int temp = 0;

	public Arc(Group root, Circle c1, Circle c2, double width, int Gain) {
		this.root = root;
		this.c1 = c1;
		this.c2 = c2;
		this.width = width;
		this.Gain = Gain;
		drawArc();
		drawArrow();
	}

	private void drawArc() {
		arc = new QuadCurve();

		arc.startXProperty().bind(c1.centerXProperty());
		arc.startYProperty().bind(c1.centerYProperty());
		arc.endXProperty().bind(c2.centerXProperty());
		arc.endYProperty().bind(c2.centerYProperty());

		double X = (c1.getCenterX() + c2.getCenterX()) / 2;
		double Y = (c1.getCenterX() - c2.getCenterX()) / 2;
		arc.setControlX(X);
		arc.setControlY(350 + Y);

		arc.setStroke(Color.GOLD);
		arc.setStrokeWidth(width);
		arc.setFill(null);
		arc.setEffect(new DropShadow());
		
		root.getChildren().add(2, arc);
		double newX = (arc.getBoundsInLocal().getMinX() + arc.getBoundsInLocal().getMaxX()) / 2;
		double newY = arc.getBoundsInLocal().getMinY() + arc.getBoundsInLocal().getMaxY() - 350;

		if (Y > 0) {
			temp = 25;
		} else {
			temp = -15;
		}
		at = new ArcText(root, Gain, newX - 10, newY + temp);
	}

	public void setControl() {
		double X = (c1.getCenterX() + c2.getCenterX()) / 2;
		double Y = ((c1.getCenterX() - c2.getCenterX()) / 2) + 350;
		arc.setControlX(X);
		arc.setControlY(Y);
		double newX = (arc.getBoundsInLocal().getMinX() + arc.getBoundsInLocal().getMaxX()) / 2;
		double newY = arc.getBoundsInLocal().getMinY() + arc.getBoundsInLocal().getMaxY() - 350;
		at.updataPosition(newX - 10, newY + temp);
		arrow.updateRotate(arc);
	}

	private void drawArrow() {
		arrow = new Arrow(arc);
//detect change in circle for edge
		c1.centerXProperty().addListener(e -> {
			arrow.updateRotate(arc);
		});
		c1.centerYProperty().addListener(e -> {
			arrow.updateRotate(arc);
		});
		c2.centerXProperty().addListener(e -> {
			arrow.updateRotate(arc);
		});
		c2.centerYProperty().addListener(e -> {
			arrow.updateRotate(arc);
		});
		root.getChildren().add(arrow.getShape());

	}

	public void setWidth(double width) {
		arc.setStrokeWidth(width);
	}

}