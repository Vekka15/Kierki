/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solitaire;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author zuzanna
 */
public class Card {
    int number;
    int shape;
    Boolean color;
    Boolean visible;
    ImageView face;
    ImageView back;
    double X;
    double Y;
    int numberOfPane;
    int numberOfChild;
    int id;
    
    Card(int num, int shap,Boolean col,int i,int id){
        this.number=num;
        this.shape=shap;
        this.color=col;
        this.visible=false;
        this.face= new ImageView(new Image("/resources/images/"+i+".png"));
        this.back = new ImageView(new Image("/resources/images/cardback.png"));
        this.id = id;
        
}

    
}
