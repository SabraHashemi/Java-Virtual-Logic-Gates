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
class OrGate extends Gate {
 
   OrGate thisg = this;
    OrGate(){}

    OrGate(boolean enabled){super(enabled);}
 
    @Override
    void makeSkin() {
       
                iconName="or.gif";

         svggate = new SVGPath();
        svggate.setContent("m 76.3612,86.77097 c -3.1021,0.002 -6.3364,-0.0239 -9.7188,-0.0937 14.9942,-37.02297 14.6573,-49.27001 0.062,-81.65625 54.6754,-2.35782 76.2023,5.34878 91.75,40.59375 -0.017,0.0457 -0.03,0.0409 -0.031,0.0312 -19.4121,32.51828 -35.5319,41.09868 -82.0625,41.125 z M 14.0799,80.61472 c -7.4603,0 -13.5,-6.07092 -13.5,-13.53125 0,-7.46033 6.0397,-13.5 13.5,-13.5 7.4603,0 13.5,6.03967 13.5,13.5 0,7.46033 -6.0397,13.53125 -13.5,13.53125 z m 14.5625,-13 43.625,0 z m 189.4688,-8.59375 c -7.4604,0 -13.5,-6.03967 -13.5,-13.5 0,-7.46033 6.0396,-13.5 13.5,-13.5 7.4603,0 13.5,6.03967 13.5,13.5 0,7.46033 -6.0397,13.5 -13.5,13.5 z m -59.625,-13.03125 45.625,0 z m -143.875,-9.8125 c -7.4604,0 -13.5,-6.07092 -13.5,-13.53125 0,-7.46033 6.0396,-13.5 13.5,-13.5 7.4603,0 13.5,6.03967 13.5,13.5 0,7.46033 -6.0397,13.53125 -13.5,13.53125 z m 14.0312,-12.625 45.5938,0 z");
        svggate.setFill(new Color(1.0, 1.0, 1.0, 1.0));

        svggate.setStrokeWidth(4);
        svggate.setStroke(new Color(0.0, 0, 0, 1.0));


        Pin inPin1 = new Pin(14.2, 21.5, 12.0, new Color(1.0, 0.0, 1.0, 1.0),"inPin");
 

        Pin inPin2 = new Pin(14.2, 66, 12, new Color(1.0, 0.0, 1.0, 1.0),"inPin");

        Pin outPin = new Pin(219, 45, 12, new Color(1.0, 1.0, 0.0, 1.0),"outPin");

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
        outPins.get(0).state.set((inPins.get(0).state.getValue()|inPins.get(1).state.getValue()));
         System.out.println("Or"+outPins.get(0).state.get());
    }

       public String toString(){
        return "OR Gate";
      
    }
      
  
 
}
