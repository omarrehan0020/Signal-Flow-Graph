package application.guiShapes;

import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ArcText {
	private Group root;
	private int Gain;
	private double X;
	private double Y;
	Text text;

	public ArcText(Group root, int Gain, double X, double Y) {
		this.root = root;
		this.Gain = Gain;
		this.X = X;
		this.Y = Y;
		writetext();
	}

	private void writetext() {
		text = new Text(Gain+"");
		text.setFill(Color.BLACK);
		text.setStroke(Color.HOTPINK);
		text.setStrokeWidth(1.5);
		// Setting the text to be added.
		text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 15));
		text.setEffect(new DropShadow());
		// setting the position of the text
		text.setX(X);
		text.setY(Y);
		root.getChildren().add(text);
	}

	public void updataPosition(double newX, double newY) {
		text.setX(newX);
		text.setY(newY);
	}
}
