/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kareta;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author zuzanna
 */
public class Kareta extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       /* Button btn = new Button("To jest przycisk");
        btn.setText("Say 'Hello World'");
        btn.setId("przycisk");
        
        Image img = new Image("resources/images/card.png");
        ImageView imgView = new ImageView(img);
        Pane panel = new Pane(); //dla group nie działa -fx-background-color dlatego trzeba uzyc pane
        panel.getChildren().add(btn);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                panel.getChildren().add(imgView);
            }
        });
                
        */
        
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        Scene scene = new Scene(root, 1000, 500);
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
