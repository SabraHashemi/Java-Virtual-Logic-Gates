/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logedit6;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
abstract class Gate extends Group implements InvalidationListener {

   boolean selected ;
   double dragX=0;;
   double dragY=0;
   DoubleProperty x ;
   DoubleProperty y ;

     String textName = "new gate" ;
     
    BooleanProperty bp ;
    SVGPath svggate ;
    boolean initialized;
   
    ObservableList<Pin> inPins;
    ObservableList<Pin> outPins;
   Label name = new Label();
 
    String iconName;
    boolean enabled;
    BooleanProperty disable;
 

    abstract void makeSkin();

    abstract void addBehavior();
    
  


    abstract void setStyle();

    abstract void logic();

    Gate() {
        this(true);
    }

    Gate(boolean enabled) {
//        disable = new SimpleBooleanProperty();
        inPins = FXCollections.observableArrayList();
        outPins = FXCollections.observableArrayList();
        
       x= new SimpleDoubleProperty();
       y= new SimpleDoubleProperty();
       
      
       bp= new SimpleBooleanProperty();
         
       
      name.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                
               String newName =  JOptionPane.showInputDialog("What is the new Name ?");
               textName=newName;
               name.setText(textName);
               t.consume();
             
            }
        });
       this.enabled = enabled;
       
       x.addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                LogicAppController.dragX.setText(t1.doubleValue()+ "");
                 
            }
        });
       y.addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                LogicAppController.dragY.setText(t1.doubleValue()+ "");
                 
            }
        });
      
        makeSkin();
        if (enabled) {
            addBehavior();
        }
        setStyle();

        for (int i = 0; i < inPins.size(); i++) {
            inPins.get(i).state.addListener(this);
        }
        for (int i = 0; i < outPins.size(); i++) {
            outPins.get(i).state.addListener(this);
        }
        initialized = true;
        
       
    }

    @Override
    
     
   
    public void invalidated(Observable o) {
        logic();

    }
    
    public void  selection( Gate selectedGate )
    { 
  
      FullApp.sgates.add(selectedGate);
     
     LogicAppController.del.setDisable(false);
     LogicAppController.del1.setDisable(false);
     LogicAppController.delete.setDisable(false);
     for(int i=0 ; i<selectedGate.getParent().getChildrenUnmodifiable().size();i++)
           {
               if(selectedGate.getParent().getChildrenUnmodifiable().get(i) instanceof Connector)
               {
                   Connector ct =(Connector)selectedGate.getParent().getChildrenUnmodifiable().get(i);
                   if(ct.added==false)
                   {
                       System.out.println(ct.added);
                         FullApp.gatesg.add( selectedGate.getParent().getChildrenUnmodifiable().get(i), FullApp.i);
                         FullApp.connectors.add(ct);
                         FullApp.i++;
                         ct.added=true;
                   }
                     
      
               }
               
             
            }
     FullApp.gatesg.add(selectedGate, FullApp.i);
     FullApp.i++;
                   
 
    
      
    }
    
    
    public void removeSelection(Gate unselected)
    {
    
    }
    
    
      public void drag (MouseEvent t , Gate gate )
       {
        Point2D p = gate.localToParent(t.getX(), t.getY());
                if (p.getX() - gate.dragX < 0 || p.getY() -gate.dragY < 0) {
                    t.consume();
                    return;
                }
               gate. x.set(p.getX() - gate.dragX) ;
               gate. y.set(p.getY() - gate.dragY);
               gate. x.get();
               gate.y.get();
                AnchorPane.setLeftAnchor(gate, gate.x.doubleValue());
                AnchorPane.setTopAnchor(gate, gate.y.doubleValue());
                
                t.consume();

       }
    
    public void dragAll (MouseEvent t)
    {      
    /*for(Node gate : group.getChildren())
    {
        Gate gate1 = (Gate)gate;
      drag(t,gate1);
    }*/
    }
    
   
    
            
  
            
    public ImageView getIcon() {
        try {
            return new ImageView(new Image(new FileInputStream("img/" + iconName)));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
     public void setName()
     {
    
     name.setText("NewGate");
     name.setTextFill(new Color(0.5,0.4,0.6,1.0));
     if(this instanceof SevenSegment)
     {  
         name.setLayoutY(150);
     }
    else if(this instanceof ComplexGate)
     {
          name.setLayoutY(150);
     }
     else{
     
     name.setLayoutY(90);
     }
     name.setLayoutX(50);
     Font font = new Font("Impact",30);
     name.setFont(font);
 
     
    
    
   

     getChildren().add(name);
   
      }
  
  
    
}
