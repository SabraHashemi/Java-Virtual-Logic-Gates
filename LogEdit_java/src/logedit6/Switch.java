/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logedit6;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.adapter.JavaBeanObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;

/**
 *
 * @author admin
 */
class Switch extends Gate {

    //jfxtras.labs.scene.control.gauge.Led led;
    private ObjectProperty<Color> color;
    private BooleanProperty on;
    private Circle lightO;
    private Rectangle lightI;
    private DropShadow GLOW;
    Circle ledOff;
    SVGPath sp;
    Pin outPin;
    
    Switch thisg = this;
    Switch(boolean enabled){super(enabled);}
    Switch(){}

    @Override
    void makeSkin() {
        iconName="switch.gif";

        color = new SimpleObjectProperty<>();
        on = new SimpleBooleanProperty();
        setColor(Color.GREEN);
        setOn(TimerGate.value.getValue());
        
      sp = new SVGPath();
        sp.setContent("M 107 23.8125 C 99.539705 23.8125 93.5 29.85217 93.5 37.3125 C 93.5 44.77283 99.539705 50.84375 107 50.84375 C 114.4604 50.84375 120.5 44.77283 120.5 37.3125 C 120.5 29.85217 114.4604 23.8125 107 23.8125 z M 93.375 36.84375 L 70.40625 37.3125 L 93.375 36.84375 z ");
        sp.setFill(new Color(1.0, 1.0, 1.0, 0.5));
        sp.setStrokeWidth(4);
        sp.setStroke(new Color(0.0, 0, 0, 1.0));

        SVGPath holder = new SVGPath();
        holder.setContent("m 38.266414,6.1663311 c -17.987248,0 -32.5937478,13.9915219 -32.5937478,31.2187519 0,17.22723 14.6064998,31.1875 32.5937478,31.1875 17.98725,0 32.5625,-13.96027 32.5625,-31.1875 0,-17.22723 -14.57525,-31.2187519 -32.5625,-31.2187519 z");
        holder.setFill(new Color(1.0, 1.0, 1.0, 0.5));
        holder.setStrokeWidth(8);
        holder.setStroke(new Color(0.0, 0.0, 0.0, 1.0));

        holder.setEffect(new GaussianBlur(0.9));


         outPin = new Pin(107.0, 37, 12.0, new Color(1.0, 0.0, 1.0, 1.0), "outPin");

        lightO = new Circle(38, 37, 20.0);
        if (on.getValue()) {
            lightO.setStroke(color.getValue().brighter().brighter());
        } else {
            lightO.setStroke(Color.gray(0.3));
        }

        lightO.setStrokeWidth(7);
        lightO.setFill(null);


        lightI = new Rectangle(34, 27, 7, 20.5);
        if (on.getValue()) {
            lightI.setFill(color.getValue().brighter().brighter());
        } else {
            lightI.setFill(Color.gray(0.3));
        }
        //   light.setStyle("-fx-led: " + Util.colorToCssColor(color.getValue())+";"+"-fx-fill  : linear-gradient(from 20% 20% to 90% 90%, derive(-fx-led, -25%) 0%, derive(-fx-led, -45%) 49%, derive(-fx-led, 0%) 100%)");     


         ledOff = new Circle(38, 37, 30.0);
        ledOff.setStyle("-fx-led: " + Util.colorToCssColor(Color.BLACK) + ";" + "-fx-fill  : linear-gradient(from 20% 20% to 90% 90%, derive(-fx-led, -75%) 0%, derive(-fx-led, -82%) 49%, derive(-fx-led, -75%) 100%)");


        Circle highlight = new Circle(38, 37, 30.0);
        highlight.setStyle("-fx-fill  : radial-gradient(focus-angle 0deg, focus-distance 0%, center 12% 12%, radius 50%, rgba(200, 194, 208, 1) 0%, rgba(200, 194, 208, 0) 100%)");



        final InnerShadow INNER_SHADOW = new InnerShadow();
        INNER_SHADOW.setWidth(0.180 * 60);
        INNER_SHADOW.setHeight(0.180 * 60);
        INNER_SHADOW.setRadius(0.15 * 60);
        INNER_SHADOW.setColor(Color.BLACK);
        INNER_SHADOW.setBlurType(BlurType.GAUSSIAN);
        ledOff.setEffect(INNER_SHADOW);


        GLOW = new DropShadow();
        GLOW.setSpread(0.55);
        GLOW.setRadius(0.16 * 70);
        if (on.getValue()) {
            GLOW.setColor(color.getValue());
        } else {
            GLOW.setColor(Color.BLACK);
        }


        GLOW.setBlurType(BlurType.GAUSSIAN);
        GLOW.setInput(INNER_SHADOW);
        lightI.setEffect(GLOW);
        lightO.setEffect(GLOW);






        outPins.add(outPin);

        getChildren().addAll(sp, outPin, holder, ledOff, highlight, lightO, lightI);
        
        outPin.state.bind(on);

//        getChildren().add(led);





    }

    void refresh() {
        if (!initialized) {
            return;
        }
        if (on.getValue()) {
            lightO.setStroke(color.getValue().brighter().brighter());
        } else {
            lightO.setStroke(Color.gray(0.3));
        }

        if (on.getValue()) {
            lightI.setFill(color.getValue().brighter().brighter());
        } else {
            lightI.setFill(Color.gray(0.3));
        }

        if (on.getValue()) {
            GLOW.setColor(color.getValue());
        } else {
            GLOW.setColor(Color.BLACK);
        }



        lightI.setEffect(GLOW);
        lightO.setEffect(GLOW);



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

                Point2D p = localToParent(t.getX(), t.getY());
                if (p.getX() - dragX < 0 || p.getY() - dragY < 0) {
                    t.consume();
                    return;
                }
                AnchorPane.setLeftAnchor(Switch.this, p.getX() - dragX);
                AnchorPane.setTopAnchor(Switch.this, p.getY() - dragY);
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
                       removeSelection(thisg);
                       outPin.setFill( new Color(1.0, 0.0, 1.0, 1.0));
                   }
             if(!selected)
             {
                selected=true;
                    name.setStyle(" -fx-effect: dropshadow( three-pass-box , rgba(0.5,0.5,0.6,0.6) , 10, 0.0 , 0 , 1 );");
                selection(thisg);
                
          outPin.setFill(new Color(0.5,0.5,1,1));
              LogicAppController.name.setText(thisg.toString());
                 LogicAppController.dragX.setText(thisg.dragX+ "");
                  LogicAppController.dragY.setText(thisg.dragY+ "");
               
               }
             
               }
              
                setOn(!getOn());
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
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color.getValue();
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
    public void setOn(boolean onP) {
        on.setValue(onP);
        System.out.println("-->"+on.getValue());
        if( outPins.size()>0)System.out.println("|-->"+outPins.get(0).state.getValue());
        refresh();
    }
           public String toString(){
        return "Switch";
      
    }
           
         
}
