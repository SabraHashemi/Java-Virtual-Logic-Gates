/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logedit6;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;


/**
 *
 * @author admin
 */
class Pin extends Circle {
    BooleanProperty state;
    Paint fill;
     AnchorPane ap;
     String name;
     Label cname = new Label();
      
    
     public Pin()
     {}
    public Pin(double x, double y, double r, Paint fill , final String name) {
        super(x, y, r, fill);
        this.name=name;
        setFill(fill);
        this.fill = fill;
        state = new SimpleBooleanProperty(false);
        
       
        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                t.consume();
            }
        });
        
        setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if(name.startsWith("outPin"))
                {
                    if(state.get()==true)
                    {
                     Paint newf = new Color(0.0,1.0,0.4,1.0);
                     setFill(newf);
                    }
                    else 
                    {
                      Paint newf = new Color(1.0,0.16,0.16,1.0);
                     setFill(newf);   
                    }
                        
                }
                else{
                if(t.getClickCount()==2)
                {
                     Paint newf = new Color(0.0,1.0,0.4,1.0);
                     setFill(newf);
                     state.set(true);
                     LogicAppController.name.setText(toString());
                     t.consume();
                     
                }
                else if(t.getClickCount()==1)
                {
                     Paint newf = new Color(1.0,0.16,0.16,1.0);
                     setFill(newf);
                     state.set(false);
                     LogicAppController.name.setText(toString());
                     t.consume();
                }
            }
            }
        });
               
        
        setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                Dragboard db = Pin.this.startDragAndDrop(TransferMode.LINK);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString("IN_PIN");
                db.setContent(content);
                event.consume();
            }
        });
        setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                /* accept it only if it is  not dragged from the same node
                 * and if it has a string data */
                if (event.getGestureSource() != this && event.getDragboard().hasString() && event.getDragboard().getString().equals("IN_PIN")) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.LINK);
                }
                event.consume();
            }
        });
        setOnDragDone(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t) {
                t.consume();
            }
        });
        setOnDragExited(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */
                setFill(Color.BLACK);
                event.consume();
            }
        });
        setOnDragEntered(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the target */
                System.out.println("onDragEntered");
                /* show to the user that it is an actual gesture target */
                if (event.getGestureSource() != this && event.getDragboard().hasString()) {
                    setFill(Color.GREEN);
                }
                event.consume();
            }
        });
        setOnDragExited(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */
                setFill(Pin.this.fill);
                event.consume();
            }
        });
        setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* data dropped */
                /* if there is a string data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString() && event.getGestureSource() instanceof Pin) {
                    Connector c = new Connector((Pin) event.getGestureSource(), (Pin) event.getGestureTarget(), Connector.ConnectorType.Curve);
                    
                    ap = (AnchorPane) Pin.this.getParent().getParent();
                    System.out.println(ap);
                    ap.getChildren().add(c);
                    
                    c.toBack();
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);
                event.consume();
            }
        });
        
      
         cname.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
             
               
               t.consume();
             
               
            }
        });
        
    }
    
     
  
    @Override
    public String toString()
    {
        return name;
    }
    
}
