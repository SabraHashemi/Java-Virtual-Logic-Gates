/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logedit6;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;

/**
 *
 * @author admin
 */
class NandGate extends Gate {

    
 NandGate thisg = this;
    
  
    
    
    NandGate() {
        
    }

    NandGate(boolean enabled) {
        super(enabled);
    }

    @Override
    void makeSkin() {
        svggate = new SVGPath();
        iconName = "and.gif";
     
        svggate.setContent("m 120.72122,80.436134 c -25.425211,0 -67.318695,4e-6 -67.318695,4e-6 l 0,-69.378168 67.318695,-0.0039 c 25.42518,0 46.06014,15.541559 46.06014,34.690987 0,19.149429 -20.63496,34.690989 -46.06014,34.690989 z m 60.2325,-34.690982 c 0,2.946064 -3.17462,5.337072 -7.08618,5.337072 -3.91158,0 -7.08618,-2.391008 -7.08618,-5.337072 0,-2.946068 3.1746,-5.337078 7.08618,-5.337078 3.91156,0 7.08618,2.39101 7.08618,5.337078 z m -0.001,-3e-6 31.88799,0 M 21.514728,28.399646 l 31.888011,0 m -31.888011,34.690982 31.888011,0");
        svggate.setFill(new Color(1.0, 1.0, 1.0, 1.0));

        svggate.setStrokeWidth(4);
        svggate.setStroke(new Color(0.0, 0, 0, 1.0));


        Pin inPin1 = new Pin(14.2, 21.5, 12.0, new Color(1.0, 0.0, 1.0, 1.0),"inPin");


        Pin inPin2 = new Pin(14.2, 66, 12, new Color(1.0, 0.0, 1.0, 1.0),"inPin");

        Pin outPin = new Pin(219, 42, 12, new Color(1.0, 1.0, 0.0, 1.0),"outPin");
         
        getChildren().add(svggate);
        getChildren().add(inPin1);
        getChildren().add(inPin2);
        getChildren().add(outPin);
        inPins.add(inPin1);
        inPins.add(inPin2);
        outPins.add(outPin);
        
       

    }

 
    @Override
    void addBehavior() {
    
    
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
               
                
                dragX=t.getX() ;
                dragY=t.getY();
               
                
                t.consume();
                

            }
        });
        
        
        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if(selected==true)
                {
                     
               dragAll(t);
                }
           else
                {
                    drag(t, thisg);
                }
               
        }
        });

    
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
               if( t.getClickCount()==2)
               {
                   if(selected)
                   {
                       selected= false;
                       svggate. setFill(new Color(1 ,1, 1, 1.0));
                       removeSelection(thisg);
                       
                   }
                   else if(!selected)
                   {
                name.setStyle(" -fx-effect: dropshadow( three-pass-box , rgba(0.5,0.5,0.6,0.6) , 10, 0.0 , 0 , 1 );");
                svggate. setFill(new Color(0.5, 0.4, 0.6, 1.0));
                selected=true;
                
                selection(thisg);
              LogicAppController.name.setText(thisg.toString());
                 LogicAppController.dragX.setText(thisg.dragX+ "");
                  LogicAppController.dragY.setText(thisg.dragY+ "");
               
                   }
               
               }
                t.consume();
                
                

            }
        });
        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                t.consume();

            }
        });
        
        
        

    }

    @Override
    void setStyle() {
    }

    @Override
    void logic() {
        outPins.get(0).state.set(!(inPins.get(0).state.getValue() & inPins.get(1).state.getValue()));
    }

    public String toString() {
        return "NAND Gate";

    }
   
  
}
