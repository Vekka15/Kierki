/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solitaire;


import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author zuzanna
 */
public class Card  extends Thread{
    int number;
    int shape;
    Boolean color;
    ImageView face;
    ImageView back;
    double startX;
    double startY;
    Thread thread;
    double X;
    double Y;
    Boolean uncovered;
    public int column;
    int id;
    Node child;
    Card(int num, int shap,Boolean col,int i,int id){
        this.number=num;
        this.shape=shap;
        this.color=col;
        this.uncovered=false;
        this.face= new ImageView(new Image("/resources/images/"+i+".png"));
        this.back = new ImageView(new Image("/resources/images/cardback.png"));
        thread=new Thread(this);
        this.X=0;
        this.Y=0;
        this.column=7;
    // child=face;

            
        
}
   
    
     public void createDrag(ArrayList<Card> deck, ArrayList<Integer> randomArray,Pane gp, ArrayList<ArrayList> arrayOfColumns){
            class Delta { double x, y; }
        final Delta dragDelta = new Delta();
    this.child.setOnMousePressed(new EventHandler<MouseEvent>() {
  @Override public void handle(MouseEvent mouseEvent) {
    // record a delta distance for the drag and drop operation.
     dragDelta.x = child.getLayoutX() - mouseEvent.getSceneX();
    dragDelta.y = child.getLayoutY() - mouseEvent.getSceneY();
    child.setCursor(Cursor.MOVE);
 
  }
});
    

     child.setOnMouseReleased(new EventHandler<MouseEvent>() {
  @Override
 public void handle(MouseEvent mouseEvent) {
          int k=0;
     // TEST
     for(int u=0;u<7;u++){
        ArrayList<Card> columnTmp = arrayOfColumns.get(u);
         if((columnTmp.get(0).face.getLayoutX()<=child.getLayoutX())&&(columnTmp.get(0).face.getLayoutX()+105>=child.getLayoutX())&&(columnTmp.get(0).face.getLayoutY()<=child.getLayoutY())&&(columnTmp.get(0).face.getLayoutY()+180>=child.getLayoutY())){
            if((u!=column)||(column==7)) {
                if((columnTmp.get(0).number-1==number)&&(columnTmp.get(0).color!=color)) {
                    
                    child.setLayoutX(columnTmp.get(0).face.getLayoutX());
                    child.setLayoutY(columnTmp.get(0).face.getLayoutY()+25);
                    
                    
                        gp.getChildren().remove(child);
                        gp.getChildren().add(child);
                    
                    child.setLayoutX(columnTmp.get(0).face.getLayoutX());
                    child.setLayoutY(columnTmp.get(0).face.getLayoutY()+25);
                    k=1;                  
                            int numberOfExColumn= column;
                      ArrayList<Card> myColumn = arrayOfColumns.get(numberOfExColumn);
                       myColumn.remove(0);
                       Card temp1 = myColumn.get(myColumn.size()-1);
                       myColumn.remove(myColumn.size()-1);
                       myColumn.add(0,temp1);
                   //   myColumn.set(0, myColumn.get(myColumn.size()-1));
                      System.out.println(myColumn.size());
                     columnTmp.add(0, Card.this);  
                     Card temp = columnTmp.get(1);
                     columnTmp.remove(1);
                     columnTmp.add(columnTmp.size()-1, temp);
                    
                    // gp.getChildren().remove(myColumn.get(0).child);
                   //  myColumn.get(0).child=myColumn.get(0).face;
                      gp.getChildren().remove(myColumn.get(0).back);
                     myColumn.get(0).child.setVisible(true);
                         myColumn.get(0).createDrag( deck, randomArray,gp,arrayOfColumns);
                          System.out.println("Zabierająca!!!");
                    for (Card myColumn1 : myColumn) {
                        System.out.println("numer karty:"+ myColumn1.number);
                        
                    }
                    System.out.println("Dokładajaca!!!");
                    for (Card column1 : columnTmp) {
                        System.out.println("numer karty:"+ column1.number);
                        
                    }
                    break;
                    
                }
            }
         }
     }
     if (k==0){
         child.setLayoutX(startX);
       child.setLayoutY(startY);
       System.out.println("kurde nie dziala");
     }
    child.setCursor(Cursor.HAND);
  }
});
     child.setOnMouseDragged(new EventHandler<MouseEvent>() {
  @Override public void handle(MouseEvent mouseEvent) {
     child.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
     child.setLayoutY(mouseEvent.getSceneY() + dragDelta.y); //moze nalezałoby zmieniac X i Y karty, czy warto tez moze ustawiac w której kolumnie jest karta
     deck.get(randomArray.get(number)).X=(mouseEvent.getX() + dragDelta.x);
    deck.get(randomArray.get(number)).Y=(mouseEvent.getY() + dragDelta.y);
     if (child.getLayoutX()==100){
         System.out.println("wykryło");
         
     }
  
  }
});
     child.setOnMouseEntered(new EventHandler<MouseEvent>() {
  @Override public void handle(MouseEvent mouseEvent) {
     child.setCursor(Cursor.HAND);
  }
});
       }

    private static class arrayOfColumns {

        public arrayOfColumns() {
        }
    }

    
}
