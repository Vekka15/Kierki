/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solitaire;

import javafx.scene.image.Image;

/**
 *
 * @author zuzanna
 */
public class Card {
    int number;
    int shape;
    Boolean color;
    Boolean visible;
    Image face;
    Card(int num, int shap,Boolean col,int i){
        this.number=num;
        this.shape=shap;
        this.color=col;
        this.visible=false;
        this.face=new Image("/resources/images/"+"0"+".png");
}

    /*Card(int numberOfCard, int shapeOfCard, Boolean colorOfCard, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
