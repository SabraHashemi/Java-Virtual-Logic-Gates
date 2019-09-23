/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logedit6;

import com.sun.javafx.event.BasicEventDispatcher;
import java.beans.PropertyChangeSupport;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.event.EventType;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;

/**
 *
 * @author admin
 */
class SevenSegment extends Gate {

    SevenSegment thisg = this;
    private ObjectProperty<Color> color;
    private Circle light;
    private Rectangle ledOff;
    private DropShadow GLOW;
    private BooleanProperty on = new SimpleBooleanProperty(true);
    private jfxtras.labs.scene.control.gauge.SevenSegment ledBulb;

    SevenSegment(boolean enabled) {
        super(enabled);
    }
    SevenSegment(){}

    @Override
    void makeSkin() {
        iconName = "and.gif";
        color = new SimpleObjectProperty<>();
        on = new SimpleBooleanProperty();
        setColor(Color.GREEN);

        setColor(Color.BLUE.brighter().brighter());
        setOn((Math.random() > 0.5 ? true : false));
        SVGPath sp = new SVGPath();
        ledBulb = new jfxtras.labs.scene.control.gauge.SevenSegment();
        AnchorPane led = new AnchorPane();
        led.getChildren().add(ledBulb);
        AnchorPane.setLeftAnchor(ledBulb, 60.0);
        AnchorPane.setTopAnchor(ledBulb, 20.0);
        ledBulb.setCharacter("8");
        ledBulb.setDotOn(true);
        ledBulb.setPrefSize(62, 105);

        ledBulb.eventDispatcherProperty().setValue(new BasicEventDispatcher() {
        });
        ledBulb.setColor(Color.GREEN.brighter().brighter().brighter().brighter());




        SVGPath sp1 = new SVGPath();
        sp1.setContent("m 14.03125,10.03125 c -7.4603,0 -13.5,6.03967 -13.5,13.5 0,7.46033 6.0397,13.53125 13.5,13.53125 7.4604,0 13.5,-6.07092 13.5,-13.53125 0,-7.46033 -6.0396,-13.5 -13.5,-13.5 z m 37.15625,13.375 -23.625,0.46875 23.625,-0.46875 z");
        sp1.setFill(new Color(1.0, 1.0, 1.0, 0.5));
        sp1.setStrokeWidth(4);
        sp1.setStroke(new Color(0.0, 0, 0, 1.0));
        Pin inPin1 = new Pin(14.5, 23.5, 11.0, new Color(1.0, 0.0, 1.0, 1.0),"inPin");

        SVGPath sp2 = new SVGPath();
        sp2.setContent("m 14.03125,40.1212 c -7.4603,0 -13.5,6.03967 -13.5,13.5 0,7.46033 6.0397,13.53125 13.5,13.53125 7.4604,0 13.5,-6.07092 13.5,-13.53125 0,-7.46033 -6.0396,-13.5 -13.5,-13.5 z m 37.15625,13.375 -23.625,0.46875 23.625,-0.46875 z");
        sp2.setFill(new Color(1.0, 1.0, 1.0, 0.5));
        sp2.setStrokeWidth(4);
        sp2.setStroke(new Color(0.0, 0, 0, 1.0));
        Pin inPin2 = new Pin(14.5, 54.0, 11.0, new Color(1.0, 0.0, 1.0, 1.0),"inPin");

        SVGPath sp3 = new SVGPath();
        sp3.setContent("m 14.03125,70.21114 c -7.4603,0 -13.5,6.03967 -13.5,13.5 0,7.46033 6.0397,13.53125 13.5,13.53125 7.4604,0 13.5,-6.07092 13.5,-13.53125 0,-7.46033 -6.0396,-13.5 -13.5,-13.5 z m 37.15625,13.375 -23.625,0.46875 23.625,-0.46875 z");
        sp3.setFill(new Color(1.0, 1.0, 1.0, 0.5));
        sp3.setStrokeWidth(4);
        sp3.setStroke(new Color(0.0, 0, 0, 1.0));
        Pin inPin3 = new Pin(14.5, 84.2, 11.0, new Color(1.0, 0.0, 1.0, 1.0),"inPin");

        SVGPath sp4 = new SVGPath();
        sp4.setContent("m 14.03125,100.30111 c -7.4603,0 -13.5,6.03967 -13.5,13.5 0,7.46033 6.0397,13.53125 13.5,13.53125 7.4604,0 13.5,-6.07092 13.5,-13.53125 0,-7.46033 -6.0396,-13.5 -13.5,-13.5 z m 37.15625,13.375 -23.625,0.46875 23.625,-0.46875 z");
        sp4.setFill(new Color(1.0, 1.0, 1.0, 0.5));
        sp4.setStrokeWidth(4);
        sp4.setStroke(new Color(0.0, 0, 0, 1.0));
        Pin inPin4 = new Pin(14.5, 114.0, 11.0, new Color(1.0, 0.0, 1.0, 1.0),"inPin");




        Rectangle frame = new Rectangle(48, 0, 82, 135);
        frame.setFill(new Color(1.0, 1.0, 1.0, 0.5));
        frame.setStrokeWidth(4);
        frame.setStroke(new Color(0.0, 0.0, 0.0, 1.0));

        frame.setEffect(new GaussianBlur(0.9));


         ledOff = new Rectangle(48, 0, 82, 135);
        ledOff.setStyle("-fx-led: " + Util.colorToCssColor(Color.gray(0.4)) + ";" + "-fx-fill  : linear-gradient(from 20% 20% to 90% 90%, derive(-fx-led, -25%) 0%, derive(-fx-led, -82%) 49%, derive(-fx-led, -75%) 100%)");


        Rectangle highlight = new Rectangle(48, 0, 82, 135);
        highlight.setStyle("-fx-fill  : radial-gradient(focus-angle 0deg, focus-distance 10%, center 90%90%, radius 50%, rgba(200, 194, 208, 1) 0%, rgba(200, 194, 208, 0) 100%)");



        final InnerShadow INNER_SHADOW = new InnerShadow();
        INNER_SHADOW.setWidth(0.180 * 60);
        INNER_SHADOW.setHeight(0.180 * 60);
        INNER_SHADOW.setRadius(0.15 * 60);
        INNER_SHADOW.setColor(Color.BLACK);
        INNER_SHADOW.setBlurType(BlurType.GAUSSIAN);
        frame.setEffect(INNER_SHADOW);

        inPins.add(inPin1);
        inPins.add(inPin2);
        inPins.add(inPin3);
        inPins.add(inPin4);

        getChildren().addAll(sp1, sp2, sp3, sp4, ledOff, frame, led, inPin1, inPin2, inPin3, inPin4);

    }

    private void refresh() {

        if (!initialized) {
            return;
        }


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
                if (p.getX() - dragX< 0 || p.getY() - dragY < 0) {
                    t.consume();
                    return;
                }
                AnchorPane.setLeftAnchor(SevenSegment.this, p.getX() - dragX);
                AnchorPane.setTopAnchor(SevenSegment.this, p.getY() - dragY);
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
                    ledOff.setFill(Color.gray(0.4));
                   
                    selected=false;
                    removeSelection(thisg);
                }
                
                else if (!selected)
                {
               name.setStyle(" -fx-effect: dropshadow( three-pass-box , rgba(0.5,0.5,0.6,0.6) , 10, 0.0 , 0 , 1 );");
                selected=true;
                
                selection(thisg);
                
            ledOff.setFill(new Color(0,0,0.5,1));
                
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

    byte getNum() {

        return (byte) (1 * (inPins.get(0).state.getValue() ? 1 : 0)
                + 2 * (inPins.get(1).state.getValue() ? 1 : 0)
                + 4 * (inPins.get(2).state.getValue() ? 1 : 0)
                + 8 * (inPins.get(3).state.getValue() ? 1 : 0));
    }

    @Override
    void logic() {

        byte num = getNum();
        System.out.println(num);
        if (num < 10) {
            ledBulb.setCharacter("" + (char) (num + 48));
        } else {
            ledBulb.setCharacter("" + (char) (num - 10 + 'A'));
        }




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

    public String toString() {
        return "7 Segment";

    }
    
    
}
