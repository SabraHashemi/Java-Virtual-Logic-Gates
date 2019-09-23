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
class NotGate extends Gate {

     
 NotGate thisg = this;
    
  
    
    
    NotGate() {
        
    }

    NotGate(boolean enabled) {
        super(enabled);
    }

    @Override
    void makeSkin() {
        svggate = new SVGPath();
        iconName = "not.gif";
     
        svggate.setContent("m 173.53724,51.806086 c 0,3.539161 -4.4518,6.411526 -9.93706,6.411526 -5.48526,0 -9.93707,-2.872365 -9.93707,-6.411526 0,-3.539163 4.45181,-6.411528 9.93707,-6.411528 5.48526,0 9.93706,2.872365 9.93706,6.411528 z m -116.760255,35.2634 0,-70.526799 94.330485,35.263399 -94.330485,35.2634 z m 116.760515,-35.2634 44.7171,0 m -206.194411,0 44.71709,0");
        svggate.setFill(new Color(1.0, 1.0, 1.0, 1.0));

        svggate.setStrokeWidth(4);
        svggate.setStroke(new Color(0.0, 0, 0, 1.0));


         Pin inPin1 = new Pin(14.2, 66, 12, new Color(1.0, 0.0, 1.0, 1.0),"inPin");

        Pin outPin = new Pin(219, 42, 12, new Color(1.0, 1.0, 0.0, 1.0),"outPin");
         
        getChildren().add(svggate);
        getChildren().add(inPin1);
        
        getChildren().add(outPin);
        inPins.add(inPin1);
      
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
        outPins.get(0).state.set(!(inPins.get(0).state.getValue()) );
    }

    public String toString() {
        return "NOT Gate";

    }
   
}
