package game.gui;
	

import game.engine.Battle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;




public class GUIModel extends Application {

	public void start(Stage primaryStage) {
		
		VBox root = new VBox(10);
		Scene scene = new Scene(root,1280,720);
		BackgroundSize size = new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,true,true,true,true);
		Background background = new Background(new BackgroundImage(new Image("resources/S.JPEG"),BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.CENTER,size));
		Background instructionBackground =new Background(new BackgroundImage(new Image("resources/Ss.JPEG"),BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.CENTER,size));
		root.setBackground(background);
		//buttons
		Button start = new Button("Start Game");
		start.setPrefWidth(120);
		start.setPrefHeight(35);
		
		Button instruction =new Button("Instructions");
		instruction.setPrefWidth(120);
		instruction.setPrefHeight(35);
		
		Button exit = new Button("Exit");
		exit.setPrefWidth(120);
		exit.setPrefHeight(35);
		
		Button easy = new Button("Easy");
		easy.setPrefWidth(120);
		easy.setPrefHeight(35);
		
		Button hard = new Button("Hard");
		hard.setPrefWidth(120);
		hard.setPrefHeight(35);
		
		Button back = new Button("back");
		back.setPrefWidth(120);
		back.setPrefHeight(35);
		
		root.getChildren().addAll(start,instruction,exit);
		root.setAlignment(Pos.CENTER);
		
		//functionality
		start.setOnMouseClicked(Event -> {try {
			startFunction(root,start,instruction,exit,easy,hard,back);
		}catch(Exception e) {
			e.printStackTrace();
		}});
		
		instruction.setOnMouseClicked(Event ->{try {
			root.setBackground(instructionBackground);
			instructionFunction(root,start,instruction,exit,back);
		}catch(Exception e) {
			e.printStackTrace();
		}});
		
		exit.setOnMouseClicked(Event -> {try {
			primaryStage.close();
            Platform.exit();
		}catch(Exception e) {
			e.printStackTrace();
		}});
		
		easy.setOnMouseClicked(Event -> {try {
			Easy Easy = new Easy(primaryStage,scene);
			backFunction(root,start,instruction,exit,easy,hard,back);
			primaryStage.setScene(Easy.getScene());
		}catch(Exception e) {
			e.printStackTrace();
		}
		});
		
		hard.setOnMouseClicked(Event ->{try {
			Hard hardMode = new Hard(primaryStage,scene);
			backFunction(root,start,instruction,exit,easy,hard,back);
			primaryStage.setScene(hardMode.getScene());
		}catch(Exception e) {
			e.printStackTrace();
		}
		});
		
		back.setOnMouseClicked(Event ->{try{
			root.setBackground(background);
			backFunction(root,start,instruction,exit,easy,hard,back);
		}catch(Exception e) {
			e.printStackTrace();
		}});
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void startFunction(VBox root,Button b,Button b1,Button b2,Button b3,Button b4,Button b5) {
		root.getChildren().removeAll(b,b1,b2);
		root.getChildren().addAll(b3,b4,b5);
	}
	
	private void instructionFunction(VBox root,Button b,Button b1,Button b2,Button b3) {
		root.getChildren().removeAll(b,b1,b2);
		root.getChildren().add(b3);
		root.setAlignment(Pos.BASELINE_CENTER);
	}
	
	private void backFunction(VBox root,Button b,Button b1,Button b2,Button b3,Button b4,Button b5) {
		root.getChildren().addAll(b,b1,b2);
		root.getChildren().removeAll(b3,b4,b5);
		root.setAlignment(Pos.CENTER);
		b3.setPrefWidth(120);
		b3.setPrefHeight(35);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
