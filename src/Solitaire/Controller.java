

package Solitaire;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author zuzanna
 */
public class Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    public ImageView imgView;
    public Label text;
    public Image image;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int numberOfCard=2;
        int shapeOfCard=1;
        Boolean colorOfCard=false;
        int i;
        ArrayList<Card> deck = new ArrayList<Card>();
        for(i=0;i<52;i++){
            deck.add(new Card(numberOfCard,shapeOfCard,colorOfCard,i));
            numberOfCard++;
            if (numberOfCard==15){
                numberOfCard=2;
                shapeOfCard++;
                if (shapeOfCard==3){
                    colorOfCard=true;
                }
            }
        }
        
         imgView.setImage(deck.get(1).face);  // wazne !! nie tworzymy nowego obiektu tylko nadajemy mu properties
         text.setText("blablabal");
         System.out.print("hahahaha");
    }   
    
    @FXML
        public void click(ActionEvent action){ //ale to działa
            text.setText("blablabla");
        }
    
}
