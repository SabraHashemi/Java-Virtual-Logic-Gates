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
class BufferGate extends Gate {

  
 BufferGate thisg = this;
    
  
    
    
    BufferGate() {
        
    }

    BufferGate(boolean enabled) {
        super(enabled);
    }

    @Override
    void makeSkin() {
        svggate = new SVGPath();
        iconName = "buffer.gif";
  
        svggate.setContent("m 70.541523,8.8724762 0,86.6647038 C 103.48564,95.416821 136.79466,79.284713 138.30069,52.565462 137.99745,27.754219 106.30943,8.8724762 70.541523,8.8724762 z M 2.5708381,52.204827 l 64.9564269,0 -64.9564269,0 z m 138.4444419,0 64.93879,0 -64.93879,0 z");
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
//                name.setStyle(" -fx-effect: dropshadow( three-pass-box , rgba(0.5,0.5,0.6,0.6) , 10, 0.0 , 0 , 1 );");
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
        outPins.get(0).state.set(inPins.get(0).state.getValue() );
    }

    public String toString() {
        return "Buffer Gate";

    }
   
  
  
}
