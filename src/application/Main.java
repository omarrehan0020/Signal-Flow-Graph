package application;

import java.io.File;

import application.guiShapes.ControlArea;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Main extends Application {

	public void start(Stage stage) {
		Group root = new Group();
		setBackground(root);
		new ControlArea(root);
		ScrollPane sp = new ScrollPane();
		sp.setContent(root);
		// Setting the stage
		Scene scene = new Scene(sp, 1500, 700);
		stage.setTitle("Signal Flow Graph");
		stage.setScene(scene);
		stage.show();
	}
	public static void setBackground(Group root) {
		
		Image background1 = new Image(new File("qafsha.jpg").toURI().toString());
		ImageView vm = new ImageView(background1);
		vm.setLayoutX(5);
		vm.setLayoutY(5);
		vm.setFitHeight(690);
		vm.setFitWidth(1190);
		  
        // create a image
		Image background2 = new Image(new File("backGround.jpg").toURI().toString());
		ImageView vm2 = new ImageView(background2);
		vm2.setLayoutX(1200);
		vm2.setFitHeight(700);
		vm2.setFitWidth(300);
		root.getChildren().addAll(vm);
		root.getChildren().addAll(vm2);
	}

	public static void main(String args[]) {
		launch(args);
	}
}
