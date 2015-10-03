

package Solitaire;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    public int countOfCards;
      ArrayList<Integer> randomArray;
      ArrayList<Card> deck;
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
          randomArray=createArray();
         deck = new ArrayList<Card>();
       
        createDeck();
       
        
        
        //tworzenie kolumn z kartami
        
    
      createColumns();
        
        //tworzenie stosiku z resztą kart
        
        for(int z=countOfCards;z<52;z++){
           deck.get(randomArray.get(z)).back.setLayoutX(0);
               deck.get(randomArray.get(z)).back.setLayoutY(0);
                deck.get(randomArray.get(z)).startX=deck.get(randomArray.get(z)).back.getLayoutX();
                deck.get(randomArray.get(z)).startY=deck.get(randomArray.get(z)).back.getLayoutY();
              deck.get(randomArray.get(z)).stos=1;
                deck.get(randomArray.get(z)).back.setFitHeight(180);
                deck.get(randomArray.get(z)).back.setFitWidth(105);          
               gp.getChildren().add(deck.get(randomArray.get(z)).back);
               
                deck.get(randomArray.get(z)).createClick(deck,randomArray,gp,arrayOfColumns);
               
        }
        
       //obsługa kliknięcia na karte ze stosiku
        
      
      
      for (int y=0;y<7;y++){
          ArrayList<Card> kolumna = arrayOfColumns.get(y);
          for (int z=0; z<kolumna.size();z++){
              System.out.println("Kolumna: "+y+" Ele: "+ z + "Num karty: "+kolumna.get(z).number);
          }
      }

    }  
    
    
    @FXML
  
        
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
        
        public void createDeck(){
            int numberOfCard=2;
        int shapeOfCard=1;
        Boolean colorOfCard=false;
        int i;   
        
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
        }
        
        public void createColumns(){
           int width=0;
        countOfCards=0;
        for(int iter=0;iter<7;iter++){
            int height=200;
            
            for(int a=0;a<iter+1;a++){
                if(a==iter){
                     deck.get(randomArray.get(countOfCards)).face.setLayoutX(width);
               deck.get(randomArray.get(countOfCards)).face.setLayoutY(height);
                deck.get(randomArray.get(countOfCards)).startX=deck.get(randomArray.get(countOfCards)).face.getLayoutX();
                deck.get(randomArray.get(countOfCards)).startY=deck.get(randomArray.get(countOfCards)).face.getLayoutY();
                deck.get(randomArray.get(countOfCards) ).face.setFitHeight(180);
                deck.get(randomArray.get(countOfCards) ).face.setFitWidth(105);
               deck.get(randomArray.get(countOfCards)).child=deck.get(randomArray.get(countOfCards)).face;
                deck.get(randomArray.get(countOfCards)).createDrag(deck, randomArray,gp,arrayOfColumns);
                gp.getChildren().add(deck.get(randomArray.get(countOfCards)).child);
                arrayOfColumns.get(iter).add(0,deck.get(randomArray.get(countOfCards))); //to dodajemy na chama na 0 pozycje
                deck.get(randomArray.get(countOfCards)).column=iter;
                }else{
                deck.get(randomArray.get(countOfCards)).face.setLayoutX(width);
               deck.get(randomArray.get(countOfCards)).face.setLayoutY(height);
              deck.get(randomArray.get(countOfCards)).back.setLayoutX(width);
            deck.get(randomArray.get(countOfCards)).back.setLayoutY(height);
             
                deck.get(randomArray.get(countOfCards)).startX=deck.get(randomArray.get(countOfCards)).back.getLayoutX();
                deck.get(randomArray.get(countOfCards)).startY=deck.get(randomArray.get(countOfCards)).back.getLayoutY();
                deck.get(randomArray.get(countOfCards)).back.setFitHeight(180);
                deck.get(randomArray.get(countOfCards)).back.setFitWidth(105);
                deck.get(randomArray.get(countOfCards)).face.setFitHeight(180);
                deck.get(randomArray.get(countOfCards)).face.setFitWidth(105);
                deck.get(randomArray.get(countOfCards)).child=deck.get(randomArray.get(countOfCards)).face;
                gp.getChildren().add(deck.get(randomArray.get(countOfCards)).back); //to na samej górze jest na 1 pozycji bo dodawalismy od gory na dol
                gp.getChildren().add(deck.get(randomArray.get(countOfCards)).child);
                deck.get(randomArray.get(countOfCards)).child.setVisible(false);
                arrayOfColumns.get(iter).add(0,deck.get(randomArray.get(countOfCards)));
                deck.get(randomArray.get(countOfCards)).column=iter;
                }
                      height=height+25;
                      
                countOfCards++;
                
        }
            width=width+170;
        }
         
        }
    
}
