/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logedit6;

import java.beans.PropertyChangeSupport;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import jfxtras.labs.scene.control.gauge.LedBuilder;



class LedDiod extends Gate implements InvalidationListener {

    private ObjectProperty<Color> color;
    private Circle light;
    private Circle ledOff;
    private DropShadow GLOW;
    SVGPath sp;
    LedDiod thisg = this;
    private BooleanProperty on = new SimpleBooleanProperty(true);
LedDiod() {
    }
LedDiod(boolean enabled){super(enabled);}


    @Override
    void makeSkin() {
        iconName="led.gif";

        color = new SimpleObjectProperty<Color>(Color.RED.brighter().brighter().brighter().brighter());
        on = new SimpleBooleanProperty(true);

        setColor(Color.BLUE.brighter().brighter());
        setOn((Math.random() > 0.5 ? true : false));
         sp = new SVGPath();
        sp.setContent("m 14.5,21.567474 c -7.4603002,0 -13.5,6.03967 -13.5,13.5 0,7.46033 6.0396998,13.53125 13.5,13.53125 7.4604,0 13.5,-6.07092 13.5,-13.53125 0,-7.46033 -6.0396,-13.5 -13.5,-13.5 z m 37.15625,13.375 -23.625,0.46875 23.625,-0.46875 z");
        sp.setFill(new Color(1.0, 1.0, 1.0, 0.5));
        sp.setStrokeWidth(4);
        sp.setStroke(new Color(0.0, 0, 0, 1.0));

        SVGPath holder = new SVGPath();
        holder.setContent("m 84.625,3.9424722 c -17.98725,0 -32.59375,13.9915218 -32.59375,31.2187518 0,17.22723 14.6065,31.1875 32.59375,31.1875 17.98725,0 32.5625,-13.96027 32.5625,-31.1875 0,-17.22723 -14.57525,-31.2187518 -32.5625,-31.2187518 z");
        holder.setFill(new Color(1.0, 1.0, 1.0, 0.5));
        holder.setStrokeWidth(8);
        holder.setStroke(new Color(0.0, 0, 0, 1.0));

        holder.setEffect(new GaussianBlur(0.8));


        Pin inPin = new Pin(14.2, 35, 12.0, new Color(1.0, 0.0, 1.0, 1.0),"inPin");

        light = new Circle(84, 35, 30.0);
        light.setVisible(on.getValue());
        light.setStyle("-fx-led: " + Util.colorToCssColor(getColor().getValue().brighter().brighter().brighter()) + ";" + "-fx-fill  : linear-gradient(from 20% 20% to 90% 90%, derive(-fx-led, -25%) 0%, derive(-fx-led, -45%) 49%, derive(-fx-led, 0%) 100%)");


        ledOff = new Circle(84, 35, 30.0);
        ledOff.setStyle("-fx-led: " + Util.colorToCssColor(getColor().getValue().darker().darker()) + ";" + "-fx-fill  : linear-gradient(from 20% 20% to 90% 90%, derive(-fx-led, -75%) 0%, derive(-fx-led, -82%) 49%, derive(-fx-led, -75%) 100%)");
        ledOff.setVisible(!on.getValue());

        Circle highlight = new Circle(84, 35, 30.0);
        highlight.setStyle("-fx-fill  : radial-gradient(focus-angle 0deg, focus-distance 0%, center 12% 12%, radius 50%, rgba(200, 194, 208, 1) 0%, rgba(200, 194, 208, 0) 100%)");

        final InnerShadow INNER_SHADOW = new InnerShadow();
        INNER_SHADOW.setWidth(0.180 * 60);
        INNER_SHADOW.setHeight(0.180 * 60);
        INNER_SHADOW.setRadius(0.15 * 60);
        INNER_SHADOW.setColor(Color.BLACK);
        INNER_SHADOW.setBlurType(BlurType.GAUSSIAN);
        ledOff.setEffect(INNER_SHADOW);
        light.setEffect(INNER_SHADOW);

        GLOW = new DropShadow();
        GLOW.setSpread(0.35);
        GLOW.setRadius(0.16 * 70);
        GLOW.setColor(getColor().getValue());
        GLOW.setBlurType(BlurType.GAUSSIAN);
        GLOW.setInput(INNER_SHADOW);
    //    light.setEffect(GLOW);

        inPins.add(inPin);

        getChildren().addAll(sp, inPin, holder, ledOff, light, highlight);

 
    }

    private void refresh() {

        if (!initialized) {
            return;
        }

         light.setVisible(getOn());
         ledOff.setStyle("-fx-led: " + Util.colorToCssColor(getColor().getValue().darker().darker()) + ";" + "-fx-fill  : linear-gradient(from 20% 20% to 90% 90%, derive(-fx-led, -75%) 0%, derive(-fx-led, -82%) 49%, derive(-fx-led, -75%) 100%)");
         ledOff.setVisible(!on.getValue());
       GLOW.setColor(getColor().getValue());
        light.setStyle("-fx-led: " + Util.colorToCssColor(getColor().getValue()) + ";" + "-fx-fill  : linear-gradient(from 20% 20% to 90% 90%, derive(-fx-led, -25%) 0%, derive(-fx-led, -45%) 49%, derive(-fx-led, 0%) 100%)");


    }

    @Override
    void addBehavior() {


        on.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
            }
        });


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

                Point2D p = localToParent(t.getX(), t.getY());
                if (p.getX() - dragX < 0 || p.getY() - dragY < 0) {
                    t.consume();
                    return;
                }
                AnchorPane.setLeftAnchor(LedDiod.this, p.getX() - dragX);
                AnchorPane.setTopAnchor(LedDiod.this, p.getY() - dragY);
                t.consume();

            }
        });


        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                 if( t.getClickCount()==2)
                 {
                     
                     if(selected)
                     {
                         selected=false;
                         removeSelection(thisg);
                         sp.setStroke(new Color(0,0,0,1));
                         sp.setStrokeWidth(4);
                         
                     }
                     else if(!selected)
                     {
                 name.setStyle(" -fx-effect: dropshadow( three-pass-box , rgba(0.5,0.5,0.6,0.6) , 10, 0.0 , 0 , 1 );");
                selected=true;
                
                selection(thisg);
                
            
                
              LogicAppController.name.setText(thisg.toString());
                 LogicAppController.dragX.setText(thisg.dragX+ "");
                  LogicAppController.dragY.setText(thisg.dragY+ "");
                  sp.setStroke(new Color(0,0,1,1));
                  sp.setStrokeWidth(6);
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
        setOn(inPins.get(0).state.getValue());
    }

    /**
     * @return the color
     */
    public ObjectProperty<Color> getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color cl) {
        color.setValue(cl);
        refresh();
    }

    /**
     * @return the on
     */
    public boolean getOn() {
        return on.getValue();
    }

    /**
     * @param on the on to set
     */
    public void setOn(boolean val) {

        on.setValue(val);
        refresh();
    }

    @Override
    public void invalidated(Observable o) {
        setOnMouseClicked(new EventHandler <MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
              if(t.getClickCount()==2)
              {
                  LogicAppController.name.setText(thisg.toString());
                 LogicAppController.dragX.setText(thisg.dragX+ "");
                  LogicAppController.dragY.setText(thisg.dragY+ "");
              }
            }
        }
                );
        logic();

    }
    
    public String toString(){
        return "Led";
      
    }
    
}
