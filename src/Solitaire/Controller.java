

package Solitaire;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author zuzanna
 */
public class Controller implements Initializable {

    /**
     * Initializes the controller class.
     */

    public Label text;
    public Image image;
    public Pane gp;
    
    //tworzenie talii kart
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int numberOfCard=2;
        int shapeOfCard=1;
        Boolean colorOfCard=false;
        int i;
        ArrayList<Card> deck = new ArrayList<Card>();
        ArrayList<Integer> randomArray;
        for(i=0;i<52;i++){
            deck.add(new Card(numberOfCard,shapeOfCard,colorOfCard,i,i));
            numberOfCard++;
            if (numberOfCard==15){
                numberOfCard=2;
                shapeOfCard++;
                if (shapeOfCard==3){
                    colorOfCard=true;
                }
            }
        }
      
        //tworzenie kolumn z kartami
        
      randomArray=createArray();
      
        int width=0;
        int countOfCards=0;
        for(int iter=0;iter<7;iter++){
            int height=200;
            
            for(int a=0;a<iter+1;a++){
                if(a==iter){
                 deck.get(randomArray.get(countOfCards)).face.setTranslateX(width);
                deck.get(randomArray.get(countOfCards)).face.setTranslateY(height);
                deck.get(randomArray.get(countOfCards)).back.setTranslateX(width);
                deck.get(randomArray.get(countOfCards)).back.setTranslateY(height);
                deck.get(randomArray.get(countOfCards)).back.setVisible(false);
                deck.get(randomArray.get(countOfCards)).X=deck.get(randomArray.get(countOfCards)).face.getLayoutX();
                deck.get(randomArray.get(countOfCards)).Y=deck.get(randomArray.get(countOfCards)).face.getLayoutY();
                 deck.get(randomArray.get(countOfCards)).numberOfPane=iter;
                  deck.get(randomArray.get(countOfCards)).numberOfChild=a;
                  deck.get(randomArray.get(countOfCards)).id=countOfCards;
                deck.get(randomArray.get(countOfCards) ).face.setFitHeight(180);
                deck.get(randomArray.get(countOfCards) ).face.setFitWidth(105);   
                }else{
                deck.get(randomArray.get(countOfCards)).back.setTranslateX(width);
                deck.get(randomArray.get(countOfCards)).back.setTranslateY(height);
                 deck.get(randomArray.get(countOfCards)).face.setTranslateX(width);
                deck.get(randomArray.get(countOfCards)).face.setTranslateY(height);
                 deck.get(randomArray.get(countOfCards)).face.setVisible(false);
                deck.get(randomArray.get(countOfCards)).X=deck.get(randomArray.get(countOfCards)).back.getLayoutX();
                deck.get(randomArray.get(countOfCards)).Y=deck.get(randomArray.get(countOfCards)).back.getLayoutY();
                deck.get(randomArray.get(countOfCards)).numberOfPane=iter;
                  deck.get(randomArray.get(countOfCards)).numberOfChild=a;
                  deck.get(randomArray.get(countOfCards)).id=countOfCards;
                deck.get(randomArray.get(countOfCards)).back.setFitHeight(180);
                deck.get(randomArray.get(countOfCards)).back.setFitWidth(105);
                }
              
                    if(a==iter){
                gp.getChildren().add(deck.get(randomArray.get(countOfCards)).face);
                    }else{
                gp.getChildren().add(deck.get(randomArray.get(countOfCards)).back);  
                    }
            
                 
                   
                      height=height+25;
                      
                countOfCards++;
                
        }
            width=width+170;
        }
        
        //tworzenie stosiku z resztą kart
        
        for(int z=countOfCards;z<52;z++){
            deck.get(randomArray.get(z)).back.setTranslateX(0);
                deck.get(randomArray.get(z)).back.setTranslateY(0);
                deck.get(randomArray.get(z)).X=deck.get(randomArray.get(z)).back.getLayoutX();
                deck.get(randomArray.get(z)).Y=deck.get(randomArray.get(z)).back.getLayoutY();
             
                deck.get(randomArray.get(z)).back.setFitHeight(180);
                deck.get(randomArray.get(z)).back.setFitWidth(105);
                gp.getChildren().add(deck.get(randomArray.get(z)).back);
        }
        
       //obsługa kliknięcia na karte ze stosiku
        
        for(int r=24;r<52;r++){
            final int t=r;
            gp.getChildren().get(t).setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                     deck.get(randomArray.get(t)).face.setTranslateX(140);
                deck.get(randomArray.get(t)).face.setTranslateY(0);
                deck.get(randomArray.get(t)).face.setFitHeight(180);
                deck.get(randomArray.get(t)).face.setFitWidth(105);
                   deck.get(randomArray.get(t)).X=deck.get(randomArray.get(t)).face.getLayoutX();
                deck.get(randomArray.get(t)).Y=deck.get(randomArray.get(t)).face.getLayoutY();
                 gp.getChildren().add(deck.get(randomArray.get(t)).face);
                 deck.get(randomArray.get(t)).back.setVisible(false);
                 createDrag(gp.getChildren().size()-1,deck,randomArray);
                }
            
                    });
        }
        
     //obsługa przesuwania kart
        
       int z=0;
        
       while(z<52){
           final int y=z;
           createDrag(y,deck,randomArray);
        z++;
      }
    }   
    
    @FXML
       public void createDrag(int i,ArrayList<Card> deck, ArrayList<Integer> randomArray){
            class Delta { double x, y; }
        final Delta dragDelta = new Delta();
    gp.getChildren().get(i).setOnMousePressed(new EventHandler<MouseEvent>() {
  @Override public void handle(MouseEvent mouseEvent) {
    // record a delta distance for the drag and drop operation.
     dragDelta.x = gp.getChildren().get(i).getLayoutX() - mouseEvent.getSceneX();
    dragDelta.y = gp.getChildren().get(i).getLayoutY() - mouseEvent.getSceneY();
    gp.getChildren().get(i).setCursor(Cursor.MOVE);
     gp.getChildren().get(i).setTranslateZ(24);
  }
});
     gp.getChildren().get(i).setOnMouseReleased(new EventHandler<MouseEvent>() {
  @Override public void handle(MouseEvent mouseEvent) {
      gp.getChildren().get(i).setLayoutX(deck.get(randomArray.get(i)).X);
       gp.getChildren().get(i).setLayoutY(deck.get(randomArray.get(i)).Y);
       System.out.println(deck.get(randomArray.get(i)).X);
        System.out.println(deck.get(randomArray.get(i)).Y);
     gp.getChildren().get(i).setCursor(Cursor.HAND);
     System.out.print(randomArray.get(i));
  }
});
     gp.getChildren().get(i).setOnMouseDragged(new EventHandler<MouseEvent>() {
  @Override public void handle(MouseEvent mouseEvent) {
       gp.getChildren().get(i).setTranslateZ(24);
     gp.getChildren().get(i).setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
     gp.getChildren().get(i).setLayoutY(mouseEvent.getSceneY() + dragDelta.y);
    
  }
});
     gp.getChildren().get(i).setOnMouseEntered(new EventHandler<MouseEvent>() {
  @Override public void handle(MouseEvent mouseEvent) {
     gp.getChildren().get(i).setCursor(Cursor.HAND);
  }
});
       }
        
     //tworzenie tablicy z randomowymi elementami   
        
        public ArrayList createArray(){
            ArrayList<Integer> randomArray;
        randomArray = new ArrayList<Integer>();
            for(int i=0;i<52;i++){
                randomArray.add(i);
            }
            Collections.shuffle(randomArray);
            return randomArray;
        }
    
}
