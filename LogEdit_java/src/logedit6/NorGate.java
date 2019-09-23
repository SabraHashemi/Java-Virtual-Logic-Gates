/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logedit6;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
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
class NorGate extends Gate {
    NorGate thisg = this;
    
    NorGate() {
    }
    NorGate(boolean enabled){super(enabled);}

     
    @Override
    void makeSkin() {
                iconName="nor.gif";
       svggate = new SVGPath();
        svggate.setContent("m 76.656198,0.97320741 c -1.5045,-3.1e-4 -3.0557,0.0142 -4.625,0.0312 -1.6198,0.0176 -3.2775,0.0588 -4.9687,0.0937 14.9942,37.0229696 14.6573,49.2700096 0.062,81.6562516 25.8888,1.11643 44.340802,-0.0297 58.250002,-5.28125 15.466,-5.83936 25.3142,-16.756042 33.5,-35.312502 -0.017,-0.0457 -0.03,-0.041 -0.031,-0.0312 C 139.4111,9.5772474 123.2983,0.98250741 76.655898,0.97315741 z M 14.5312,7.1607074 C 7.0709,7.1607074 1,13.200377 1,20.660707 c 0,7.46033 6.0709,13.5 13.5312,13.5 7.4604,0 13.5,-6.03967 13.5,-13.5 0,-7.46033 -6.0396,-13.4999996 -13.5,-13.4999996 z m 14.1641,12.9687496 44.737198,0 z m 189.8359,8.59375 c -7.3968,0 -13.3984,5.97064 -13.5,13.34375 -8e-4,0.0633 0,0.12405 0,0.1875 0,7.46033 6.0397,13.5 13.5,13.5 7.4604,0 13.5313,-6.03967 13.5313,-13.5 0,-7.46033 -6.0709,-13.53125 -13.5313,-13.53125 z m -52.8437,6.5625 c -3.3911,0 -6.125,2.91517 -6.125,6.5 0,3.58483 2.7339,6.46875 6.125,6.46875 3.3911,0 6.1562,-2.88392 6.1562,-6.46875 0,-3.58483 -2.7651,-6.5 -6.1562,-6.5 z m 6.7408,6.09375 32.1967,0 z M 15.0312,51.598207 c -7.4603,0 -13.5,6.03967 -13.5,13.5 0,7.460332 6.0397,13.531252 13.5,13.531252 7.4604,0 13.5313,-6.07092 13.5313,-13.531252 0,-7.46033 -6.0709,-13.5 -13.5313,-13.5 z m 14.0313,14.59375 44.196698,0 z");
        svggate.setFill(new Color(1.0, 1.0, 1.0, 1.0));

        svggate.setStrokeWidth(4);
        svggate.setStroke(new Color(0.0, 0, 0, 1.0));


        Pin inPin1 = new Pin(14.2, 21.5, 12.0, new Color(1.0, 0.0, 1.0, 1.0),"inPin");
        // inPin1,getBoundsInLocal()


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
               dragX =t.getX();
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
        outPins.get(0).state.set(!(inPins.get(0).state.getValue()|inPins.get(1).state.getValue()));
    }

       public String toString(){
        return "NOR Gate";
      
    }


  
}
