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
    int column;
    
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
      //  thread.start();
}
    public void run(){
        while(true){
            System.out.println(this.X);
        }
    }

    
}
