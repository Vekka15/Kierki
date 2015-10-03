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
    double X;
    double Y;
    public int column;
    Node child;
    int stos;
    Card(int num, int shap,Boolean col,int i,int id){
        this.number=num;
        this.shape=shap;
        this.color=col;
        this.face= new ImageView(new Image("/resources/images/"+i+".png"));
        this.back = new ImageView(new Image("/resources/images/cardback.png"));
        this.X=0;
        this.Y=0;
        this.column=7;
        this.stos=0;
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
      //    System.out.println("weszlo");
          //TODO
          // Zrobić żeby się dokładało kilka kart na raz jezeli przenosimy ich kilka
     for(int u=0;u<7;u++){
        ArrayList<Card> columnTmp = arrayOfColumns.get(u);
         if((columnTmp.get(0).face.getLayoutX()<=child.getLayoutX())&&(columnTmp.get(0).face.getLayoutX()+105>=child.getLayoutX())&&(columnTmp.get(0).face.getLayoutY()<=child.getLayoutY())&&(columnTmp.get(0).face.getLayoutY()+180>=child.getLayoutY())){
            if((u!=column)||(column==7)) {
                if((columnTmp.get(0).number-1==number)&&(columnTmp.get(0).color!=color)) {
                                     
                    k=1;     
                    //usuniecie elementu ze starej kolumny i dołączenie jej do nowej
                    // myColumn - stara kolumna
                    // columnTmp - nowa kolumna
                   if(column!=7){
                    //    System.out.println("weszlo");
                            int numberOfExColumn= column;
                      ArrayList<Card> myColumn = arrayOfColumns.get(numberOfExColumn);
                       int ind = myColumn.indexOf(Card.this);           
                 int y=25;
                 for(int p=ind;p>=0;p--){ 
                     myColumn.get(p).child.setLayoutX(columnTmp.get(0).face.getLayoutX());
                    myColumn.get(p).child.setLayoutY(columnTmp.get(0).face.getLayoutY()+y);
                    
                    // po prostu zeby element po przesunieciu zawsze był na wierzchu
                        gp.getChildren().remove(myColumn.get(p).child);
                        gp.getChildren().add(myColumn.get(p).child);
                        Card tmp = myColumn.get(p);
                     columnTmp.add(0, tmp); 
                   
                 }
                 for(int p=ind;p>=0;p--){ 
                       Card tmp = myColumn.get(p);
                       myColumn.remove(p);
                 }
                  column=u;
                   gp.getChildren().remove(myColumn.get(0).back);
                 myColumn.get(0).child.setVisible(true);
                         myColumn.get(0).createDrag( deck, randomArray,gp,arrayOfColumns);
                   }else{
                   
                       face.setLayoutX(columnTmp.get(0).child.getLayoutX());
                       face.setLayoutY(columnTmp.get(0).child.getLayoutY()+25);
                        gp.getChildren().remove(child);
                        gp.getChildren().add(child);
                         columnTmp.add(0,Card.this); 
                       stos=0;
                      column=u;
                   }
                     // System.out.println(myColumn.size());
                      
                        // wyswietlenie nowej karty w starej kolumnie                              
                      
                     
//                          System.out.println("Zabierająca!!!");
//                    for (Card myColumn1 : myColumn) {
//                        System.out.println("numer karty:"+ myColumn1.number);
//                        
//                    }
//                    System.out.println("Dokładajaca!!!");
//                    for (Card column1 : columnTmp) {
//                        System.out.println("numer karty:"+ column1.number);
//                        
//                    }
                    
                
                    break;
                    
                }
            }
         }
     }
     if (k==0){
         if(column!=7){
          ArrayList<Card> myColumn = arrayOfColumns.get(column);
         int ind = myColumn.indexOf(Card.this);
            if (ind>0){
                 int y=25*ind;
                 for(int p=0;p<=ind;p++){
                      myColumn.get(p).child.setLayoutX(startX);
                     myColumn.get(p).child.setLayoutY(startY+y);
                     y=y-25;
                 }
            }else{
         child.setLayoutX(startX);
       child.setLayoutY(startY);
            }
         }else{
             child.setLayoutX(140);
       child.setLayoutY(0);
         }
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
   
    if(column!=7){
         ArrayList<Card> myColumn = arrayOfColumns.get(column);
    int ind = myColumn.indexOf(Card.this);
    if (ind>0){
        int y=25*ind;
        for(int p=0;p<=ind;p++){
             myColumn.get(p).child.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
            myColumn.get(p).child.setLayoutY(mouseEvent.getSceneY() + dragDelta.y + y); //moze nalezałoby zmieniac X i Y karty, czy warto tez moze ustawiac w której kolumnie jest karta
            y=y-25;
        }
    }
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
    
    public void createClick(ArrayList<Card> deck, ArrayList<Integer> randomArray,Pane gp, ArrayList<ArrayList> arrayOfColumns){
        back.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                     child=face;
            
                gp.getChildren().add(child);
               child.setLayoutX(140);     
               child.setLayoutY(0);
                face.setFitHeight(180);
                face.setFitWidth(105);
                   startX=face.getLayoutX();
                startY=face.getLayoutY();
                child.setVisible(true);
                Card.this.createDrag(deck, randomArray,gp,arrayOfColumns);
                back.setVisible(false);
                }
            
                    });
    }

    
}
