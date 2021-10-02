package application.guiShapes;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.QuadCurve;

public class Arrow {
	private Polygon shape = new Polygon();
	public Arrow(QuadCurve curve) {
		double p1x = -8;
		double p1y = 0;
		double p2x = -25;
		double p2y = -7;
		double p3x = -25;
		double p3y = 7;
		Double[] arrowShape = new Double[] { p1x, p1y, p2x, p2y, p3x, p3y };
		shape.getPoints().addAll(arrowShape);
		shape.setFill(Color.BLACK);
		shape.setStroke(Color.GOLD);
		shape.setStrokeWidth(1.5);
		shape.setEffect(new DropShadow());
		updateRotate(curve);
	}

	// 03_METHODS
//*************************************************************
	public Polygon getShape() {
		return shape;
	}

//*************************************************************
	public void updateRotate(QuadCurve curve) {
		updateTranslate(curve);
		double deltaY = curve.getEndY() - curve.getStartY();
		double deltaX = curve.getEndX() - curve.getStartX();
		double angle = Math.atan2(deltaY, deltaX) * (180 / Math.PI);
		shape.setRotate(angle);
	}

//*************************************************************
	private void updateTranslate(QuadCurve curve) {
		shape.setTranslateX((curve.getBoundsInLocal().getMinX()+curve.getBoundsInLocal().getMaxX())/2+15);
		shape.setTranslateY(curve.getBoundsInLocal().getMinY()+curve.getBoundsInLocal().getMaxY()-350);
	}

}