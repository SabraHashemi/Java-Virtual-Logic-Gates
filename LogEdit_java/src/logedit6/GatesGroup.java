/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logedit6;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;



public class GatesGroup extends Group {
    
    double dragX=500;
    double dragY=1024;
     double fX;
     double fY;
    ObservableList<Pin> inPins;
    ObservableList<Pin> outPins;
    
    
 public  GatesGroup()
 {
      inPins = FXCollections.observableArrayList();
      outPins = FXCollections.observableArrayList();
      
     
    setOnMouseDragged(new EventHandler<MouseEvent> () {

        
         @Override
         public void handle(MouseEvent t) {
      
            
          /*   for(Node n : getChildren())
             {
                
                 System.out.println( getChildren().indexOf(n) + n.toString());
             }*/
             //  System.out.println("11111");
                 Point2D p = localToParent(t.getX(), t.getY());
                if (p.getX() - dragX < 0 || p.getY() -dragY< 0) {
                    t.consume();
                    return;
                }
                fX= p.getX()-dragX;
                fY= p.getY()-dragY;
                
               AnchorPane.setLeftAnchor(GatesGroup.this, p.getX() - dragX);
               AnchorPane.setTopAnchor(GatesGroup.this, p.getY() - dragY);
               
               
                
                t.consume();

            
         }
     });
    
 
   
    
 }
 
 public void add(Node node1, int i)
 {
     
     getChildren().add(i, node1);
     
     
     for(Node node :getChildren())
     {
          if(node instanceof Gate)
          {
         
         Gate tg = (Gate)node;
         
         if(tg.dragX<dragX )
             
         {
             dragX= tg.dragX;
         }
          if(tg.dragY<dragY)
          {
             dragY = tg.dragY;
          }
             
             
          }
         
         
     }
     fX=dragX;
     fY=dragY;
       GateDeck.gd.getChildren().remove(this);
      GateDeck.gd.getChildren().add(this);
    
     
     
    
    
     
   
   
 }
 
 public void getPins()
 {
     
    ObservableList<Pin> allinPins = FXCollections.observableArrayList();
    ObservableList<Pin> alloutPins= FXCollections.observableArrayList();
     boolean is=false;
     for(Gate gate :FullApp.sgates)
     {
       
         for(Pin in : gate.inPins)
         {
             allinPins.add(in);
         }
         for(Pin out : gate.outPins)
         {
             alloutPins.add(out);
         }
     }
      System.out.println("allinPins"+allinPins.size());
      System.out.println("alloutPins"+alloutPins.size());
      
     for(Pin in : allinPins)
     {
         is=false;
        for(Connector ct : FullApp.connectors)
        {
         if(ct.source.equals(in) || ct.dist.equals(in))
         {
             is=true;
             break;
         }
        }
        if(is==false)
        {
          inPins.add(in);  
        }
     }
   
     System.out.println("inPins"+inPins.size());
     for(Pin out : alloutPins)
     {
         is=false;
        for(Connector ct : FullApp.connectors)
        {
         if(ct.source.equals(out) || ct.dist.equals(out))
         {
             is=true;
             break;
         }
        }
        if(is==false)
        {
          outPins.add(out);  
        }
     }
       System.out.println("outPins"+outPins.size());
      
 }
   
}
