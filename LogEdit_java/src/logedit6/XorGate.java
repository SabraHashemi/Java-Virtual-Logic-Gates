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
class XorGate extends Gate {
    XorGate thisg = this;
   
    XorGate() {
    }
    XorGate(boolean enabled){super(enabled);}

     
    @Override
    void makeSkin() {
                iconName="nor.gif";
         svggate = new SVGPath();
        svggate.setContent("m 67.806721,6.0798522 0,0.2004037 C 74.95052,18.513931 79.076889,32.640677 79.076889,47.765474 c 0,15.124795 -4.126369,29.25154 -11.270168,41.485214 l 0,0.200405 11.270168,0 32.490571,0 c 31.22573,0 58.48327,-16.800272 73.10378,-41.685628 C 170.05073,22.880121 142.79319,6.0798522 111.56746,6.0798522 l -32.490571,0 -11.270168,0 z M 51.561432,6.2802559 C 58.705233,18.513931 62.8316,32.640677 62.8316,47.765474 c 0,15.124795 -4.126367,29.25154 -11.270168,41.485214 m 133.211258,-41.485151 29.24165,0 M 30.442562,26.922668 l 29.24171,0 m -29.24171,41.685626 29.24171,0");
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
        outPins.get(0).state.set(inPins.get(0).state.getValue()^inPins.get(1).state.getValue());
    }

       public String toString(){
        return "Xor Gate";
      
    }

  
}
