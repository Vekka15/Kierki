

package Solitaire;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
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
    public ArrayList<Card> column1;
    public ArrayList<Card> column2;
    public ArrayList<Card> column3;
    public ArrayList<Card> column4;
    public ArrayList<Card> column5;
    public ArrayList<Card> column6;
    public ArrayList<Card> column7;
    public ArrayList<ArrayList> arrayOfColumns;
    //tworzenie talii kart
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //kolumny w których znajdują się karty
        column1=new ArrayList<Card>();
        column2=new ArrayList<Card>();
        column3=new ArrayList<Card>();
        column4=new ArrayList<Card>();
        column5=new ArrayList<Card>();
        column6=new ArrayList<Card>();
        column7=new ArrayList<Card>();
        arrayOfColumns=new ArrayList<ArrayList>();
        
        
        arrayOfColumns.add(column1);
        arrayOfColumns.add(column2);
        arrayOfColumns.add(column3);
        arrayOfColumns.add(column4);
        arrayOfColumns.add(column5);
        arrayOfColumns.add(column6);
        arrayOfColumns.add(column7);
        
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
                     deck.get(randomArray.get(countOfCards)).face.setLayoutX(width);
               deck.get(randomArray.get(countOfCards)).face.setLayoutY(height);
              deck.get(randomArray.get(countOfCards)).back.setLayoutX(width);
            deck.get(randomArray.get(countOfCards)).back.setLayoutY(height);
                deck.get(randomArray.get(countOfCards)).back.setVisible(false);
                deck.get(randomArray.get(countOfCards)).uncovered=true;
                deck.get(randomArray.get(countOfCards)).startX=deck.get(randomArray.get(countOfCards)).face.getLayoutX();
                deck.get(randomArray.get(countOfCards)).startY=deck.get(randomArray.get(countOfCards)).face.getLayoutY();
                deck.get(randomArray.get(countOfCards) ).face.setFitHeight(180);
                deck.get(randomArray.get(countOfCards) ).face.setFitWidth(105);
                gp.getChildren().add(deck.get(randomArray.get(countOfCards)).face);
                createDrag(countOfCards,deck,randomArray,countOfCards);
                arrayOfColumns.get(iter).add(0,deck.get(randomArray.get(countOfCards)));
                deck.get(randomArray.get(countOfCards)).column=iter;
                }else{
                deck.get(randomArray.get(countOfCards)).face.setLayoutX(width);
               deck.get(randomArray.get(countOfCards)).face.setLayoutY(height);
              deck.get(randomArray.get(countOfCards)).back.setLayoutX(width);
            deck.get(randomArray.get(countOfCards)).back.setLayoutY(height);
                 deck.get(randomArray.get(countOfCards)).face.setVisible(false);
                deck.get(randomArray.get(countOfCards)).startX=deck.get(randomArray.get(countOfCards)).back.getLayoutX();
                deck.get(randomArray.get(countOfCards)).startY=deck.get(randomArray.get(countOfCards)).back.getLayoutY();
                deck.get(randomArray.get(countOfCards)).back.setFitHeight(180);
                deck.get(randomArray.get(countOfCards)).back.setFitWidth(105);
                gp.getChildren().add(deck.get(randomArray.get(countOfCards)).back);
                arrayOfColumns.get(iter).add(deck.get(randomArray.get(countOfCards)));
                deck.get(randomArray.get(countOfCards)).column=iter;
                }
                      height=height+25;
                      
                countOfCards++;
                
        }
            width=width+170;
        }
        
        //tworzenie stosiku z resztą kart
        
        for(int z=countOfCards;z<52;z++){
           deck.get(randomArray.get(z)).back.setLayoutX(0);
               deck.get(randomArray.get(z)).back.setLayoutY(0);
                deck.get(randomArray.get(z)).startX=deck.get(randomArray.get(z)).back.getLayoutX();
                deck.get(randomArray.get(z)).startY=deck.get(randomArray.get(z)).back.getLayoutY();
             
                deck.get(randomArray.get(z)).back.setFitHeight(180);
                deck.get(randomArray.get(z)).back.setFitWidth(105);
               gp.getChildren().add(deck.get(randomArray.get(z)).back);
        }
        
       //obsługa kliknięcia na karte ze stosiku
        
      for(int r=28;r<52;r++){
            final int t=r;
            gp.getChildren().get(t).setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                deck.get(randomArray.get(t)).face.setLayoutX(140);     
               deck.get(randomArray.get(t)).face.setLayoutY(0);
                deck.get(randomArray.get(t)).face.setFitHeight(180);
                deck.get(randomArray.get(t)).face.setFitWidth(105);
                   deck.get(randomArray.get(t)).startX=deck.get(randomArray.get(t)).face.getLayoutX();
                deck.get(randomArray.get(t)).startY=deck.get(randomArray.get(t)).face.getLayoutY();
                 gp.getChildren().add(deck.get(randomArray.get(t)).face);
                 deck.get(randomArray.get(t)).back.setVisible(false);
                 System.out.println(gp.getChildren().size()-1);
                 createDrag(gp.getChildren().size()-1,deck,randomArray,t);
                }
            
                    });
        }

    }   
    
    @FXML
       public void createDrag(int i,ArrayList<Card> deck, ArrayList<Integer> randomArray,int number){
            class Delta { double x, y; }
        final Delta dragDelta = new Delta();
    gp.getChildren().get(i).setOnMousePressed(new EventHandler<MouseEvent>() {
  @Override public void handle(MouseEvent mouseEvent) {
    // record a delta distance for the drag and drop operation.
     dragDelta.x = gp.getChildren().get(i).getLayoutX() - mouseEvent.getSceneX();
    dragDelta.y = gp.getChildren().get(i).getLayoutY() - mouseEvent.getSceneY();
    gp.getChildren().get(i).setCursor(Cursor.MOVE);
 
  }
});
    

     gp.getChildren().get(i).setOnMouseReleased(new EventHandler<MouseEvent>() {
  @Override
 public void handle(MouseEvent mouseEvent) {
          int k=0;
     // TEST
     for(int u=0;u<7;u++){
        ArrayList<Card> column = arrayOfColumns.get(u);
         if((column.get(0).face.getLayoutX()<=gp.getChildren().get(i).getLayoutX())&&(column.get(0).face.getLayoutX()+105>=gp.getChildren().get(i).getLayoutX())&&(column.get(0).face.getLayoutY()<=gp.getChildren().get(i).getLayoutY())&&(column.get(0).face.getLayoutY()+180>=gp.getChildren().get(i).getLayoutY())&&((u!=deck.get(randomArray.get(number)).column)||(deck.get(randomArray.get(number)).column==7))){
            if((column.get(0).number-1==deck.get(randomArray.get(number)).number)&&(column.get(0).color!=deck.get(randomArray.get(number)).color)) {
             gp.getChildren().get(i).setLayoutX(column.get(0).face.getLayoutX());
             gp.getChildren().get(i).setLayoutY(column.get(0).face.getLayoutY()+25); 
             Node tmp = gp.getChildren().get(i);
             gp.getChildren().remove(i);
             gp.getChildren().add(tmp);
             gp.getChildren().get(gp.getChildren().size()-1).setLayoutX(column.get(0).face.getLayoutX());
             gp.getChildren().get(gp.getChildren().size()-1).setLayoutY(column.get(0).face.getLayoutY()+25); 
             createDrag(gp.getChildren().size()-1,deck,randomArray,i);
             System.out.println("weszło"+u);
             System.out.println(column.get(0).face.getLayoutX());
             System.out.println(gp.getChildren().get(i).getLayoutX());
             k=1;
             break;
             
            }
         }
     }
     if (k==0){
         gp.getChildren().get(i).setLayoutX(deck.get(randomArray.get(number)).startX);
       gp.getChildren().get(i).setLayoutY(deck.get(randomArray.get(number)).startY);
    
     }
    gp.getChildren().get(i).setCursor(Cursor.HAND);
  }
});
     gp.getChildren().get(i).setOnMouseDragged(new EventHandler<MouseEvent>() {
  @Override public void handle(MouseEvent mouseEvent) {
     gp.getChildren().get(i).setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
     gp.getChildren().get(i).setLayoutY(mouseEvent.getSceneY() + dragDelta.y); //moze nalezałoby zmieniac X i Y karty, czy warto tez moze ustawiac w której kolumnie jest karta
     deck.get(randomArray.get(number)).X=(mouseEvent.getX() + dragDelta.x);
    deck.get(randomArray.get(number)).Y=(mouseEvent.getY() + dragDelta.y);
     if (gp.getChildren().get(i).getLayoutX()==100){
         System.out.println("wykryło");
         
     }
  
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
