/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solitaire;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author zuzanna
 */

public class Solitaire extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
      
        
        
        //ImageView imgView = new ImageView(deck.get(1).face);
        
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
       // StackPane root1= new StackPane();
        Scene scene = new Scene(root, 1000, 500);
       // root1.getChildren().add(imgView);
        scene.getStylesheets().add("resources/css/stylesheet.css");
        primaryStage.setTitle("Z wykorzystaniem FXML");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
