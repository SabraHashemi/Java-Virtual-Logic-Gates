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
class XNorGate extends Gate {

    XNorGate thisg = this;
    
    XNorGate(boolean enabled) {
        super(enabled);
    }

    XNorGate() {
    }

    @Override
    void makeSkin() {
        iconName = "xnor.gif";
         svggate = new SVGPath();
        svggate.setContent("m 76.716928,84.834907 c -1.5045,3.1e-4 -3.0557,-0.0143 -4.625,-0.0312 -1.6198,-0.0176 -3.27755,-0.0589 -4.96875,-0.0937 14.9942,-37.02297 14.6578,-49.270008 0.0625,-81.65625 25.8888,-1.11643 44.340802,0.0297 58.250002,5.28125 15.466,5.83936 25.3142,16.75604 33.5,35.3125 -0.017,0.0457 -0.0302,0.04105 -0.0312,0.03125 -19.4324,32.55216 -35.5451,41.1469 -82.187502,41.15625 z m -62.125,-6.1875 c -7.4603,0 -13.53125,-6.03967 -13.53125,-13.5 0,-7.46033 6.07095,-13.5 13.53125,-13.5 7.4604,0 13.5,6.03967 13.5,13.5 0,7.46033 -6.0396,13.5 -13.5,13.5 z m 14.1875,-12.96875 44.71875,0 -44.71875,0 z m 189.812502,-8.59375 c -7.3968,0 -13.3984,-5.97064 -13.5,-13.34375 -8e-4,-0.0633 0,-0.12405 0,-0.1875 0,-7.46033 6.0397,-13.5 13.5,-13.5 7.4604,0 13.53125,6.03967 13.53125,13.5 0,7.46033 -6.07085,13.53125 -13.53125,13.53125 z m -52.84375,-6.5625 c -3.3911,0 -6.125,-2.91517 -6.125,-6.5 0,-3.58483 2.7339,-6.46875 6.125,-6.46875 3.3911,0 6.15625,2.88392 6.15625,6.46875 0,3.58483 -2.76515,6.5 -6.15625,6.5 z m 6.75,-6.09375 32.1875,0 -32.1875,0 z M 15.091928,34.209907 c -7.4603,0 -13.5,-6.03967 -13.5,-13.5 0,-7.460332 6.0397,-13.53125 13.5,-13.53125 7.4604,0 13.53125,6.070918 13.53125,13.53125 0,7.46033 -6.07085,13.5 -13.53125,13.5 z m 14.03125,-14.59375 44.21875,0 -44.21875,0 z");
        svggate.setFill(new Color(1.0, 1.0, 1.0, 1.0));

        svggate.setStrokeWidth(4);
        svggate.setStroke(new Color(0.0, 0, 0, 1.0));

        SVGPath svggateC = new SVGPath();
        svggateC.setContent("m 53.261569,82.821247 0,0 c 14.9942,-37.022972 14.6573,-49.270012 0.062,-81.656254");
        svggateC.setFill(null);
        svggateC.setStrokeWidth(4);
        svggateC.setStroke(new Color(0.0, 0, 0, 1.0));

        Pin inPin1 = new Pin(14.2, 21.5, 12.0, new Color(1.0, 0.0, 1.0, 1.0),"inPin");


        Pin inPin2 = new Pin(14.2, 66, 12, new Color(1.0, 0.0, 1.0, 1.0),"inPin");

        Pin outPin = new Pin(219, 45, 12, new Color(1.0, 1.0, 0.0, 1.0),"outPin");

        getChildren().add(svggate);
        getChildren().add(svggateC);
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
                dragX=t.getX();
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
        outPins.get(0).state.set(!(inPins.get(0).state.getValue() ^ inPins.get(1).state.getValue()));
    }

    public String toString() {
        return "XNOR Gate";

    }
    
  
  
}
